package fr.coding.bankaccount.rules;

import fr.coding.bankaccount.exceptions.AccountNotFoundException;
import fr.coding.bankaccount.exceptions.AmountNegativeException;
import fr.coding.bankaccount.models.Account;
import fr.coding.bankaccount.models.Operation;
import fr.coding.bankaccount.models.OperationType;
import fr.coding.bankaccount.repositories.AccountRepository;
import fr.coding.bankaccount.repositories.OperationRepository;
import fr.coding.bankaccount.services.DateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import static fr.coding.bankaccount.models.Amount.create;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DiscountCalculatorTest {
    private static final Long ACCOUNT_ID = 1L;
    private DiscountCalculator discountCalculator;
    private static final Instant mockedDate = Instant.ofEpochSecond(1558083897);

    @Mock
    private AccountRepository accountRepository;
    @Mock
    private OperationRepository operationRepository;
    @Mock
    private DateService dateService;

    @BeforeEach
    void setup() {
        discountCalculator = new DiscountCalculator(accountRepository, operationRepository, dateService);
    }

    @Test
    void should_throw_an_exception_when_calculating_discount_on_account_not_existing() throws AccountNotFoundException {
        AccountNotFoundException accountNotFoundException = new AccountNotFoundException(ACCOUNT_ID);
        when(accountRepository.find(ACCOUNT_ID)).thenThrow(accountNotFoundException);

        Throwable throwable = assertThrows(AccountNotFoundException.class, () ->
                discountCalculator.calculateDiscountPercentage(ACCOUNT_ID)
        );

        String messageExpected = "Account with id : " + ACCOUNT_ID + " doesn't exist";

        assertEquals(messageExpected, throwable.getMessage());
    }

    @Test
    void should_calculate_discount() throws AccountNotFoundException, AmountNegativeException {
        Account account = new Account("john", "wick", mockedDate);
        List<Operation> operations = Arrays.asList(
                Operation.create(ACCOUNT_ID, create("200"), OperationType.DEPOSIT, mockedDate),
                Operation.create(ACCOUNT_ID, create("300"), OperationType.WITHDRAWAL, mockedDate),
                Operation.create(ACCOUNT_ID, create("500"), OperationType.DEPOSIT, mockedDate),
                Operation.create(ACCOUNT_ID, create("1000"), OperationType.WITHDRAWAL, mockedDate)
        );

        when(dateService.getDate()).thenReturn(mockedDate);
        when(accountRepository.find(ACCOUNT_ID)).thenReturn(account);
        when(operationRepository.findAll(ACCOUNT_ID)).thenReturn(operations);

        BigDecimal output = discountCalculator.calculateDiscountPercentage(ACCOUNT_ID);
        BigDecimal expected = new BigDecimal("500");

        assertEquals(expected, output);
        verify(accountRepository, times(1)).find(ACCOUNT_ID);
        verify(operationRepository, times(1)).findAll(ACCOUNT_ID);
        verifyNoMoreInteractions(accountRepository);
        verifyNoMoreInteractions(operationRepository);
    }
}
