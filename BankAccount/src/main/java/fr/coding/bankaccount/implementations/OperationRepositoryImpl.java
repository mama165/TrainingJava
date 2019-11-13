package fr.coding.bankaccount.implementations;

import fr.coding.bankaccount.exceptions.AccountNotFoundException;
import fr.coding.bankaccount.models.Operation;
import fr.coding.bankaccount.repositories.OperationRepository;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class OperationRepositoryImpl implements OperationRepository {
    private final List<Operation> operations = new LinkedList<>();

    @Override
    public void add(Operation operation) throws AccountNotFoundException {
        operations.add(operation);
    }

    @Override
    public List<Operation> findAll(Long accountID) throws AccountNotFoundException {
        Predicate<Operation> operationIsNotNull = operation -> operation.getAccountID() != null
                && operation.getAccountID().equals(accountID);

        return operations
                .stream()
                .filter(operationIsNotNull)
                .collect(Collectors.toList());
    }
}
