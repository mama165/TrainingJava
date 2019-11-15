package fr.coding.bankaccount.rules;

import java.math.BigDecimal;

public class LoyalAccountRule implements IDiscountRule {
    private final int years;
    private final BigDecimal discount;

    LoyalAccountRule(int years, BigDecimal discount) {
        this.years = years;
        this.discount = discount;
    }

    @Override
    public BigDecimal calculateAccountDiscount(Long accountID) {
        return BigDecimal.ZERO;
    }
}
