package fr.coding.auction;

import java.math.BigDecimal;

public class Price {
    private BigDecimal priceValue;

    private Price(String reservePrice) throws NegativePriceException {
        if (reservePrice == null || new BigDecimal(reservePrice).compareTo(BigDecimal.ZERO) < 0) {
            throw new NegativePriceException(reservePrice);
        }
        new BigDecimal(reservePrice);
    }

    public BigDecimal getPriceValue() {
        return priceValue;
    }

    public static Price create(String reservePrice) throws NegativePriceException {
        return new Price(reservePrice);
    }
}
