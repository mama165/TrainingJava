package fr.coding.stringcalculator.features;

import fr.coding.stringcalculator.exceptions.NegativeNumberException;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class StringCalculator {
    private static final String DEFAULT_DELIMITER = String.join("|", ",", "\n");
    private static int LIMIT_VALUE = 1000;

    public int add(String input) throws NegativeNumberException {
        if (StringUtils.isBlank(input)) return 0;

        int[] integers = calculateIntegers(input);

        return add(integers);
    }

    private int[] calculateIntegers(String input) {
        String[] split = findValue(input);

        return Arrays.stream(split)
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    private String[] findValue(String input) {
        String value = input;
        String delimiter = DEFAULT_DELIMITER;

        if (input.startsWith("//")) {
            value = StringUtils.substringAfterLast(input, "\n");
            delimiter = DEFAULT_DELIMITER + "|" + StringUtils.substringBetween(input, "//", "\n");
        }

        return value.split(delimiter);
    }

    private int add(int[] integers) throws NegativeNumberException {
        List<String> negativeValues = new LinkedList<>();

        int sum = 0;

        for (int value : integers) {
            if (value < 0) {
                negativeValues.add(String.valueOf(value));
            }
            if (value <= LIMIT_VALUE) {
                sum += value;
            }
        }

        if (!negativeValues.isEmpty()) {
            throw new NegativeNumberException(negativeValues);
        }

        return sum;
    }
}
