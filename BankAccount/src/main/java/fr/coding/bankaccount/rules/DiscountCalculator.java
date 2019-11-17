package fr.coding.bankaccount.rules;

import fr.coding.bankaccount.exceptions.AccountNotFoundException;
import fr.coding.bankaccount.repositories.AccountRepository;
import fr.coding.bankaccount.repositories.OperationRepository;
import fr.coding.bankaccount.services.DateService;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

public class DiscountCalculator implements IDiscountCalculator {
    private final List<IDiscountRule> rules = new LinkedList<>();
    private static final BigDecimal defaultDiscount = new BigDecimal(500);
    private static final int defaultYears = 5;
    private static final int maxWithdrawal = 5;

    DiscountCalculator(AccountRepository accountRepository, OperationRepository operationRepository, DateService dateService) {
        rules.add(new LoyalAccountRule(accountRepository, dateService, defaultYears, defaultDiscount));
        rules.add(new NegativeAccountRule(operationRepository, maxWithdrawal,  defaultDiscount));
    }

    @Override
    public BigDecimal calculateDiscountPercentage(Long accountID) throws AccountNotFoundException {
        BigDecimal currentDiscount = BigDecimal.ZERO;

        for (IDiscountRule discountRule : rules) {
            BigDecimal discountAfterRule = discountRule.calculateAccountDiscount(accountID);
            currentDiscount = currentDiscount.max(discountAfterRule);
        }
        return currentDiscount;
    }
}
