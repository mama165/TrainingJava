package fr.coding.stringcalculator.exceptions;

import java.util.List;

public class NegativeNumberException extends Exception {
    private NegativeNumberException(String message) {
        super(message);
    }

    public NegativeNumberException(List<String> negativeValues) throws NegativeNumberException {
        String message = formatMessage(negativeValues);
        throw new NegativeNumberException(message);
    }

    private String formatMessage(List<String> negativeValues) {
        String message = "Les nombres négatifs ne sont pas autorisés";

        if (negativeValues.size() > 1) {
            message = "Les nombres négatifs ne sont pas autorisés : " + String.join(",", negativeValues);
        }
        return message;
    }
}