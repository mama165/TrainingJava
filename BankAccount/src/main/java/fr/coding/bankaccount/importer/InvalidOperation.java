package fr.coding.bankaccount.importer;

public class InvalidOperation  implements BaseOperation{
    @Override
    public Long getAccountID() {
        return null;
    }
}
