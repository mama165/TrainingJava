package fr.coding.bankaccount.exceptions;

public class AccountAlreadyExistsExceptions extends Exception {
    public AccountAlreadyExistsExceptions(Long accountID) {
        super("Account with id : " + accountID + " already exists");
    }
}
