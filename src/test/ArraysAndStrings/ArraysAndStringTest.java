package ArraysAndStrings;

import CrackingTheCodingInterview.ArraysAndStrings.Foo;
import CrackingTheCodingInterview.ArraysAndStrings.sorting.Bar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class ArraysAndStringTest {
    Foo foo;
    Bar bar;

    @BeforeEach
    public void setup() {
        foo = new Foo();
        bar = new Bar();
    }
    @Nested
    @DisplayName("Unit tests for Arrays and Strings")
    class InnerTest {
        @Nested
        @DisplayName("Unit tests for Strings")
        class StringValues {
            @Nested
            @DisplayName("Test for method isUnique")
            class IsUnique {
                @Test
                public void testIsUnique() {
                    assertTrue(Foo.isUnique("patrick"));
                }

                @Test
                public void testIsNotUniqueUpperCase() {
                    assertFalse(Foo.isUnique("patriciA"));
                }

                @Test
                public void testIsNotUnique() {
                    assertFalse(Foo.isUnique("Michelle"));
                }
            }

            @Nested
            @DisplayName("Test for method checkPermutation")
            class CheckPermutation {
                @Test
                public void testCheckPermutation() {
                    assertTrue(Foo.checkPermutation("pale", "alep"));
                }

                @Test
                public void testCheckPermutationUpperCase() {
                    assertTrue(Foo.checkPermutation("PalE", "ALEp"));
                }

                @Test
                public void testCheckPermutationWithEscape() {
                    assertFalse(Foo.checkPermutation("ninja", "jani n"));
                }

                @Test
                public void testNotCheckPermutation() {
                    assertFalse(Foo.checkPermutation("hello", "Nello"));
                }
            }

            @Nested
            @DisplayName("test for URLify")
            class URLify {
                @Test
                public void testURLify() {
                    String output = Foo.URLify("Mr John Smith    ");
                    String expected = "Mr%20John%20Smith";
                    assertEquals(expected, output);
                }

                @Test
                public void testNotURLify() {
                    java.lang.String output = Foo.URLify("Mr John Smith    ");
                    java.lang.String expected = "Mr%20John%20Smith";
                    assertEquals(expected, output);
                }
            }

            @Nested
            @DisplayName("test for PalindromePermutation")
            class PalindromePermutation {
                @Test
                public void testPalindromePermutation() {
                    assertTrue(Foo.palindromePermutation("Tact Coa"));
                }

                @Test
                public void testNotPalindromePermutation() {
                    assertFalse(Foo.palindromePermutation("pkl lip"));
                }
            }

            @Nested
            @DisplayName("test for OneAway")
            class OneAway {
                @Test
                public void testOneAway() {
                    assertTrue(Foo.oneAway("pale", "ple"));
                }

                @Test
                public void testNotOneAway() {
                    assertFalse(Foo.oneAway("pale", "palme"));
                }

                @Test
                public void testNotOneAwaySecond() {
                    assertFalse(Foo.oneAway("pale", "bake"));
                }
            }

            @Nested
            @DisplayName("test for stringCompression")
            class StringCompression {
                @Test
                public void testStringCompression() {
                    String expected = "a2b1c5a3";
                    String output = Foo.stringCompression("aabcccccaaa");
                    assertEquals(expected, output);
                }

                @Test
                public void testNotStringCompression() {
                    String unexpected = "a1f1g2e2y11";
                    String output = Foo.stringCompression("afggeey");
                    assertNotSame(unexpected, output);
                }
            }

            @Nested
            @DisplayName("test for stringRotation")
            class StringRotation {
                @Test
                public void testStringRotation() {
                    boolean expected = true;
                    boolean output = Foo.stringRotation("waterbottle", "erbottlewat");
                    assertEquals(expected, output);
                }

                @Test
                public void testStringRotationSecond() {
                    boolean expected = true;
                    boolean output = Foo.stringRotation("stackoverflow", "tackoverflows");
                    assertEquals(expected, output);
                }

                @Test
                public void testStringRotationThird() {
                    boolean expected = true;
                    boolean output = Foo.stringRotation("stackoverflow", "ackoverflowst");
                    assertEquals(expected, output);
                }

                @Test
                public void testStringRotationForth() {
                    boolean expected = true;
                    boolean output = Foo.stringRotation("stackoverflow", "overflowstack");
                    assertEquals(expected, output);
                }

                @Test
                public void testStringRotationFifth() {
                    boolean expected = true;
                    boolean output = Foo.stringRotation("stackoverflow", "stackoverflwo");
                    assertEquals(expected, output);
                }

                @Test
                public void testStringRotationSixth() {
                    boolean expected = true;
                    boolean output = Foo.stringRotation("stackoverflow", "flowstackover");
                    assertEquals(expected, output);
                }

                @Test
                public void testStringRotationSeventh() {
                    boolean expected = true;
                    boolean output = Foo.stringRotation("stackoverflow", "wstackoverflo");
                    assertEquals(expected, output);
                }
            }

            @Nested
            @DisplayName("Test for closest to zero")
            class closestToZero {
                @Test
                @DisplayName("Test with a good behaviour")
                public void testWithStandardParameters() {
                    int[] values = {456, 34, -45, -32, -87, 90, 32, -34};
                    int output = foo.closestToZero(values);
                    int expected = 32;
                    assertEquals(expected, output);
                }

                @Test
                @DisplayName("Test with same absolute parameters -5 and 5")
                public void testWithSameAbsoluteValues() {
                    int[] values = {-5, 5};
                    int output = foo.closestToZero(values);
                    int expected = 5;
                    assertEquals(expected, output);
                }

                @Test
                @DisplayName("Test with parameters -5 and 5")
                public void testWithResultNotInTheInput() {
                    int[] values = {-5, -5};
                    int output = foo.closestToZero(values);
                    int expected = -5;
                    assertEquals(expected, output);
                }

                @Test
                @DisplayName("Test with an empty array should return 0")
                public void testWithEmptyArrayShouldReturnZero() {
                    int[] values = {};
                    int output = foo.closestToZero(values);
                    int expected = 0;
                    assertEquals(expected, output);
                }
            }

            @Nested
            @DisplayName("Test for random methods")
            class Random {
                @Test
                public void testMin() {
                    int[] array = {12, 595, 6, 47, 41, 854865, 24, 577, 543, 74, 996};
                    int output = Foo.min(array);
                    assertEquals(6, output);
                }

                @Test
                public void testMax() {
                    int[] array = {14, 266, 355, 78, 52436, 8945, 42258};
                    int output = Foo.max(array);

                    assertEquals(52436, output);
                }
            }
            @Nested
            @DisplayName("Test for balanced parenthesis")
            class Parenthesis {
                @ParameterizedTest
                @CsvSource({
                        "'{}',true",
                        "{{)(}}, false",
                        "({)}, false",
                        "[({})], true",
                        "{}([]), true",
                        "{()}[[{}]], true",
                        "{} [[, false"
                })
                @DisplayName("Test with numbers separated with custom delimiter")
                void testBalancedParenthesis(String input, boolean expected) {
                    boolean output = Foo.balancedBrackets(input);
                    assertEquals(expected, output);
                }
            }
        }

        @Nested
        @DisplayName("Unit tests for sorting")
        class Sorting {
            @Nested
            @DisplayName("Unit test for insert sort")
            class insertSorting {
                @Test
                @DisplayName("insert sort")
                public void insertSort() {
                    int[] input = {5, 2, 4, 6, 1, 3};
                    int[] expected = {1, 2, 3, 4, 5, 6};

                    int[] output = bar.insertSort(input);
                    assertTrue(Arrays.equals(expected, output));
                    //assertArrayEquals(expected, output);
                }
            }
        }
    }
}
