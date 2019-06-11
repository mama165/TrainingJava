package fr.coding.bankaccount.features;

import fr.coding.bankaccount.exceptions.AccountNotFoundException;
import fr.coding.bankaccount.exceptions.AmountNegativeException;
import fr.coding.bankaccount.exceptions.NotEnoughMoneyOnAccountException;

public interface IWithdraw {
    void withdraw(Long accountID, String input) throws AmountNegativeException, NotEnoughMoneyOnAccountException, AccountNotFoundException;
}
