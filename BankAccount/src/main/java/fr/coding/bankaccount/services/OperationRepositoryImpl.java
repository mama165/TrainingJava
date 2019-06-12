package fr.coding.bankaccount.services;

import fr.coding.bankaccount.exceptions.AccountNotFoundException;
import fr.coding.bankaccount.models.Operation;
import fr.coding.bankaccount.repositories.OperationRepository;

import java.util.Collections;
import java.util.List;

public class OperationRepositoryImpl implements OperationRepository {
    @Override
    public void add(Operation operation) throws AccountNotFoundException {

    }

    @Override
    public List<Operation> findAll(Long accountID) throws AccountNotFoundException {

        return Collections.emptyList();
    }
}
