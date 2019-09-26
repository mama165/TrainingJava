package fr.coding.auction;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Bids {
    private static String MINUS_CHAR = "-";
    private static String COMMA_CHAR = ",";
    private List<BigDecimal> bidsValue;

    private Bids(List<BigDecimal> bidsValue) throws NegativePriceException {
        this.bidsValue = bidsValue;
    }

    public List<BigDecimal> getBidsValue() {
        return bidsValue;
    }

    public static Bids create(Map<String, List<String>> input) throws NegativePriceException {
        if (input == null) throw new NullPointerException();

        List<String> allValues = new LinkedList<>();
        List<String> negativeValues;

        for (Map.Entry<String, List<String>> entry : input.entrySet()) {
            List<String> values = new ArrayList<>(entry.getValue());
            allValues.addAll(values);
        }

        negativeValues = allValues
                .stream()
                .filter(v -> v.startsWith(MINUS_CHAR))
                .collect(Collectors.toList());

        if (!negativeValues.isEmpty()) {
            throw new NegativePriceException(String.join(COMMA_CHAR, negativeValues));
        }

        List<BigDecimal> bidsValues = allValues.stream()
                .map(BigDecimal::new)
                .collect(Collectors.toList());

        return new Bids(bidsValues);
    }
}
