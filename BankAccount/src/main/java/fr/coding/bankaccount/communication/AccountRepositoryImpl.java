package fr.coding.bankaccount.communication;

import fr.coding.bankaccount.exceptions.AccountAlreadyExistsExceptions;
import fr.coding.bankaccount.exceptions.AccountNotFoundException;
import fr.coding.bankaccount.models.Account;
import fr.coding.bankaccount.repositories.AccountRepository;

public class AccountRepositoryImpl implements AccountRepository {
    @Override
    public void add(Account account) throws AccountAlreadyExistsExceptions {

    }

    @Override
    public void remove(Long accountID) {

    }

    @Override
    public Account find(Long accountID) throws AccountNotFoundException {
        return null;
    }
}
