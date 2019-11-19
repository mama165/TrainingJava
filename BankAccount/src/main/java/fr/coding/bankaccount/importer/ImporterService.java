package fr.coding.bankaccount.importer;

import akka.Done;
import akka.NotUsed;
import akka.actor.ActorSystem;
import akka.stream.FlowShape;
import akka.stream.Graph;
import akka.stream.javadsl.Flow;
import akka.stream.javadsl.Framing;
import akka.stream.javadsl.FramingTruncation;
import akka.stream.javadsl.StreamConverters;
import akka.util.ByteString;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import fr.coding.bankaccount.Balancer.Balancer;
import fr.coding.bankaccount.features.ICsvImporter;
import fr.coding.bankaccount.models.Amount;
import fr.coding.bankaccount.models.Operation;
import fr.coding.bankaccount.models.OperationType;
import fr.coding.bankaccount.repositories.OperationRepository;
import io.vavr.collection.List;
import io.vavr.control.Try;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.zip.GZIPInputStream;

public class ImporterService implements ICsvImporter {
    private final OperationRepository operationRepository;
    private final ActorSystem system;
    private final File importDirectory;
    private final int linesToSkip;
    private final int concurrentFiles;
    private final int concurrentWrites;
    private final int nonIOParallelism;
    private static final Logger logger = LoggerFactory.getLogger(ImporterService.class);

    public ImporterService(Config config, OperationRepository operationRepository, ActorSystem system) {
        this.operationRepository = operationRepository;
        this.system = system;
        this.importDirectory = Paths.get(config.getString("importer.import-directory")).toFile();
        this.linesToSkip = config.getInt("importer.lines-to-skip");
        this.concurrentFiles = config.getInt("importer.concurrent-files");
        this.concurrentWrites = config.getInt("importer.concurrent-writes");
        this.nonIOParallelism = config.getInt("importer.non-io-parallelism");
    }

    @Override
    public CompletionStage<Done> importFromFiles() {
        Config config = ConfigFactory.load();
        ActorSystem system = ActorSystem.create();
        List<File> files = List.of(importDirectory.listFiles());

        Graph<FlowShape<File, ValidOperation>, NotUsed> balancer = Balancer.create(concurrentFiles, processSingleFile());



        return null;
    }

    private Flow<ByteString, ByteString, NotUsed> lineDelimiter =
            Framing.delimiter(ByteString.fromString("\n"), 128, FramingTruncation.ALLOW);

    private Flow<File, ValidOperation, NotUsed> processSingleFile() {
        return Flow.of(File.class).flatMapConcat(file -> {
            GZIPInputStream inputStream = new GZIPInputStream(new FileInputStream(file));
            return StreamConverters.fromInputStream(() -> inputStream)
                    .via(lineDelimiter)
                    .drop(linesToSkip)
                    .map(ByteString::utf8String)
                    .mapAsync(nonIOParallelism, this::parseLine);
        });
    }

    private CompletionStage<ValidOperation> parseLine(String line) {
        return CompletableFuture.supplyAsync(() -> {
            String[] fields = line.split(",");
            Long accountID = Long.parseLong(fields[0]);
            Amount amount = Amount.create(fields[1]);
            OperationType operationType = "Deposit".equals(fields[2]) ? OperationType.DEPOSIT : OperationType.WITHDRAWAL;
            Instant time = Instant.parse(fields[3]);

            return Try
                    .of(() -> (BaseOperation) Operation.create(accountID, amount, operationType, time))
                    .onFailure(e -> logger.error("Unable to parse line: {}: {}", line, e.getMessage()))
        });
    }
}
