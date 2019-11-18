package fr.coding.bankaccount.importer;

import akka.actor.ActorSystem;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import fr.coding.bankaccount.features.ICsvImporter;
import fr.coding.bankaccount.repositories.OperationRepository;

public class ImporterService implements ICsvImporter {
    private final OperationRepository operationRepository;
    private final ActorSystem system;

    public ImporterService(OperationRepository operationRepository, ActorSystem system) {
        this.operationRepository = operationRepository;
        this.system = system;
    }

    @Override
    public void importFromFiles() {
        Config config = ConfigFactory.load();
        ActorSystem system = ActorSystem.create();

    }
}
