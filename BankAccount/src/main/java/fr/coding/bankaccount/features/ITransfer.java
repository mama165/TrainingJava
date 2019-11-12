package fr.coding.bankaccount.features;

import fr.coding.bankaccount.exceptions.AccountNotFoundException;
import fr.coding.bankaccount.exceptions.AmountNegativeException;
import fr.coding.bankaccount.exceptions.BeneficiaryUnrecognizedException;
import fr.coding.bankaccount.exceptions.NotEnoughMoneyOnAccountException;

public interface ITransfer {
    void transfer(Long ownerID, Long accountID, String value) throws AmountNegativeException, AccountNotFoundException, NotEnoughMoneyOnAccountException, BeneficiaryUnrecognizedException;
}
