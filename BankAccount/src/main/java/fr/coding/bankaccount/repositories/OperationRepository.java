package fr.coding.bankaccount.repositories;

import fr.coding.bankaccount.exceptions.AccountNotFoundException;
import fr.coding.bankaccount.models.Operation;

import java.util.List;

public interface OperationRepository {
    void add(Operation operation) throws AccountNotFoundException;

    List<Operation> findAll(Long accountID) throws AccountNotFoundException;
}
