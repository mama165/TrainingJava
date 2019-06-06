package fr.coding.bankaccount.formatters;

import fr.coding.bankaccount.models.Operation;

import java.math.BigDecimal;
import java.util.List;

public interface OperationFormatter {
    String format(List<Operation> operations, BigDecimal balance);
}
