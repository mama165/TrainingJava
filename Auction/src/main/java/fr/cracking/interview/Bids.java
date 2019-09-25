package fr.cracking.interview;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Bids {
   private static String MINUS_CHAR = "-";
    private static String COMMA_CHAR = ",";
    private List<BigDecimal> bidsValue;

    private Bids(Map<String, List<String>> input) throws NegativePriceException {
        if (input == null) throw new NullPointerException();
        List <String> negativeValues = new LinkedList<>();

        for (Map.Entry<String, List<String>> entry : input.entrySet()) {
            List<String> value = entry.getValue()
                    .stream()
                    .filter(v -> v.startsWith(MINUS_CHAR))
                    .collect(Collectors.toList());

            negativeValues.addAll(value);
        }

        if (!negativeValues.isEmpty()) {
            throw new NegativePriceException(String.join(COMMA_CHAR, negativeValues));
        }
    }

    public List<BigDecimal> getBidsValue() {
        return bidsValue;
    }

    public static Bids create(Map<String, List<String>> input) throws NegativePriceException {
        return new Bids(input);
    }
}
