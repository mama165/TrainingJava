package fr.coding.bankaccount.importer;

import java.util.Objects;

public class InvalidOperation  implements BaseOperation {
    private final Long accountID;

    InvalidOperation(Long accountID) {
        this.accountID = accountID;
    }

    @Override
    public Long getAccountID() {
        return accountID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvalidOperation that = (InvalidOperation) o;
        return Objects.equals(accountID, that.accountID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountID);
    }
}
