package DynamicProgramming;

import CrackingTheCodingInterview.DynamicProgramming.Fibonacci;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class FibonacciTest {
    private int value;
    private int expected;
    private Fibonacci fibonacci;

    public FibonacciTest(int value, int expected) {
        this.value = value;
        this.expected = expected;
    }

    @Before
    public void setup() {
        fibonacci = new Fibonacci();
    }

    @Parameters
    public static Collection<Object[]> valuesSamples() {
        int value = 5;
        int expected = 5;

        int valueSecond = 10;
        int expectedSecond = 55;

        return Arrays.asList(new Object[][]{
                {value, expected},
                {valueSecond, expectedSecond}
        });
    }

    @Test
    @DisplayName("Test with recursive programming")
    public void testFibonacciRecursive() {
        int output = fibonacci.fibonacciRecursive(value);
        Assert.assertEquals(expected, output);
    }

    @Test
    @DisplayName("Test with dynamic programming")
    public void testFibonacci() {
        int output = fibonacci.fibonacci(value);
        Assert.assertEquals(expected, output);
    }
}
