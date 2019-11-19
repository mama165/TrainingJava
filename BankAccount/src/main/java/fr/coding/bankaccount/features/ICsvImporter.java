package fr.coding.bankaccount.features;

import akka.Done;

import java.util.concurrent.CompletionStage;

public interface ICsvImporter  {
    CompletionStage<Done> importFromFiles();
}
