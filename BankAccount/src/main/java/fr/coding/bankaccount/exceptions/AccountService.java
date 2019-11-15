package fr.coding.bankaccount.exceptions;

import fr.coding.bankaccount.features.*;
import fr.coding.bankaccount.models.Account;
import fr.coding.bankaccount.models.Amount;
import fr.coding.bankaccount.models.Operation;
import fr.coding.bankaccount.models.OperationType;
import fr.coding.bankaccount.printers.OperationPrinter;
import fr.coding.bankaccount.repositories.AccountRepository;
import fr.coding.bankaccount.repositories.BeneficiaryRepository;
import fr.coding.bankaccount.repositories.OperationRepository;
import fr.coding.bankaccount.services.DateService;
import fr.coding.bankaccount.features.IAccount;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

public final class AccountService implements ITransfer, IDeposit, IWithdraw, IBeneficiary, IAccount, IReport {
    private final OperationRepository operationRepository;
    private final BeneficiaryRepository beneficiaryRepository;
    private final AccountRepository accountRepository;
    private final DateService dateService;

    public AccountService(OperationRepository operationRepository, BeneficiaryRepository beneficiaryRepository, AccountRepository accountRepository, DateService dateService) {
        this.operationRepository = operationRepository;
        this.beneficiaryRepository = beneficiaryRepository;
        this.accountRepository = accountRepository;
        this.dateService = dateService;
    }

    @Override
    public void open(String firstName, String lastName) throws AccountAlreadyExistsExceptions {
        Account account = new Account(firstName, lastName, dateService.getDate());
        accountRepository.add(account);
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
    public void transfer(Long accountHolderID, Long accountBeneficiaryID, String value) throws AmountNegativeException, AccountNotFoundException, NotEnoughMoneyOnAccountException, BeneficiaryUnrecognizedException {
        if (accountHolderID == null || accountBeneficiaryID == null) throw new NullPointerException();

        List<Long> beneficiaryIDs = beneficiaryRepository.findAll(accountHolderID);
        List<Long> unmodifiableBeneficiaryIDs = Collections.unmodifiableList(beneficiaryIDs);

        if(!unmodifiableBeneficiaryIDs.contains(accountBeneficiaryID)) {
            throw new BeneficiaryUnrecognizedException(accountHolderID, accountBeneficiaryID);
        }

        this.withdraw(accountHolderID, value);
        this.deposit(accountBeneficiaryID, value);
    }

    @Override
    public void attachBeneficiary(Long accountHolderID, Long accountBeneficiaryID)  throws AccountNotFoundException{
        if (accountHolderID == null || accountBeneficiaryID == null) throw new NullPointerException();

        beneficiaryRepository.add(accountHolderID, accountBeneficiaryID);
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
