package fr.coding.bankaccount.services;

import fr.coding.bankaccount.exceptions.*;
import fr.coding.bankaccount.models.Account;
import fr.coding.bankaccount.models.Operation;
import fr.coding.bankaccount.models.OperationType;
import fr.coding.bankaccount.printers.OperationPrinter;
import fr.coding.bankaccount.repositories.AccountRepository;
import fr.coding.bankaccount.repositories.BeneficiaryRepository;
import fr.coding.bankaccount.repositories.OperationRepository;
import fr.coding.bankaccount.rules.DiscountCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static fr.coding.bankaccount.models.Amount.create;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {
    private static final Long ACCOUNT_HOLDER_ID = 1L;
    private static final Long ACCOUNT_BENEFICIARY_ID = 123L;
    private static final Instant mockedDate = Instant.ofEpochSecond(1558083897);

    private  InOrder inOrder;

    private AccountService accountService;
    @Mock
    private OperationRepository operationRepository;
    @Mock
    private BeneficiaryRepository beneficiaryRepository;
    @Mock
    private AccountRepository accountRepository;
    @Mock
    private OperationPrinter operationPrinter;
    @Mock
    private DiscountCalculator discountCalculator;
    @Mock
    private DateService dateService;

    @BeforeEach
    void setup() {
        accountService = new AccountService(operationRepository, beneficiaryRepository, accountRepository, dateService);
        inOrder = inOrder(operationRepository);
    }

    @Nested
    class OpenAccount {
        @Test
        void should_throw_exception_on_opening_when_account_already_exists() throws AccountAlreadyExistsExceptions {
            Account account = new Account("john", "wick", mockedDate);
            AccountAlreadyExistsExceptions accountAlreadyExistsExceptions = new AccountAlreadyExistsExceptions(ACCOUNT_HOLDER_ID);
            doThrow(accountAlreadyExistsExceptions).when(accountRepository).add(account);
            when(dateService.getDate()).thenReturn(mockedDate);

            Throwable throwable = assertThrows(AccountAlreadyExistsExceptions.class, () ->
                    accountService.open("john", "wick")
            );

            String messageExpected = "Account with id : " + ACCOUNT_HOLDER_ID + " already opened";

            assertEquals(messageExpected, throwable.getMessage());
            verifyZeroInteractions(accountRepository);
        }

        @Test
        void should_record_account_when_opening() throws AccountAlreadyExistsExceptions {
            Account account = new Account("john", "wick", mockedDate);
            when(dateService.getDate()).thenReturn(mockedDate);
            accountService.open("john", "wick");

            verify(accountRepository, times(1)).add(account);
            verifyNoMoreInteractions(accountRepository);
        }
    }

    @Nested
    class CloseAccount {
        @Test
        void should_throw_an_exception_on_opening_when_account_not_existing() throws AccountNotFoundException {
            AccountNotFoundException accountNotFoundException = new AccountNotFoundException(ACCOUNT_HOLDER_ID);
            doThrow(accountNotFoundException).when(accountRepository).remove(ACCOUNT_HOLDER_ID);

            Throwable throwable = assertThrows(AccountNotFoundException.class, () ->
                    accountService.close(ACCOUNT_HOLDER_ID)
            );

            String messageExpected = "Account with id : " + ACCOUNT_HOLDER_ID + " doesn't exist";

            assertEquals(messageExpected, throwable.getMessage());
        }

        @Test
        void should_delete_account_when_close() throws AccountNotFoundException {
            accountService.close(ACCOUNT_HOLDER_ID);
            verify(accountRepository, times(1)).remove(ACCOUNT_HOLDER_ID);
            verifyNoMoreInteractions(accountRepository);
        }
    }

    @Nested
    class DepositMoney {
        @Test
        void should_not_record_when_an_exception_occurred_on_deposit_negative_amount() {
            String amount = "-564";

            Throwable throwable = assertThrows(AmountNegativeException.class, () ->
                    accountService.deposit(ACCOUNT_BENEFICIARY_ID, amount)
            );

            String messageExpected = "The amount is a negative number (" + amount + ").";

            assertEquals(messageExpected, throwable.getMessage());
            verifyZeroInteractions(operationRepository);
        }

        @Test
        void should_throw_exception_on_deposit_when_account_doesnt_exist() throws AccountNotFoundException {
            String amount = "50";
            AccountNotFoundException accountNotFoundExceptionToThrow = new AccountNotFoundException(ACCOUNT_BENEFICIARY_ID);

            when(dateService.getDate()).thenReturn(mockedDate);
            doThrow(accountNotFoundExceptionToThrow).when(operationRepository).add(any());

            Throwable throwable = assertThrows(AccountNotFoundException.class, () ->
                    accountService.deposit(ACCOUNT_BENEFICIARY_ID, amount)
            );

            String messageExpected = "Account with id : " + ACCOUNT_BENEFICIARY_ID + " doesn't exist";

            assertEquals(messageExpected, throwable.getMessage());
            verifyZeroInteractions(operationRepository);
        }

        @Test
        void should_record_operation_when_deposit() throws AmountNegativeException, AccountNotFoundException {
            String amount = "50";
            when(dateService.getDate()).thenReturn(mockedDate);

            accountService.deposit(ACCOUNT_BENEFICIARY_ID, amount);

            Operation operationDeposit = Operation.create(ACCOUNT_BENEFICIARY_ID, create(amount), OperationType.DEPOSIT, mockedDate);
            verify(operationRepository, times(1)).add(operationDeposit);
            verifyNoMoreInteractions(operationRepository);
        }
    }

    @Nested
    class WithdrawMoney {
        @Test
        void should_not_record_when_an_exception_occurred_on_withdrawal_negative_value() {
            String amount = "-50";

            Throwable throwable = assertThrows(AmountNegativeException.class, () ->
                    accountService.withdraw(ACCOUNT_BENEFICIARY_ID, amount)
            );

            String messageExpected = "The amount is a negative number (" + amount + ").";

            assertEquals(messageExpected, throwable.getMessage());
            verifyZeroInteractions(operationRepository);
        }

        @Test
        void should_throw_exception_on_withdrawal_when_account_doesnt_exist() throws AccountNotFoundException {
            String amount = "50";
            AccountNotFoundException accountNotFoundExceptionToThrow = new AccountNotFoundException(ACCOUNT_BENEFICIARY_ID);

            when(operationRepository.findAll(ACCOUNT_BENEFICIARY_ID)).thenThrow(accountNotFoundExceptionToThrow);
            when(dateService.getDate()).thenReturn(mockedDate);

            Throwable throwable = assertThrows(AccountNotFoundException.class, () ->
                    accountService.withdraw(ACCOUNT_BENEFICIARY_ID, amount)
            );

            String messageExpected = "Account with id : " + ACCOUNT_BENEFICIARY_ID + " doesn't exist";

            assertEquals(messageExpected, throwable.getMessage());
            verifyZeroInteractions(operationRepository);
        }

        @Test
        void should_throw_exception_on_withdrawal_when_not_enough_money() throws AmountNegativeException, AccountNotFoundException {
            String depositAmount = "50";
            String withdrawalAmount = "51";

            when(dateService.getDate()).thenReturn(mockedDate);

            Operation depositOperation = Operation.create(ACCOUNT_BENEFICIARY_ID,  create(depositAmount), OperationType.DEPOSIT, mockedDate);

            List<Operation> operations = Collections.singletonList(depositOperation);

            when(operationRepository.findAll(ACCOUNT_BENEFICIARY_ID)).thenReturn(operations);

            Throwable throwable = assertThrows(NotEnoughMoneyOnAccountException.class, () ->
                    accountService.withdraw(ACCOUNT_BENEFICIARY_ID, withdrawalAmount)
            );

            String messageExpected = "Withdrawal impossible with amount : " + withdrawalAmount;

            assertEquals(messageExpected, throwable.getMessage());
            verifyNoMoreInteractions(operationRepository);
        }

        @Test
        void should_record_operation_when_withdrawal() throws AmountNegativeException, NotEnoughMoneyOnAccountException, AccountNotFoundException {
            String amount = "50";
            Operation operationDeposit = Operation.create(ACCOUNT_BENEFICIARY_ID,  create(amount), OperationType.DEPOSIT, mockedDate);
            List<Operation> operations = Collections.singletonList(operationDeposit);

            when(operationRepository.findAll(ACCOUNT_BENEFICIARY_ID)).thenReturn(operations);
            when(dateService.getDate()).thenReturn(mockedDate);

            accountService.withdraw(ACCOUNT_BENEFICIARY_ID, amount);

            Operation operationWithdrawal = Operation.create(ACCOUNT_BENEFICIARY_ID, create(amount), OperationType.WITHDRAWAL, mockedDate);

            verify(operationRepository, times(1)).add(operationWithdrawal);
            verifyNoMoreInteractions(operationRepository);
        }
    }

    @Nested
    class TransferMoney {
        @Test
        void should_not_record_when_an_exception_occurred_on_transfer_negative_value() throws AccountNotFoundException {
            String amount = "-50";
            when(beneficiaryRepository.findAll(ACCOUNT_HOLDER_ID)).thenReturn(Collections.singletonList(ACCOUNT_BENEFICIARY_ID));

            Throwable throwable = assertThrows(AmountNegativeException.class, () ->
                    accountService.transfer(ACCOUNT_HOLDER_ID, ACCOUNT_BENEFICIARY_ID, amount)
            );

            String messageExpected = "The amount is a negative number (" + amount + ").";

            assertEquals(messageExpected, throwable.getMessage());
            verifyZeroInteractions(operationRepository);
        }

        @Test
        void should_throw_exception_on_transfer_when_owner_account_doesnt_exist() throws AccountNotFoundException {
            String amount = "50";
            AccountNotFoundException accountNotFoundExceptionToThrow = new AccountNotFoundException(ACCOUNT_HOLDER_ID);

            when(dateService.getDate()).thenReturn(mockedDate);
            when(operationRepository.findAll(ACCOUNT_HOLDER_ID)).thenThrow(accountNotFoundExceptionToThrow);
            when(beneficiaryRepository.findAll(ACCOUNT_HOLDER_ID)).thenReturn(Collections.singletonList(ACCOUNT_BENEFICIARY_ID));

            Throwable throwable = assertThrows(AccountNotFoundException.class, () ->
                    accountService.transfer(ACCOUNT_HOLDER_ID, ACCOUNT_BENEFICIARY_ID, amount)
            );

            String messageExpected = "Account with id : " + ACCOUNT_HOLDER_ID + " doesn't exist";

            assertEquals(messageExpected, throwable.getMessage());
            verifyZeroInteractions(operationRepository);
        }

        @Test
        void should_throw_exception_on_transfer_when_beneficiary_account_doesnt_exist() throws AccountNotFoundException, AmountNegativeException {
            String amount = "50";
            List<Operation> beneficiaryOperations = Collections.singletonList(
                    Operation.create(ACCOUNT_HOLDER_ID, create(amount), OperationType.DEPOSIT, mockedDate)
            );

            AccountNotFoundException accountNotFoundExceptionToThrow = new AccountNotFoundException(ACCOUNT_BENEFICIARY_ID);

            when(dateService.getDate()).thenReturn(mockedDate);
            when(operationRepository.findAll(ACCOUNT_HOLDER_ID)).thenReturn(beneficiaryOperations);
            when(beneficiaryRepository.findAll(ACCOUNT_HOLDER_ID)).thenReturn(Collections.singletonList(ACCOUNT_BENEFICIARY_ID));
            doThrow(accountNotFoundExceptionToThrow).when(operationRepository).add(any());

            Throwable throwable = assertThrows(AccountNotFoundException.class, () ->
                    accountService.transfer(ACCOUNT_HOLDER_ID, ACCOUNT_BENEFICIARY_ID, amount)
            );

            String messageExpected = "Account with id : " + ACCOUNT_BENEFICIARY_ID + " doesn't exist";

            assertEquals(messageExpected, throwable.getMessage());
            verifyZeroInteractions(operationRepository);
        }

        @Test
        void should_throw_exception_on_transfer_when_when_not_enough_money_on_beneficiary_account() throws AmountNegativeException, AccountNotFoundException {
            String depositAmount = "50";
            String withdrawalAmount = "51";
            Operation depositOperation = Operation.create(ACCOUNT_BENEFICIARY_ID,  create(depositAmount), OperationType.DEPOSIT, mockedDate);

            when(dateService.getDate()).thenReturn(mockedDate);
            when(operationRepository.findAll(ACCOUNT_HOLDER_ID)).thenReturn(Collections.singletonList(depositOperation));
            when(beneficiaryRepository.findAll(ACCOUNT_HOLDER_ID)).thenReturn(Collections.singletonList(ACCOUNT_BENEFICIARY_ID));

            Throwable throwable = assertThrows(NotEnoughMoneyOnAccountException.class, () ->
                    accountService.transfer(ACCOUNT_HOLDER_ID, ACCOUNT_BENEFICIARY_ID, withdrawalAmount)
            );

            String messageExpected = "Withdrawal impossible with amount : " + withdrawalAmount;

            assertEquals(messageExpected, throwable.getMessage());
            verifyZeroInteractions(operationRepository);
        }

        @Test
        void should_throw_exception_when_beneficiary_unrecognized() {
            String amount = "51";

            Throwable throwable = assertThrows(BeneficiaryUnrecognizedException.class, () ->
                    accountService.transfer(ACCOUNT_HOLDER_ID, ACCOUNT_BENEFICIARY_ID, amount)
            );

            String messageExpected = "The account with id : " + ACCOUNT_BENEFICIARY_ID + " is not a beneficiary of account " + ACCOUNT_HOLDER_ID;

            assertEquals(messageExpected, throwable.getMessage());
            verifyZeroInteractions(operationRepository);
        }

        @Test
        void should_record_operation_on_transfer() throws AmountNegativeException, AccountNotFoundException, NotEnoughMoneyOnAccountException, BeneficiaryUnrecognizedException {
            String initialAmount = "100";
            String amountToTransfer = "50";

            Operation depositOperation = Operation.create(ACCOUNT_HOLDER_ID, create(initialAmount), OperationType.DEPOSIT, mockedDate);
            Operation withdrawOperationOnOwner = Operation.create(ACCOUNT_HOLDER_ID, create(amountToTransfer), OperationType.WITHDRAWAL, mockedDate);
            Operation depositOperationOnBeneficiary = Operation.create(ACCOUNT_BENEFICIARY_ID, create(amountToTransfer), OperationType.DEPOSIT, mockedDate);

            when(dateService.getDate()).thenReturn(mockedDate);
            when(operationRepository.findAll(ACCOUNT_HOLDER_ID)).thenReturn(Collections.singletonList(depositOperation));
            when(beneficiaryRepository.findAll(ACCOUNT_HOLDER_ID)).thenReturn(Collections.singletonList(ACCOUNT_BENEFICIARY_ID));

            accountService.transfer(ACCOUNT_HOLDER_ID, ACCOUNT_BENEFICIARY_ID, amountToTransfer);

            inOrder.verify(operationRepository, times(1)).add(withdrawOperationOnOwner);
            inOrder.verify(operationRepository, times(1)).add(depositOperationOnBeneficiary);

            verifyNoMoreInteractions(operationRepository);
        }
    }

    @Nested
    class Beneficiary {
        @Test
        void should_throw_exception_when_account_holder_doesnt_exist() throws AccountNotFoundException {
            AccountNotFoundException accountNotFoundException = new AccountNotFoundException(ACCOUNT_HOLDER_ID);
            doThrow(accountNotFoundException).when(beneficiaryRepository).add(ACCOUNT_HOLDER_ID, ACCOUNT_BENEFICIARY_ID);

            Throwable throwable = assertThrows(AccountNotFoundException.class, () ->
                    accountService.attachBeneficiary(ACCOUNT_HOLDER_ID, ACCOUNT_BENEFICIARY_ID));

            String messageExpected = "Account with id : " + ACCOUNT_HOLDER_ID + " doesn't exist";;

            assertEquals(messageExpected, throwable.getMessage());
            verifyZeroInteractions(beneficiaryRepository);
        }

        @Test
        void should_throw_exception_when_account_beneficiary_doest_exist() throws AccountNotFoundException {
            AccountNotFoundException accountNotFoundException = new AccountNotFoundException(ACCOUNT_BENEFICIARY_ID);
            doThrow(accountNotFoundException).when(beneficiaryRepository).add(ACCOUNT_HOLDER_ID, ACCOUNT_BENEFICIARY_ID);

            Throwable throwable = assertThrows(AccountNotFoundException.class, () ->
                    accountService.attachBeneficiary(ACCOUNT_HOLDER_ID, ACCOUNT_BENEFICIARY_ID));

            String messageExpected = "Account with id : " + ACCOUNT_BENEFICIARY_ID + " doesn't exist";;

            assertEquals(messageExpected, throwable.getMessage());
            verifyZeroInteractions(beneficiaryRepository);
        }

        @Test
        void should_add_beneficiary_when_attach() throws AccountNotFoundException {
            accountService.attachBeneficiary(ACCOUNT_HOLDER_ID, ACCOUNT_BENEFICIARY_ID);
            verify(beneficiaryRepository, times(1)) .add(ACCOUNT_HOLDER_ID, ACCOUNT_BENEFICIARY_ID);
            verifyNoMoreInteractions(beneficiaryRepository);
        }
    }

    @Nested
    class ApplyDiscount {
        @Test
        void should_apply_discount_on_account_when_not_zero() throws AccountNotFoundException, AmountNegativeException {
            BigDecimal discount = new BigDecimal("500");
            when(discountCalculator.calculateDiscountPercentage(ACCOUNT_HOLDER_ID)).thenReturn(discount);
            when(dateService.getDate()).thenReturn(mockedDate);
            Operation depositOperation = Operation.create(ACCOUNT_HOLDER_ID, create(discount.toString()), OperationType.DEPOSIT, dateService.getDate());

            accountService.applyDiscount(discountCalculator, ACCOUNT_HOLDER_ID);
            verify(operationRepository, times(1)).add(depositOperation);
            verifyNoMoreInteractions(operationRepository);
        }

        @Test
        void should_ot_apply_discount_on_account_when_zero() throws AccountNotFoundException, AmountNegativeException {
            BigDecimal discount = BigDecimal.ZERO;
            when(discountCalculator.calculateDiscountPercentage(ACCOUNT_HOLDER_ID)).thenReturn(discount);

            accountService.applyDiscount(discountCalculator, ACCOUNT_HOLDER_ID);
            verifyZeroInteractions(operationRepository);
        }
    }

    @Nested
    class CheckOperations {
        @Test
        void should_print_all_operation_reported() throws AmountNegativeException, AccountNotFoundException {
            BigDecimal balance = new BigDecimal(150);
            String amount = "50";

            List<Operation> operations = Arrays.asList(
                    Operation.create(ACCOUNT_BENEFICIARY_ID, create(amount), OperationType.DEPOSIT, mockedDate),
                    Operation.create(ACCOUNT_BENEFICIARY_ID, create(amount), OperationType.DEPOSIT, mockedDate),
                    Operation.create(ACCOUNT_BENEFICIARY_ID, create(amount), OperationType.DEPOSIT, mockedDate)
            );
            when(operationRepository.findAll(ACCOUNT_BENEFICIARY_ID)).thenReturn(operations);

            accountService.printStatement(operationPrinter, ACCOUNT_BENEFICIARY_ID);

            verify(operationRepository, times(1)).findAll(ACCOUNT_BENEFICIARY_ID);
            verify(operationPrinter, times(1)).print(operations, balance);
            verifyNoMoreInteractions(operationRepository);
            verifyNoMoreInteractions(operationPrinter);
        }

        @Test
        void should_throw_exception_on_printing_when_account_doesnt_exist() throws AccountNotFoundException {
            final AccountNotFoundException accountNotFoundException = new AccountNotFoundException(ACCOUNT_BENEFICIARY_ID);

            doThrow(accountNotFoundException).when(operationRepository).findAll(ACCOUNT_BENEFICIARY_ID);

            Throwable throwable = assertThrows(AccountNotFoundException.class, () ->
                    accountService.printStatement(operationPrinter, ACCOUNT_BENEFICIARY_ID)
            );

            String messageExpected = "Account with id : " + ACCOUNT_BENEFICIARY_ID + " doesn't exist";

            assertEquals(messageExpected, throwable.getMessage());
            verifyZeroInteractions(operationRepository);
            verifyZeroInteractions(operationPrinter);
        }
    }
}
