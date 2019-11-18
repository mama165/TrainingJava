package fr.coding.bankaccount.features;

import fr.coding.bankaccount.exceptions.AccountAlreadyExistsExceptions;
import fr.coding.bankaccount.exceptions.AccountNotFoundException;
import fr.coding.bankaccount.exceptions.AmountNegativeException;
import fr.coding.bankaccount.rules.DiscountCalculator;

public interface IAccount {
    void open(String firstName, String lastName) throws AccountAlreadyExistsExceptions;

    void close(Long accountID) throws AccountNotFoundException;

    void attachBeneficiary(Long accountHolderID, Long accountBeneficiaryID) throws AccountNotFoundException;

    void applyDiscount(DiscountCalculator discountCalculator, Long accountID) throws AccountNotFoundException, AmountNegativeException;
}
