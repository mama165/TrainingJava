package fr.coding.bankaccount.features;

import fr.coding.bankaccount.exceptions.AccountNotFoundException;
import fr.coding.bankaccount.printers.OperationPrinter;

public interface IReport {
  void printStatement(OperationPrinter operationPrinter, Long accountID) throws AccountNotFoundException;
}
