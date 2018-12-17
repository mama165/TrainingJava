package fr.kelkoo.interview;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import static junit.framework.Assert.assertTrue;

@RunWith(Parameterized.class)
public class removeElementAtIndiceTest {
    public String[] array;
    public int indice;
    public String[] expected;
    public Foo foo;

    public removeElementAtIndiceTest(String[] array, int indice, String[] expected) {
        this.array = array;
        this.indice = indice;
        this.expected = expected;
    }

    @Before
    public void setup() {
        foo  = new Foo();
    }

    @Parameters
    public static Collection<Object[]> sampleArrays() {
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

        return Arrays.asList(new Object[][]{
                {arrayFirst, a, expectedFirst},
                {arraySecond, b, expectedSecond},
                {arrayThird, c, expectedThird},
                {arrayFourth, d, expectedFourth},
                {arrayFifth, e, expectedFifth},
                {arraySixth, f, expectedSixth},
                {arraySeventh, g, expectedSeventh},
        });
    }

    @Test
    @DisplayName("Testing if we can remove an element from an array")
    public void testRemoveElementAtIndice() {
        String[] output = foo.removeElementAtIndice(array, indice);
        boolean compare = Arrays.equals(output, expected);
       assertTrue(compare);
    }
}
