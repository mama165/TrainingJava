package fr.coding.auction;

import java.math.BigDecimal;

public class Price {
    private BigDecimal priceValue;

    private Price(BigDecimal priceValue) throws NegativePriceException {
        this.priceValue = priceValue;
    }

    public BigDecimal getPriceValue() {
        return priceValue;
    }

    public static Price create(String price) throws NegativePriceException {
        if (price == null) throw new NullPointerException("The price is null");

        BigDecimal priceValue = new BigDecimal(price);

        if (priceValue.compareTo(BigDecimal.ZERO) < 0) {
            throw new NegativePriceException(price);
        }
        return new Price(priceValue);
    }
}
