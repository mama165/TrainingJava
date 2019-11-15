package fr.coding.bankaccount.repositories;

import fr.coding.bankaccount.exceptions.AccountAlreadyExistsExceptions;
import fr.coding.bankaccount.models.Account;

public interface AccountRepository {
    void add(Account account) throws AccountAlreadyExistsExceptions;
}
