package fr.coding.bankaccount.features;

import fr.coding.bankaccount.exceptions.AccountNotFoundException;

public interface IBeneficiary {
    void attachBeneficiary(Long accountHolderID, Long accountBeneficiaryID)  throws AccountNotFoundException;
}
