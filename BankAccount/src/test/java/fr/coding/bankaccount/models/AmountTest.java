package fr.coding.bankaccount.models;

import fr.coding.bankaccount.exceptions.AmountNegativeException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AmountTest {
    @ParameterizedTest
    @MethodSource("provideAmountsForEquals")
    void should_validate_equals_operations(Amount a1, Amount a2) {
        assertEquals(a1, a2);
    }

    private static Stream<Arguments> provideAmountsForEquals() throws AmountNegativeException {
        return Stream.of(
                Arguments.of(
                        Amount.create("6645"),
                        Amount.create("6645")
                ),
                Arguments.of(
                        Amount.create("3453"),
                        Amount.create("3453")
                ),
                Arguments.of(
                        Amount.create("4"),
                        Amount.create("4")
                ),
                Arguments.of(
                        Amount.create("963"),
                        Amount.create("963")
                )
        );
    }

    @ParameterizedTest
    @CsvSource({
        "'-677'",
        "'-97'",
        "'-46'",
        "'-677'",
        "'-7544'",
        "'-9853335'"
    })
    void should_throw_exception_when_amount_negative(String amount) {
        Throwable throwable = assertThrows(AmountNegativeException.class, () ->
                Amount.create(amount)
        );

        String expected = "The amount is a negative number (" + amount + ").";
        assertEquals(expected, throwable.getMessage());
    }

    @Test
    void should_throw_exception_when_null_argument() {
        Throwable throwable = assertThrows(NullPointerException.class, () ->
                Amount.create(null)
        );

        String expected = "The amount is null";
        assertEquals(expected, throwable.getMessage());
    }
}
