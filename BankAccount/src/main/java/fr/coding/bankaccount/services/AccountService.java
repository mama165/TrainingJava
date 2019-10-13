package fr.coding.bankaccount.services;

import fr.coding.bankaccount.exceptions.AccountNotFoundException;
import fr.coding.bankaccount.exceptions.AmountNegativeException;
import fr.coding.bankaccount.exceptions.NotEnoughMoneyOnAccountException;
import fr.coding.bankaccount.features.IDeposit;
import fr.coding.bankaccount.features.IReport;
import fr.coding.bankaccount.features.ITransfer;
import fr.coding.bankaccount.features.IWithdraw;
import fr.coding.bankaccount.models.Amount;
import fr.coding.bankaccount.models.Operation;
import fr.coding.bankaccount.models.OperationType;
import fr.coding.bankaccount.printers.OperationPrinter;
import fr.coding.bankaccount.repositories.OperationRepository;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

public class AccountService implements ITransfer, IDeposit, IWithdraw, IReport {
    private final OperationRepository operationRepository;
    private final DateService dateService;

    public AccountService(OperationRepository operationRepository, DateService dateService) {
        this.operationRepository = operationRepository;
        this.dateService = dateService;
    }

    @Override
    public void deposit(Long accountID, String value) throws AmountNegativeException, AccountNotFoundException {
        Amount amount = Amount.create(value);
        Operation depositOperation = Operation.create(accountID, amount, OperationType.DEPOSIT, dateService.getDate());
        operationRepository.add(depositOperation);
    }

    @Override
    public void withdraw(Long accountID, String value) throws AmountNegativeException, NotEnoughMoneyOnAccountException, AccountNotFoundException {
        Amount amount = Amount.create(value);
        BigDecimal amountExtracted = amount.getValue();

        Operation withdrawalOperation = Operation.create(accountID, amount, OperationType.WITHDRAWAL, dateService.getDate());
        List<Operation> unmodifiableOperations = Collections.unmodifiableList(operationRepository.findAll(accountID));
        BigDecimal balance = computeBalance(unmodifiableOperations);

        if (balance.compareTo(amountExtracted) < 0) {
            throw new NotEnoughMoneyOnAccountException(amountExtracted);
        }
        operationRepository.add(withdrawalOperation);
    }

    @Override
    public void transfer(Long ownerID, Long accountID, String value) throws AmountNegativeException, AccountNotFoundException, NotEnoughMoneyOnAccountException {
        if (ownerID == null || accountID == null) throw new NullPointerException();

        this.withdraw(ownerID, value);
        this.deposit(accountID, value);
    }

    @Override
    public void printStatement(OperationPrinter operationPrinter, Long accountID) throws AccountNotFoundException {
        List<Operation> unmodifiableOperations = Collections.unmodifiableList(operationRepository.findAll(accountID));
        operationPrinter.print(unmodifiableOperations, computeBalance(unmodifiableOperations));
    }

    private BigDecimal computeBalance(List<Operation> operations) {
        BigDecimal currentBalance = BigDecimal.ZERO;

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
        }
        return currentBalance;
    }
}
