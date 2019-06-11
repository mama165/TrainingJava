package fr.coding.bankaccount.formatters;

import fr.coding.bankaccount.models.Operation;
import fr.coding.bankaccount.models.OperationType;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.System.lineSeparator;

public class TextOperationFormatter implements OperationFormatter {
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final String EMPTY_VALUE = "";

    @Override
    public String format(List<Operation> operations, BigDecimal balance) {
        if (operations == null || operations.isEmpty()) {
            return EMPTY_VALUE;
        }

        Long accountID = operations.get(0).getAccountID();

        String header = String.format("| AccountID : %s, Balance : %s%s", accountID, balance, lineSeparator());

        String tables = operations
                .stream()
                .map(this::formatLine)
                .collect(Collectors.joining(lineSeparator()));

        return header + tables + lineSeparator();
    }

    private String formatLine(Operation operation) {
        BigDecimal amount = operation.getAmount();
        OperationType operationType = operation.getOperationType();
        Instant date = operation.getTime();

        String stringToFormat = "| Date : %s, Amount : %s, Operation : %s";
        return String.format(stringToFormat, new SimpleDateFormat(DATE_FORMAT).format(Date.from(date)), amount.toString(), operationType.toString());
    }
}
