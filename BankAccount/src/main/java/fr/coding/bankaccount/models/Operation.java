package fr.coding.bankaccount.models;

import fr.coding.bankaccount.importer.BaseOperation;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Operation  implements BaseOperation {
    private final Long accountID;
    private final BigDecimal amount;
    private final OperationType operationType;
    private final Instant time;

    private Operation(Long accountID, BigDecimal amount, OperationType operationType, Instant time) {
        this.accountID = accountID;
        this.amount = amount;
        this.operationType = operationType;
        this.time = time;
    }

    public static Operation create(Long accountID, Amount amount, OperationType operationType, Instant time) {
        validateNotNull(accountID, operationType, time);

        return new Operation(accountID, amount.getValue(), operationType, time);
    }

    private static void validateNotNull(Long accountID, OperationType operationType, Instant time) {
        List<String> illegalArguments = new ArrayList<>();

        if (accountID == null)
            illegalArguments.add("accountID");

        if (operationType == null)
            illegalArguments.add("operationType");

        if (time == null)
            illegalArguments.add("time");

        if (!illegalArguments.isEmpty()) {
            throw new IllegalArgumentException("Multiple illegal arguments : " + String.join(",", illegalArguments));
        }
    }

    @Override
    public Long getAccountID() {
        return accountID;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Instant getTime() {
        return time;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Operation operation = (Operation) o;
        return Objects.equals(accountID, operation.accountID) &&
                Objects.equals(amount, operation.amount) &&
                Objects.equals(time, operation.time) &&
                operationType == operation.operationType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountID, amount, time, operationType);
    }
}
