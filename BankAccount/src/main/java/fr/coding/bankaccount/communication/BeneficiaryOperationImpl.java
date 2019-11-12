package fr.coding.bankaccount.communication;

import fr.coding.bankaccount.exceptions.AccountNotFoundException;
import fr.coding.bankaccount.repositories.BeneficiaryRepository;

import java.util.List;

public class BeneficiaryOperationImpl implements BeneficiaryRepository {

    @Override
    public List<Long> findAll(Long accountHolderID) throws AccountNotFoundException {
        return null;
    }

    @Override
    public void add(Long accountHolderID, Long accountBeneficiaryID) throws AccountNotFoundException {

    }
}
