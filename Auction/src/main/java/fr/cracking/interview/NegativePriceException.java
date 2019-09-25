package fr.cracking.interview;

public class NegativePriceException extends Exception {
    NegativePriceException(String price) {
        super(formatExceptionMessage(price));
    }

    private static String formatExceptionMessage(String price) {
        String message = "The price is null";
        if (price != null) {
            message = "The price is negative : " + price;
        }

        return message;
    }
}
