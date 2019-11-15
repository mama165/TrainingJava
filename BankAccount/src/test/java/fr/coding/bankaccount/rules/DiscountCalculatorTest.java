package fr.coding.bankaccount.rules;

import fr.coding.bankaccount.exceptions.AccountNotFoundException;
import fr.coding.bankaccount.exceptions.AmountNegativeException;
import fr.coding.bankaccount.models.Amount;
import fr.coding.bankaccount.models.Operation;
import fr.coding.bankaccount.models.OperationType;
import fr.coding.bankaccount.repositories.OperationRepository;
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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DiscountCalculatorTest {
    private static final Long ACCOUNT_ID = 1L;
    private DiscountCalculator discountCalculator;
    @Mock
    private OperationRepository operationRepository;
    private static final Instant mockedDate = Instant.ofEpochSecond(1558083897);


    @BeforeEach
    void setup() {
        discountCalculator = new DiscountCalculator(operationRepository);
    }

    @Test
    void should_throw_an_exception_when_calculating_discount_on_account_not_existing() throws AccountNotFoundException {
        AccountNotFoundException accountNotFoundException = new AccountNotFoundException(ACCOUNT_ID);
        when(operationRepository.findAll(ACCOUNT_ID)).thenThrow(accountNotFoundException);

        Throwable throwable = assertThrows(AccountNotFoundException.class, () ->
                discountCalculator.calculateDiscountPercentage(ACCOUNT_ID)
        );

        String messageExpected = "Account with id : " + ACCOUNT_ID + " doesn't exist";

        assertEquals(messageExpected, throwable.getMessage());
    }

    @Test
    void should_calculate_discount() throws AccountNotFoundException, AmountNegativeException {
        List<Operation> operations = Arrays.asList(
                Operation.create(ACCOUNT_ID, create("200"), OperationType.DEPOSIT, mockedDate),
                Operation.create(ACCOUNT_ID, create("300"), OperationType.WITHDRAWAL, mockedDate),
                Operation.create(ACCOUNT_ID, create("500"), OperationType.DEPOSIT, mockedDate)
        );

        BigDecimal output = discountCalculator.calculateDiscountPercentage(ACCOUNT_ID);
        BigDecimal expected = BigDecimal.TEN;

        assertEquals(expected, output);
    }
}
