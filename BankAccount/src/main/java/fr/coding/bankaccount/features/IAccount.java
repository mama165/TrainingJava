package fr.coding.bankaccount.features;

import fr.coding.bankaccount.exceptions.AccountAlreadyExistsExceptions;

public interface IAccount {
    void open(String firstName, String lastName) throws AccountAlreadyExistsExceptions;
}
