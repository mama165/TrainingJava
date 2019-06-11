package fr.coding.bankaccount.exceptions;

public class AccountNotFoundException extends Exception {
    AccountNotFoundException(Long accountID) {
        super("Account with id : " + accountID + " doesn't exist");
    }
}
