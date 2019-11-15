package fr.coding.bankaccount.repositories;

import fr.coding.bankaccount.exceptions.AccountAlreadyExistsExceptions;
import fr.coding.bankaccount.exceptions.AccountNotFoundException;
import fr.coding.bankaccount.models.Account;

public interface AccountRepository {
    void add(Account account) throws AccountAlreadyExistsExceptions;

    Account find(Long accountID) throws AccountNotFoundException;
}
