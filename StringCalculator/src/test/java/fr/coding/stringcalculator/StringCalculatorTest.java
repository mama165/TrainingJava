package fr.coding.stringcalculator;

import fr.coding.stringcalculator.exceptions.NegativeNumberException;
import fr.coding.stringcalculator.features.StringCalculator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StringCalculatorTest {
    private final StringCalculator stringCalculator = new StringCalculator();

    @Test
    void should_return_default_integer_when_empty_value_as_input() throws NegativeNumberException {
        String input = "";
        int expected = 0;
        int output = stringCalculator.add(input);
        assertEquals(expected, output);
    }

    @ParameterizedTest
    @CsvSource({
            "'1',1",
            "'2',2",
            "'3',3",
            "'4',4",
            "'5',5"
    })
    void should_return_the_value_when_only_one_value_as_input(String input, int expected) throws NegativeNumberException {
        int output = stringCalculator.add(input);
        assertEquals(expected, output);
    }

    @ParameterizedTest
    @CsvSource({
            "'1,2,3',6",
            "'5,3',8",
            "'10,33,5,40',88"
    })
    void should_return_sum_of_values_when_unknown_input(String input, int expected) throws NegativeNumberException {
        int output = stringCalculator.add(input);
        assertEquals(expected, output);
    }

    @ParameterizedTest
    @CsvSource({
            "'1\n2,3',6",
            "'16\n76\n2',94",
            "'4\n4\n9',17"
    })
    void should_return_sum_when_input_contains_default_delimiter(String input, int expected) throws NegativeNumberException {
        int output = stringCalculator.add(input);
        assertEquals(expected, output);
    }

    @ParameterizedTest
    @CsvSource({
            "'//;\n1,2;3',6",
            "'//k\n3,9k5',17",
            "'//u\n8,9u3u4u4',28",
            "'//o\n89o3,4o4',100",
    })
    void should_return_sum_when_input_contains_custom_delimiter(String input, int expected) throws NegativeNumberException {
        int output = stringCalculator.add(input);
        assertEquals(expected, output);
    }

    @ParameterizedTest
    @CsvSource({
            "'-5,2,-10,9', 'Les nombres négatifs ne sont pas autorisés : -5,-10'",
            "'-39,9','Les nombres négatifs ne sont pas autorisés'",
            "'5,-6,-87','Les nombres négatifs ne sont pas autorisés : -6,-87'"
    })
    void should_throw_exception_when_found_negative_value(String input, String expectedMessage) {
        Throwable exception = assertThrows(NegativeNumberException.class, () ->
                stringCalculator.add(input)
        );

        assertEquals(expectedMessage, exception.getMessage());
    }

    @ParameterizedTest
    @CsvSource({
            "'7,1000',1007",
            "'76,5590,4', 80",
            "'100,100,100,198384', 300"
    })
    void should_ignore_inputs_when_greater_than_thousand(String input, int expected) throws NegativeNumberException {
        int output = stringCalculator.add(input);
        assertEquals(expected, output);
    }
}