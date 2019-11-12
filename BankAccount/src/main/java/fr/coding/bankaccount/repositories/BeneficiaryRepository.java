package fr.coding.bankaccount.repositories;

import fr.coding.bankaccount.exceptions.AccountNotFoundException;

import java.util.List;

public interface BeneficiaryRepository {

    List<Long> findAll(Long accountHolderID) throws AccountNotFoundException;

    void add(Long accountHolderID, Long accountBeneficiaryID) throws AccountNotFoundException;
}
