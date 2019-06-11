package fr.coding.bankaccount.models;

public enum OperationType {
    DEPOSIT("Deposit"),
    WITHDRAWAL("Withdrawal");

    private final String type;

    OperationType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}
