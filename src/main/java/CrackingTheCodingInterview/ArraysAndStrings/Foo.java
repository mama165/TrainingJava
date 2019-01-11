package CrackingTheCodingInterview.ArraysAndStrings;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Foo {
    public static boolean isUnique(String s) {
        char[] tab = s.toLowerCase().toCharArray();

        for (int i = 0; i < tab.length; i++) {
            for (int j = i + 1; j < tab.length; j++) {
                if (tab[i] == tab[j]) {
                    return false;
                }
            }
        }
        return true;
    }

    // No data structure
    public static boolean isUniqueNoDataStructure(String s) {
        for (int i = 0; i < s.toLowerCase().toCharArray().length; i++) {
            for (int j = i + 1; j < s.toLowerCase().toCharArray().length; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean checkPermutation(String s1, String s2) {
        char[] c1 = s1.toLowerCase().toCharArray();
        char[] c2 = s2.toLowerCase().toCharArray();

        if (c1.length != c2.length)
            return false;

        ArrayList list = new ArrayList();

        boolean findElement = false;

        for (int i = 0; i < c1.length; i++) {
            for (int j = 0; j < c2.length; j++) {
                if (!list.contains(j) && (c1[i] == c2[j])) {
                    list.add(j);
                    findElement = true;
                }
            }
            if (findElement == false)
                return false;
        }
        return true;
    }

    public static String URLify(String s) {
        int index = 0;
        char[] c = s.toCharArray();

        char[] cToReturn = new char[c.length];

        char[] charUrl = {'%', '2', '0'};

        for (int i = 0; i < c.length; i++) {
            if (c[i] == ' ') {
                for (int j = 0; j < charUrl.length; j++) {
                    if (i + j + index < cToReturn.length) {
                        cToReturn[i + j + index] = charUrl[j];
                    }
                }
                //index += 2;
                index += charUrl.length - 1;
            } else if (i + index < c.length) {
                cToReturn[i + index] = c[i];
            }
        }
        return String.valueOf(cToReturn);
    }

    public static boolean palindromePermutation(String s) {
        char[] tab = s.toCharArray();

        ArrayList indexes = new ArrayList();

        boolean pivot = false;

        for (int i = 0; i < tab.length; i++) {
            if (i + 1 < tab.length) {
                for (int j = i + 1; j < tab.length; j++) {
                    if (!indexes.contains(i) && tab[i] == tab[j]) {
                        // I can find the same letter
                        // So I keep is position in the array and I quit the loop
                        indexes.add(j);
                        break;
                    }
                }
            } else {
                if (pivot == false) {
                    System.out.println("palindrome");
                }
            }
        }
        return false;
    }


    /* Example
      0    1    2    3    4    5
    [c1, c4, c3, c2, c0, c5]

    [c0, c1, c2, c3, c4]
      0    1    2    3    4

      oneAway("pppl", "pppe"))
     */
    public static boolean oneAway(String s1, String s2) {
        char[] tab1 = s1.toCharArray();
        char[] tab2 = s2.toCharArray();

        ArrayList indexesToEvict = new ArrayList();
        int commonLetters = 0;

        // Assuming s1 is bigger than s2
        for (int i = 0; i < tab2.length; i++) {
            for (int j = 0; j < tab1.length; j++) {
                if (!indexesToEvict.contains(j) && tab1[j] == tab2[i]) {
                    // Keep the index in a list (we can have the same letter)
                    indexesToEvict.add(j);
                    commonLetters++;
                    break;
                }
            }
        }

        // Now we know how many letters are both sides

        if (tab1.length - commonLetters <= 1) {
            return true;
        }
        return false;
//        return indexes.size() == 0 || indexes.size() > 1;
//        return !(indexes.size() == 0);
    }

    public static String stringCompression(String s) {
        char[] tab = s.toCharArray();
        int count = 1;
        ArrayList indexes = new ArrayList();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < tab.length; i++) {
            if (!indexes.contains(i)) {
                for (int j = i + 1; j < tab.length; j++) {
                    if (tab[i] != tab[j]) {
                        sb.append(tab[i]);
                        sb.append(count);
                        count = 1;
                        break;
                    } else {
                        indexes.add(j);
                        count++;
                        // Before it stops
                        if (j == tab.length - 1) {
                            sb.append(tab[i]);
                            sb.append(count);
                        }
                    }
                }
                // We can have on letter alone at the end !!
                if (i == tab.length - 1) {
                    sb.append(tab[i]);
                    sb.append(1);
                }
            }
        }
        return sb.toString();
    }

    public static boolean stringRotation(String a, String b) {
        return false;
    }


    public int closestToZero(int [] values) {
        IntStream s = Arrays.stream(values);
        s.forEach(x -> System.out.println(x));
        List elements = Arrays.asList(values);
        elements.stream().forEach(e -> System.out.println(e));
//        elements.forEach(e -> {
//            System.out.println(e);
//        });
        return 0;
    }

    public static int min(int[] array) {
        int min = array[0];

        for (int a : array) {
            if (a < min) {
                min = a;
            }
        }
        return min;
    }

    public static int max(int[] array) {
        int max = array[0];

        for (int a : array) {
            if (a > max) {
                max = a;
            }
        }
        return max;
    }
}