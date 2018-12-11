package ArraysAndStrings;

import static org.junit.Assert.*;

import CrackingTheCodingInterview.ArraysAndStrings.Main;
import org.junit.jupiter.api.Test;

public class ArraysAndStringTest {

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

    @Test
    public void testURLify() {
        String output= Main.URLify("Mr John Smith    ");
        String expected = "Mr%20John%20Smith";
        assertEquals(expected, output);
    }

    @Test
    public void testNotURLify() {
        String output= Main.URLify("Mr John Smith    ");
        String expected = "Mr%20John%20Smith";
        assertEquals(expected, output);
    }

    @Test
    public void testPalindromePermutation() {
        assertTrue(Main.palindromePermutation("Tact Coa"));
    }

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

    @Test
    public void testMin() {
        int [] array = {12, 595, 6, 47, 41, 854865, 24, 577, 543, 74, 996};
        int output = Main.min(array);
        assertEquals(6, output);
    }

    @Test
    public void testMax() {
        int [] array = {14, 266, 355, 78, 52436, 8945, 42258};
        int output = Main.max(array);

        assertEquals(52436, output);
    }
}
