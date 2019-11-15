package fr.coding.bankaccount.rules;

import fr.coding.bankaccount.exceptions.AccountNotFoundException;
import fr.coding.bankaccount.models.Account;
import fr.coding.bankaccount.repositories.AccountRepository;
import fr.coding.bankaccount.services.DateService;

import java.math.BigDecimal;

public class LoyalAccountRule implements IDiscountRule {
    private final int years;
    private final BigDecimal discount;
    private final AccountRepository accountRepository;
    private final DateService dateService;

    LoyalAccountRule(AccountRepository accountRepository, DateService dateService, int years, BigDecimal discount) {
        this.years = years;
        this.discount = discount;
        this.accountRepository = accountRepository;
        this.dateService = dateService;
    }

    @Override
    public BigDecimal calculateAccountDiscount(Long accountID) throws AccountNotFoundException {
        Account account = accountRepository.find(accountID);

        if(account.hasBeenLoyal(years, dateService.getDate())) {
            return discount;
        }

        return BigDecimal.ZERO;
    }
}
