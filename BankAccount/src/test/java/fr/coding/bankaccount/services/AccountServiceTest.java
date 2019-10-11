package fr.coding.bankaccount.services;

import fr.coding.bankaccount.exceptions.AccountNotFoundException;
import fr.coding.bankaccount.exceptions.AmountNegativeException;
import fr.coding.bankaccount.exceptions.NotEnoughMoneyOnAccountException;
import fr.coding.bankaccount.models.Operation;
import fr.coding.bankaccount.models.OperationType;
import fr.coding.bankaccount.printers.OperationPrinter;
import fr.coding.bankaccount.repositories.OperationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {
    private static final Long OWNER_ID = 1L;
    private static final Long ACCOUNT_ID = 123L;
    private static final Instant mockedDate = Instant.ofEpochSecond(1558083897);

    private AccountService accountService;
    @Mock
    private OperationRepository operationRepository;
    @Mock
    private OperationPrinter operationPrinter;
    @Mock
    private DateService dateService;

    @BeforeEach
    void setup() {
        accountService = new AccountService(operationRepository, dateService);
    }

    @Nested
    class DepositMoney {
        @Test
        void should_not_record_when_an_exception_occured_on_deposit_negative_amount() {
            String amount = "-564";

            Throwable throwable = assertThrows(AmountNegativeException.class, () ->
                    accountService.deposit(ACCOUNT_ID, amount)
            );

            String messageExpected = "The amount is a negative number (" + amount + ").";

            assertEquals(messageExpected, throwable.getMessage());
            verifyZeroInteractions(operationRepository);
        }

        @Test
        void should_throw_exception_on_deposit_when_account_doesnt_exist() throws AccountNotFoundException {
            String amount = "50";
            AccountNotFoundException accountNotFoundExceptionToThrow = new AccountNotFoundException(ACCOUNT_ID);

            when(dateService.getDate()).thenReturn(mockedDate);
            doThrow(accountNotFoundExceptionToThrow).when(operationRepository).add(any());

            Throwable throwable = assertThrows(AccountNotFoundException.class, () ->
                    accountService.deposit(ACCOUNT_ID, amount)
            );

            String messageExpected = "Account with id : " + ACCOUNT_ID + " doesn't exist";

            assertEquals(messageExpected, throwable.getMessage());
            verifyZeroInteractions(operationRepository);
        }

        @Test
        void should_record_operation_when_deposit() throws AmountNegativeException, AccountNotFoundException {
            String amount = "50";
            when(dateService.getDate()).thenReturn(mockedDate);

            accountService.deposit(ACCOUNT_ID, amount);

            Operation operationDeposit = Operation.create(ACCOUNT_ID, create(amount), OperationType.DEPOSIT, mockedDate);
            verify(operationRepository, times(1)).add(operationDeposit);
            verifyNoMoreInteractions(operationRepository);
        }
    }

    @Nested
    class WithdrawMoney {
        @Test
        void should_not_record_when_an_exception_occured_on_withdrawal_negative_value() {
            String amount = "-50";

            Throwable throwable = assertThrows(AmountNegativeException.class, () ->
                    accountService.withdraw(ACCOUNT_ID, amount)
            );

            String messageExpected = "The amount is a negative number (" + amount + ").";

            assertEquals(messageExpected, throwable.getMessage());
            verifyZeroInteractions(operationRepository);
        }

        @Test
        void should_throw_exception_on_withdrawal_when_account_doesnt_exist() throws AccountNotFoundException {
            String amount = "50";
            AccountNotFoundException accountNotFoundExceptionToThrow = new AccountNotFoundException(ACCOUNT_ID);

            when(operationRepository.findAll(ACCOUNT_ID)).thenThrow(accountNotFoundExceptionToThrow);
            when(dateService.getDate()).thenReturn(mockedDate);

            Throwable throwable = assertThrows(AccountNotFoundException.class, () ->
                    accountService.withdraw(ACCOUNT_ID, amount)
            );

            String messageExpected = "Account with id : " + ACCOUNT_ID + " doesn't exist";

            assertEquals(messageExpected, throwable.getMessage());
            verifyZeroInteractions(operationRepository);
        }

        @Test
        void should_throw_exception_on_withdrawal_when_not_enough_money() throws AmountNegativeException, AccountNotFoundException {
            String depositAmount = "50";
            String withdrawalAmount = "51";

            when(dateService.getDate()).thenReturn(mockedDate);

            Operation depositOperation = Operation.create(ACCOUNT_ID,  create(depositAmount), OperationType.DEPOSIT, mockedDate);

            List<Operation> operations = Collections.singletonList(depositOperation);

            when(operationRepository.findAll(ACCOUNT_ID)).thenReturn(operations);

            Throwable throwable = assertThrows(NotEnoughMoneyOnAccountException.class, () ->
                    accountService.withdraw(ACCOUNT_ID, withdrawalAmount)
            );

            String messageExpected = "Withdrawal impossible with amount : " + withdrawalAmount;

            assertEquals(messageExpected, throwable.getMessage());
            verifyNoMoreInteractions(operationRepository);
        }

        @Test
        void should_record_operation_when_withdrawal() throws AmountNegativeException, NotEnoughMoneyOnAccountException, AccountNotFoundException {
            String amount = "50";
            Operation operationDeposit = Operation.create(ACCOUNT_ID,  create(amount), OperationType.DEPOSIT, mockedDate);
            List<Operation> operations = Collections.singletonList(operationDeposit);

            when(operationRepository.findAll(ACCOUNT_ID)).thenReturn(operations);
            when(dateService.getDate()).thenReturn(mockedDate);

            accountService.withdraw(ACCOUNT_ID, amount);

            Operation operationWithdrawal = Operation.create(ACCOUNT_ID, create(amount), OperationType.WITHDRAWAL, mockedDate);

            verify(operationRepository, times(1)).add(operationWithdrawal);
            verifyNoMoreInteractions(operationRepository);
        }
    }

    @Nested
    class TransferMoney {
        @Test
        void should_not_record_when_an_exception_occured_on_transfer_negative_value() {
            String amount = "-50";

            Throwable throwable = assertThrows(AmountNegativeException.class, () ->
                    accountService.transfer(OWNER_ID, ACCOUNT_ID, amount)
            );

            String messageExpected = "The amount is a negative number (" + amount + ").";

            assertEquals(messageExpected, throwable.getMessage());
            verifyZeroInteractions(operationRepository);
        }

        @Test
        void should_throw_exception_on_transfer_when_owner_account_doesnt_exist() throws AccountNotFoundException {
            String amount = "50";
            AccountNotFoundException accountNotFoundExceptionToThrow = new AccountNotFoundException(OWNER_ID);

            when(dateService.getDate()).thenReturn(mockedDate);
            when(operationRepository.findAll(OWNER_ID)).thenThrow(accountNotFoundExceptionToThrow);

            Throwable throwable = assertThrows(AccountNotFoundException.class, () ->
                    accountService.transfer(OWNER_ID, ACCOUNT_ID, amount)
            );

            String messageExpected = "Account with id : " + OWNER_ID + " doesn't exist";

            assertEquals(messageExpected, throwable.getMessage());
            verifyZeroInteractions(operationRepository);
        }

        @Test
        void should_throw_exception_on_transfer_when_beneficiary_account_doesnt_exist() throws AccountNotFoundException, AmountNegativeException {
            String amount = "50";
            List<Operation> beneficiaryOperations = Collections.singletonList(
                    Operation.create(OWNER_ID, create(amount), OperationType.DEPOSIT, mockedDate)
            );

            AccountNotFoundException accountNotFoundExceptionToThrow = new AccountNotFoundException(ACCOUNT_ID);

            when(dateService.getDate()).thenReturn(mockedDate);
            when(operationRepository.findAll(OWNER_ID)).thenReturn(beneficiaryOperations);
            doThrow(accountNotFoundExceptionToThrow).when(operationRepository).add(any());

            Throwable throwable = assertThrows(AccountNotFoundException.class, () ->
                    accountService.transfer(OWNER_ID, ACCOUNT_ID, amount)
            );

            String messageExpected = "Account with id : " + ACCOUNT_ID + " doesn't exist";

            assertEquals(messageExpected, throwable.getMessage());
            verifyZeroInteractions(operationRepository);
        }

        @Test
        void should_throw_exception_on_transfer_when_when_not_enough_money_on_beneficiary_account() throws AmountNegativeException, AccountNotFoundException {
            String depositAmount = "50";
            String withdrawalAmount = "51";
            Operation depositOperation = Operation.create(ACCOUNT_ID,  create(depositAmount), OperationType.DEPOSIT, mockedDate);

            when(dateService.getDate()).thenReturn(mockedDate);
            when(operationRepository.findAll(OWNER_ID)).thenReturn(Collections.singletonList(depositOperation));

            Throwable throwable = assertThrows(NotEnoughMoneyOnAccountException.class, () ->
                    accountService.transfer(OWNER_ID, ACCOUNT_ID, withdrawalAmount)
            );

            String messageExpected = "Withdrawal impossible with amount : " + withdrawalAmount;

            assertEquals(messageExpected, throwable.getMessage());
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
                    Operation.create(ACCOUNT_ID, create(amount), OperationType.DEPOSIT, mockedDate),
                    Operation.create(ACCOUNT_ID, create(amount), OperationType.DEPOSIT, mockedDate),
                    Operation.create(ACCOUNT_ID, create(amount), OperationType.DEPOSIT, mockedDate)
            );
            when(operationRepository.findAll(ACCOUNT_ID)).thenReturn(operations);

            accountService.printStatement(operationPrinter, ACCOUNT_ID);

            verify(operationRepository, times(1)).findAll(ACCOUNT_ID);
            verify(operationPrinter, times(1)).print(operations, balance);
            verifyNoMoreInteractions(operationRepository);
            verifyNoMoreInteractions(operationPrinter);
        }

        @Test
        void should_throw_exception_on_printing_when_account_doesnt_exist() throws AccountNotFoundException {
            doThrow(AccountNotFoundException.class).when(operationRepository).findAll(any());

            assertThrows(AccountNotFoundException.class, () ->
                    accountService.printStatement(operationPrinter, ACCOUNT_ID)
            );
            verifyZeroInteractions(operationRepository);
        }
    }
}
