package fr.coding.bankaccount.exceptions;

public class BeneficiaryUnrecognizedException extends Exception {
    public BeneficiaryUnrecognizedException(Long accountHolderID, Long accountBeneficiaryID) {
        super("The account with id : " + accountBeneficiaryID + " is not a beneficiary of account " + accountHolderID);
    }
}
