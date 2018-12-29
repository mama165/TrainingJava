package ArraysAndStrings;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.*;

import CrackingTheCodingInterview.ArraysAndStrings.Main;
import fr.kelkoo.interview.Foo;
import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

public class ArraysAndStringTest {

    @Nested
    @DisplayName("Unit tests for Arrays and Strings")
    class InnerTest {
        @Nested
        @DisplayName("Unit tests for Arrays")
        class Arrays {
            @Nested
            @DisplayName("Test for method removeElementAtIndice")
            @RunWith(Parameterized.class)
            class removeElementAtIndice {
                public String[] array;
                public int indice;
                public String[] expected;
                public Foo foo;

                public removeElementAtIndice(String[] array, int indice, String[] expected) {
                    this.array = array;
                    this.indice = indice;
                    this.expected = expected;
                }

                @Before
                public void setup() {
                    foo = new Foo();
                }


                @Parameters
                public Collection<Object[]> sampleArrays() {
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

                    return java.util.Arrays.asList(new Object[][]{
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
                    boolean compare = java.util.Arrays.equals(output, expected);
                    assertTrue(compare);
                }
            }
        }

        @Nested
        @DisplayName("Unit tests for Strings")
        class StringValues {
            @Nested
            @DisplayName("Test for method isUnique")
            class IsUnique {
                @Test
                public void testIsUnique() {
                    assertTrue(Main.isUnique("patrick"));
                }

                @Test
                public void testIsNotUniqueUpperCase() {
                    assertFalse(Main.isUnique("patriciA"));
                }

                @Test
                public void testIsNotUnique() {
                    assertFalse(Main.isUnique("Michelle"));
                }
            }

            @Nested
            @DisplayName("Test for method checkPermutation")
            class CheckPermutation {
                @Test
                public void testCheckPermutation() {
                    assertTrue(Main.checkPermutation("pale", "alep"));
                }

                @Test
                public void testCheckPermutationUpperCase() {
                    assertTrue(Main.checkPermutation("PalE", "ALEp"));
                }

                @Test
                public void testCheckPermutationWithEscape() {
                    assertFalse(Main.checkPermutation("ninja", "jani n"));
                }

                @Test
                public void testNotCheckPermutation() {
                    assertFalse(Main.checkPermutation("hello", "Nello"));
                }
            }

            @Nested
            @DisplayName("test for URLify")
            class URLify {
                @Test
                public void testURLify() {
                    String output = Main.URLify("Mr John Smith    ");
                    String expected = "Mr%20John%20Smith";
                    assertEquals(expected, output);
                }

                @Test
                public void testNotURLify() {
                    java.lang.String output = Main.URLify("Mr John Smith    ");
                    java.lang.String expected = "Mr%20John%20Smith";
                    assertEquals(expected, output);
                }
            }

            @Nested
            @DisplayName("test for PalindromePermutation")
            class PalindromePermutation {
                @Test
                public void testPalindromePermutation() {
                    assertTrue(Main.palindromePermutation("Tact Coa"));
                }

                @Test
                public void testNotPalindromePermutation() {
                    assertFalse(Main.palindromePermutation("pkl lip"));
                }
            }

            @Nested
            @DisplayName("test for OneAway")
            class OneAway {
                @Test
                public void testOneAway() {
                    assertTrue(Main.oneAway("pale", "ple"));
                }

                @Test
                public void testNotOneAway() {
                    assertFalse(Main.oneAway("pale", "palme"));
                }

                @Test
                public void testNotOneAwaySecond() {
                    assertFalse(Main.oneAway("pale", "bake"));
                }
            }

            @Nested
            @DisplayName("test for StringCompression")
            class StringCompression {
                @Test
                public void testStringCompression() {
                    String expected = "a2b1c5a3";
                    String output = Main.stringCompression("aabcccccaaa");
                    assertEquals(expected, output);
                }

                @Test
                public void testNotStringCompression() {
                    String unexpected = "a1f1g2e2y11";
                    String output = Main.stringCompression("afggeey");
                    assertNotSame(unexpected, output);
                }
            }

            @Nested
            @DisplayName("Test for random methods")
            class Random {
                @Test
                public void testMin() {
                    int[] array = {12, 595, 6, 47, 41, 854865, 24, 577, 543, 74, 996};
                    int output = Main.min(array);
                    assertEquals(6, output);
                }

                @Test
                public void testMax() {
                    int[] array = {14, 266, 355, 78, 52436, 8945, 42258};
                    int output = Main.max(array);

                    assertEquals(52436, output);
                }
            }
        }
    }
}
