package fr.coding.auction;

public class NegativePriceException extends Exception {
    NegativePriceException(String prices) {
        super("At least one price is negative : " + prices);
    }
}
