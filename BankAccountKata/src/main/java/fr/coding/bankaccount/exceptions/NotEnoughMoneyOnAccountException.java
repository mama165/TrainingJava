package fr.coding.bankaccount.exceptions;

import java.math.BigDecimal;

public class NotEnoughMoneyOnAccountException extends Exception {
    public NotEnoughMoneyOnAccountException(BigDecimal amount) {
        super("Withdrawal impossible with amount : " + amount);
    }
}
