package InterviewQuestions.SecondInterview;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class removeElementAtIndiceTest {
    public Foo foo;

    @BeforeEach
    public void setup() {
        foo = new Foo();
    }


    @ParameterizedTest
    @MethodSource("createArrays")
    @DisplayName("Testing if we can remove an element from an array")
    public void given_array_should_return_new_array_without_element(String[] array, int indice, String[] expected) {
        String[] output = foo.removeElementAtIndice(array, indice);
        boolean compare = Arrays.equals(output, expected);
        assertTrue(compare);
    }

    private static Stream<Arguments> createArrays() {
        String[] arrayFirst = {"A", "B", "C", "D", "E"};
        int a = 1;
        String[] expectedFirst = {"B", "C", "D", "E"};

        String[] arraySecond = {"I", "N", "H", "M", "V", "Y", "P"};
        int b = 4;
        String[] expectedSecond = {"I", "N", "H", "V", "Y", "P"};

        String[] arrayThird = {"MI", "HKOJ", "JKOL", "KHYJ", "OIOJ", "JJEFJEF"};
        int c = 9;
        String[] expectedThird = null;

        String[] arrayFourth = {"B", "OJ", "JO", "EF"};
        int d = 0;
        String[] expectedFourth = null;

        String[] arrayFifth = {"B", "OJ", "JO", "EF"};
        int e = -2;
        String[] expectedFifth = null;

        String[] arraySixth = null;
        int f = 5;
        String[] expectedSixth = null;

        String[] arraySeventh = {};
        int g = 5;
        String[] expectedSeventh = null;

        return Stream.of(
                Arguments.of(arrayFirst, a, expectedFirst),
                Arguments.of(arraySecond, b, expectedSecond),
                Arguments.of(arrayThird, c, expectedThird),
                Arguments.of(arrayFourth, d, expectedFourth),
                Arguments.of(arrayFifth, e, expectedFifth),
                Arguments.of(arraySixth, f, expectedSixth),
                Arguments.of(arraySeventh, g, expectedSeventh));
    }
}
