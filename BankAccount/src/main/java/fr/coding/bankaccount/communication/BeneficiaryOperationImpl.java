package fr.coding.bankaccount.communication;

import fr.coding.bankaccount.exceptions.AccountNotFoundException;
import fr.coding.bankaccount.repositories.BeneficiaryRepository;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class BeneficiaryOperationImpl implements BeneficiaryRepository {
    private Map<Long, List<Long>> mapOfBeneficiaries = Collections.emptyMap();

    @Override
    public List<Long> findAll(Long accountHolderID) throws AccountNotFoundException {
        return mapOfBeneficiaries.get(accountHolderID);
    }

    @Override
    public void add(Long accountHolderID, Long accountBeneficiaryID) throws AccountNotFoundException {
        List<Long> beneficiaryIDs = mapOfBeneficiaries.get(accountHolderID);
        beneficiaryIDs.add(accountBeneficiaryID);
    }
}
