package fr.coding.bankaccount.printers;

import fr.coding.bankaccount.models.Operation;

import java.math.BigDecimal;
import java.util.List;

public interface OperationPrinter {
    void print(List<Operation> operations, BigDecimal balance);
}
