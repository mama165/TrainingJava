package fr.coding.bankaccount.formatters;

import fr.coding.bankaccount.exceptions.AmountNegativeException;
import fr.coding.bankaccount.models.Amount;
import fr.coding.bankaccount.models.Operation;
import fr.coding.bankaccount.models.OperationType;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static java.lang.System.lineSeparator;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TextOperationFormatterTest {
    private final TextOperationFormatter textOperationFormatter = new TextOperationFormatter();

    @ParameterizedTest
    @MethodSource("provide_operations")
    void should_return_formatted_operation(List<Operation> operationsInput, BigDecimal balance, String expected) {
        String output = textOperationFormatter.format(operationsInput, balance);
        assertEquals(expected, output);
    }

    private static Stream<Arguments> provide_operations() throws AmountNegativeException {
        Instant d1 = Instant.ofEpochSecond(1424080802);
        Instant d2 = Instant.ofEpochSecond(1455616802);
        Instant d3 = Instant.ofEpochSecond(1487239202);
        Instant d4 = Instant.ofEpochSecond(1518775202);

        return Stream.of(
                Arguments.of(
                        Collections.emptyList(), BigDecimal.TEN, ""
                ),
                Arguments.of(
                        null, BigDecimal.ZERO, ""
                ),
                Arguments.of(
                        Arrays.asList(
                                Operation.create(123L, Amount.create("100"), OperationType.DEPOSIT, d1),
                                Operation.create(123L, Amount.create("78"), OperationType.WITHDRAWAL, d2),
                                Operation.create(123L, Amount.create("98"), OperationType.DEPOSIT, d3),
                                Operation.create(123L, Amount.create("45"), OperationType.DEPOSIT, d4)
                        ),
                        new BigDecimal(165),
                        "| AccountID : 123, Balance : 165" + lineSeparator() +
                                "| Date : 2015-02-16, Amount : 100, Operation : Deposit" + lineSeparator() +
                                "| Date : 2016-02-16, Amount : 78, Operation : Withdrawal" + lineSeparator() +
                                "| Date : 2017-02-16, Amount : 98, Operation : Deposit" + lineSeparator() +
                                "| Date : 2018-02-16, Amount : 45, Operation : Deposit" + lineSeparator()
                )
        );
    }
}
