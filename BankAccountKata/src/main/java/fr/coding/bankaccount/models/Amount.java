package fr.coding.bankaccount.models;

import fr.coding.bankaccount.exceptions.AmountNegativeException;

import java.math.BigDecimal;
import java.util.Objects;

public class Amount {
    private final BigDecimal value;

    private Amount(BigDecimal value) {
        this.value = value;
    }

    public static Amount create(String amount) throws AmountNegativeException {
        if (amount == null) throw new NullPointerException("The amount is null");

        BigDecimal amountValue = new BigDecimal(amount);
        if (amountValue.compareTo(BigDecimal.ZERO) < 0) {
            throw new AmountNegativeException(amountValue);
        }
        return new Amount(amountValue);
    }

    public BigDecimal getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Amount amount = (Amount) o;
        return Objects.equals(value, amount.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
