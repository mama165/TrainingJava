package fr.coding.bankaccount.printers;

import fr.coding.bankaccount.formatters.OperationFormatter;
import fr.coding.bankaccount.models.Operation;

import java.math.BigDecimal;
import java.util.List;

public class TextOperationPrinter implements OperationPrinter {
    private final OperationFormatter operationFormatter;

    public TextOperationPrinter(OperationFormatter operationFormatter) {
        this.operationFormatter = operationFormatter;
    }

    @Override
    public void print(List<Operation> operations, BigDecimal balance) {
        String format = operationFormatter.format(operations, balance);
        System.out.print(format);
    }
}
