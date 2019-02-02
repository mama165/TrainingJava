package DynamicProgramming;

import CrackingTheCodingInterview.DynamicProgramming.Fibonacci;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FibonacciTest {
    private Fibonacci fibonacci;

    @BeforeEach
    public void setup() {
        fibonacci = new Fibonacci();
    }

    private static Stream<Arguments> createValues() {
        int value = 5;
        int expected = 5;

        int valueSecond = 10;
        int expectedSecond = 55;

        return Stream.of(
                Arguments.of(value, expected),
                Arguments.of(valueSecond, expectedSecond));
    }

    @ParameterizedTest
    @MethodSource("createValues")
    @DisplayName("Test with recursive programming")
    public void testFibonacciRecursive(int value, int expected) {
        int output = fibonacci.fibonacciRecursive(value);
        assertEquals(expected, output);
    }

    @ParameterizedTest
    @MethodSource("createValues")
    @DisplayName("Test with dynamic programming")
    public void testFibonacci(int value, int expected) {
        int output = fibonacci.fibonacci(value);
        assertEquals(expected, output);
    }
}
