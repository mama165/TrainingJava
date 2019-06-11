package fr.coding.bankaccount.features;

import fr.coding.bankaccount.exceptions.AccountNotFoundException;
import fr.coding.bankaccount.exceptions.AmountNegativeException;

public interface IDeposit {
    void deposit(Long accountID, String value) throws AmountNegativeException, AccountNotFoundException;
}
