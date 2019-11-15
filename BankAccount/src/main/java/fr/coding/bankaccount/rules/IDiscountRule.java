package fr.coding.bankaccount.rules;

import fr.coding.bankaccount.exceptions.AccountNotFoundException;

import java.math.BigDecimal;

public interface IDiscountRule {
    BigDecimal calculateAccountDiscount(Long accountID) throws AccountNotFoundException;
}
