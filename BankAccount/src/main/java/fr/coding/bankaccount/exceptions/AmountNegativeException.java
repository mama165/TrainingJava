package fr.coding.bankaccount.exceptions;

import java.math.BigDecimal;

public class AmountNegativeException extends Exception {
    public AmountNegativeException(BigDecimal amount) {
        super("The amount is a negative number (" + amount + ").");
    }
}
