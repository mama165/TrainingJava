package fr.coding.bankaccount.implementations;

import fr.coding.bankaccount.exceptions.AccountNotFoundException;
import fr.coding.bankaccount.repositories.BeneficiaryRepository;

import java.util.*;

import static java.util.Optional.ofNullable;

public class BeneficiaryOperationImpl implements BeneficiaryRepository {
    private final Map<Long, LinkedList<Long>> mapOfBeneficiaries = new HashMap<>();

    @Override
    public List<Long> findAll(Long accountHolderID) throws AccountNotFoundException {
        List<Long> nullableBeneficiaryIDs = mapOfBeneficiaries.get(accountHolderID);
        return ofNullable(nullableBeneficiaryIDs)
                .orElse(Collections.emptyList());
    }

    @Override
    public void add(Long accountHolderID, Long attachedBeneficiaryID) throws AccountNotFoundException {
        mapOfBeneficiaries.putIfAbsent(accountHolderID, storeBeneficiaryIDs(attachedBeneficiaryID, null));

        mapOfBeneficiaries.computeIfPresent(accountHolderID,
                (key, actualAccountBeneficiaryIDs) -> storeBeneficiaryIDs(attachedBeneficiaryID, actualAccountBeneficiaryIDs));
    }

    private LinkedList<Long> storeBeneficiaryIDs(Long attachedBeneficiaryID, List<Long> actualBeneficiaryIDs) {
        if (actualBeneficiaryIDs == null) return new LinkedList<>(Collections.singletonList(attachedBeneficiaryID));

        actualBeneficiaryIDs.add(attachedBeneficiaryID);
        return (LinkedList<Long>) Collections.unmodifiableList(actualBeneficiaryIDs);
    }
}
