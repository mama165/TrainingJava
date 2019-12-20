package fr.coding.bankaccount.rules;

import fr.coding.bankaccount.exceptions.AccountNotFoundException;
import fr.coding.bankaccount.models.Operation;
import fr.coding.bankaccount.repositories.OperationRepository;

import java.math.BigDecimal;
import java.util.List;

public final class NegativeAccountRule implements IDiscountRule {
    private final BigDecimal discount;
    private final OperationRepository operationRepository;
    private final int maxWithdrawal;

    NegativeAccountRule(OperationRepository operationRepository, int maxWithdrawal, BigDecimal discount) {
        this.operationRepository = operationRepository;
        this.maxWithdrawal = maxWithdrawal;
        this.discount = discount;
    }

    @Override
    public BigDecimal calculateAccountDiscount(Long accountID) throws AccountNotFoundException {
        List<Operation> operations = operationRepository.findAll(accountID);
        int negativeNumbersTime = negativeOperationsNumber(operations);

        if (negativeNumbersTime < maxWithdrawal) {
            return discount;
        }

        return BigDecimal.ZERO;
    }

    private int negativeOperationsNumber(List<Operation> operations) {
        BigDecimal currentBalance = BigDecimal.ZERO;
        int count = 0;

        for (Operation operation : operations) {
            BigDecimal amount = operation.getAmount();
            switch (operation.getOperationType()) {
                case WITHDRAWAL:
                    currentBalance = currentBalance.subtract(amount);
                    break;
                case DEPOSIT:
                    currentBalance = currentBalance.add(amount);
                    break;
                default:
                    break;
            }
            if (currentBalance.compareTo(BigDecimal.ZERO) < 0) {
                count++;
            }
        }
        return count;
    }
}
