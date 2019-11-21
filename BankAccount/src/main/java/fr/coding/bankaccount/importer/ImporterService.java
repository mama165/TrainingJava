package fr.coding.bankaccount.importer;

import akka.Done;
import akka.NotUsed;
import akka.actor.ActorSystem;
import akka.stream.ActorMaterializer;
import akka.stream.FlowShape;
import akka.stream.Graph;
import akka.stream.javadsl.*;
import akka.util.ByteString;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import fr.coding.bankaccount.balancer.Balancer;
import fr.coding.bankaccount.features.ICsvImporter;
import fr.coding.bankaccount.models.Amount;
import fr.coding.bankaccount.models.Operation;
import fr.coding.bankaccount.models.OperationType;
import io.vavr.collection.List;
import io.vavr.control.Try;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.zip.GZIPInputStream;

import static java.lang.System.lineSeparator;

public class ImporterService implements ICsvImporter {
    private final ActorSystem system;
    private final File importDirectory;
    private final int linesToSkip;
    private final int concurrentFiles;
    private final int concurrentWrites;
    private final int nonIOParallelism;
    private static final Logger logger = LoggerFactory.getLogger(ImporterService.class);

    public static void main(String[] args) {
//        String dateInString = "2014-10-05T15:23:01Z";
//
//        Instant instant = Instant.parse(dateInString);
//        System.out.println(instant.toString());
        ActorSystem system = ActorSystem.create();
        ImporterService importerService = new ImporterService(ConfigFactory.load(), system);
        importerService
                .importFromFiles()
                .thenAccept(d -> system.terminate());
    }

    public ImporterService(Config config, ActorSystem system) {
        this.system = system;
        this.importDirectory = Paths.get(config.getString("importer.import-directory")).toFile();
        this.linesToSkip = config.getInt("importer.lines-to-skip");
        this.concurrentFiles = config.getInt("importer.concurrent-files");
        this.concurrentWrites = config.getInt("importer.concurrent-writes");
        this.nonIOParallelism = config.getInt("importer.non-io-parallelism");
    }

    @Override
    public CompletionStage<Done> importFromFiles() {
        List<File> files = List.of(importDirectory.listFiles());

        long startTime = System.currentTimeMillis();

        Graph<FlowShape<File, Operation>, NotUsed> balancer = Balancer.create(concurrentFiles, processSingleFile());

        return Source.from(files)
                .via(balancer)
                .runForeach(this::performLogOperation, ActorMaterializer.create(system))
                .whenComplete((d, e) -> {
                    if (d != null) {
                        logger.info("Import finished in {}s", (System.currentTimeMillis() - startTime) / 1000.0);
                    } else {
                        logger.error("Import failed", e);
                    }
                });
    }

    private void performLogOperation(Operation operation) {
        logger.info("AccountID : {}", operation.getAccountID());
    }


    private Flow<ByteString, ByteString, NotUsed> lineDelimiter =
            Framing.delimiter(ByteString.fromString(lineSeparator()), 128, FramingTruncation.ALLOW);

    private Flow<File, Operation, NotUsed> processSingleFile() {
        return Flow.of(File.class)
                .via(parseFile())
                .via(compute());
    }

    private Flow<BaseOperation, Operation, NotUsed> compute() {
        return Flow.of(BaseOperation.class)
                .filter(Operation.class::isInstance)
                .map(Operation.class::cast);
    }

    private BigDecimal computeBalance(List<Operation> operations) {
        BigDecimal currentBalance = BigDecimal.ZERO;

        for (Operation operation : operations) {
            BigDecimal amount = operation.getAmount();
            switch (operation.getOperationType()) {
                case WITHDRAWAL:
                    currentBalance = currentBalance.subtract(amount);
                    break;
                case DEPOSIT:
                    currentBalance = currentBalance.add(amount);
                    break;
                default:
                    break;
            }
        }
        return currentBalance;
    }


    private Flow<File, BaseOperation, NotUsed> parseFile() {
        return Flow.of(File.class).flatMapConcat(file -> {
            GZIPInputStream inputStream = new GZIPInputStream(new FileInputStream(file));
            return StreamConverters.fromInputStream(() -> inputStream)
                    .via(lineDelimiter)
                    .drop(linesToSkip)
                    .map(ByteString::utf8String)
                    .mapAsync(nonIOParallelism, this::parseLine);
        });
    }


    private CompletionStage<BaseOperation> parseLine(String line) {
        return CompletableFuture.supplyAsync(() -> {
            String[] fields = line.split(",");
            Long accountID = Long.parseLong(fields[0]);

            return Try
                    .of(() ->{
                        Amount amount = Amount.create(fields[1]);
                        String depositValue = OperationType.DEPOSIT.toString();
                        OperationType operationType = depositValue.equals(fields[2]) ? OperationType.DEPOSIT : OperationType.WITHDRAWAL;
                        Instant time = Instant.parse(fields[3]);
                        BaseOperation baseOperation = Operation.create(accountID, amount, operationType, time);
                        return baseOperation;
                    })
                    .onFailure(e -> logger.error("Unable to parse line: {}: {}", line, e.getMessage()))
                    .getOrElse(new InvalidOperation(accountID));
        });
    }
}
