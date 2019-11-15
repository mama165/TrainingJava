package fr.coding.bankaccount.rules;

import fr.coding.bankaccount.exceptions.AccountNotFoundException;

import java.math.BigDecimal;

public interface IDiscountCalculator {
    BigDecimal calculateDiscountPercentage(Long accountID) throws AccountNotFoundException;
}
