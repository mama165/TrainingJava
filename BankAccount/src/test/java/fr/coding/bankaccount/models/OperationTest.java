package fr.coding.bankaccount.models;

import fr.coding.bankaccount.exceptions.AmountNegativeException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.Instant;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OperationTest {
    @ParameterizedTest
    @MethodSource("provideOperations")
    void should_validate_operation_creation(Long accountID, Amount amount, OperationType operationType, Instant date) {
        Operation operation = Operation.create(accountID, amount, operationType, date);

        assertAll(() -> {
            assertEquals(accountID, operation.getAccountID());
            assertEquals(amount.getValue(), operation.getAmount());
            assertEquals(operationType, operation.getOperationType());
            assertEquals(date, operation.getTime());
        });
    }

    private static Stream<Arguments> provideOperations() throws AmountNegativeException {
        return Stream.of(
                Arguments.of(123L, Amount.create("0"), OperationType.DEPOSIT, Instant.ofEpochSecond(1424080802)),
                Arguments.of(54L, Amount.create("0"), OperationType.DEPOSIT, Instant.ofEpochSecond(1424080802)),
                Arguments.of(78L, Amount.create("10"), OperationType.WITHDRAWAL, Instant.ofEpochSecond(1424080802)),
                Arguments.of(795L, Amount.create("366"), OperationType.WITHDRAWAL, Instant.ofEpochSecond(1424080802)),
                Arguments.of(645L, Amount.create("6546"), OperationType.DEPOSIT, Instant.ofEpochSecond(1424080802))
        );
    }

    @ParameterizedTest
    @MethodSource("provideOperationsForEquals")
    void should_validate_equals_operations(Operation o1, Operation o2) {
        assertEquals(o1, o2);
    }

    private static Stream<Arguments> provideOperationsForEquals() throws AmountNegativeException {
        Instant d1 = Instant.ofEpochSecond(1424080802);
        Instant d2 = Instant.ofEpochSecond(1455616802);
        Instant d3 = Instant.ofEpochSecond(1487239202);
        Instant d4 = Instant.ofEpochSecond(1518775202);

        return Stream.of(
                Arguments.of(
                        Operation.create(123L, Amount.create("0"), OperationType.DEPOSIT, d1),
                        Operation.create(123L,  Amount.create("0"), OperationType.DEPOSIT, d1)
                ),
                Arguments.of(
                        Operation.create(56L,  Amount.create("1"), OperationType.DEPOSIT, d2),
                        Operation.create(56L,  Amount.create("1"), OperationType.DEPOSIT, d2)
                ),
                Arguments.of(
                        Operation.create(865L,  Amount.create("10"), OperationType.WITHDRAWAL, d3),
                        Operation.create(865L,  Amount.create("10"), OperationType.WITHDRAWAL, d3)
                ),
                Arguments.of(
                        Operation.create(40984L,  Amount.create("0"), OperationType.DEPOSIT, d4),
                        Operation.create(40984L,  Amount.create("0"), OperationType.DEPOSIT, d4)
                )
        );
    }

    @ParameterizedTest
    @MethodSource("provideOperationsForNotEquals")
    void should_validate_not_equal(Operation o1, Operation o2) {
        assertNotEquals(o1, o2);
    }

    private static Stream<Arguments> provideOperationsForNotEquals() throws AmountNegativeException {
        Instant d1 = Instant.ofEpochSecond(1424080802);
        Instant d2 = Instant.ofEpochSecond(1455616802);
        Instant d3 = Instant.ofEpochSecond(1487239202);
        Instant d4 = Instant.ofEpochSecond(1518775202);

        return Stream.of(
                Arguments.of(
                        Operation.create(123L, Amount.create("0"), OperationType.DEPOSIT, d1),
                        Operation.create(45L, Amount.create("0"), OperationType.DEPOSIT, d1)
                ),
                Arguments.of(
                        Operation.create(56L, Amount.create("0"), OperationType.DEPOSIT, d2),
                        Operation.create(56L, Amount.create("1"), OperationType.DEPOSIT, d2)
                ),
                Arguments.of(
                        Operation.create(865L, Amount.create("10"), OperationType.DEPOSIT, d3),
                        Operation.create(865L, Amount.create("1"), OperationType.WITHDRAWAL, d3)
                ),
                Arguments.of(
                        Operation.create(40984L, Amount.create("0"), OperationType.DEPOSIT, d4),
                        Operation.create(40984L, Amount.create("500"), OperationType.DEPOSIT, d1)
                )
        );
    }

    @ParameterizedTest
    @MethodSource("provideOperationsWithNullArguments")
    void should_throw_exception_when_null_argument_on_operation(Long accountID, Amount amount, OperationType operationType, Instant date, String exceptionMessageExpected) {
        Throwable throwable = assertThrows(IllegalArgumentException.class, () ->
                Operation.create(accountID, amount, operationType, date)
        );

        assertEquals(exceptionMessageExpected, throwable.getMessage());
    }

    private static Stream<Arguments> provideOperationsWithNullArguments() throws AmountNegativeException {
        return Stream.of(
                Arguments.of(null, Amount.create("0"), OperationType.DEPOSIT, Instant.ofEpochSecond(1424080802), "Multiple illegal arguments : accountID"),
                Arguments.of(78L, Amount.create("10"), null, Instant.ofEpochSecond(1424080802), "Multiple illegal arguments : operationType"),
                Arguments.of(795L, Amount.create("366"), OperationType.WITHDRAWAL, null, "Multiple illegal arguments : time")
        );
    }
}
