package fr.coding.bankaccount.printers;

import fr.coding.bankaccount.exceptions.AmountNegativeException;
import fr.coding.bankaccount.formatters.OperationFormatter;
import fr.coding.bankaccount.models.Amount;
import fr.coding.bankaccount.models.Operation;
import fr.coding.bankaccount.models.OperationType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import static java.lang.System.lineSeparator;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TextOperationPrinterTest {
    private TextOperationPrinter textOperationPrinter;

    @Mock
    private OperationFormatter operationFormatter;

    private ByteArrayOutputStream consoleOutputStream;
    private final PrintStream formerOutputPrintStream = System.out;

    @BeforeEach
    void redirectOutputStream() {
        consoleOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(consoleOutputStream));
    }

    @AfterEach
    void restoreOutputStream() {
        System.setOut(formerOutputPrintStream);
    }

    @BeforeEach
    void setup() {
        textOperationPrinter = new TextOperationPrinter(operationFormatter);
    }

    @Test
    void should_print_when_formatter_called() throws AmountNegativeException {
        BigDecimal balance = BigDecimal.TEN;

        Instant d1 = Instant.ofEpochSecond(1424080802);
        Instant d2 = Instant.ofEpochSecond(1455616802);
        Instant d3 = Instant.ofEpochSecond(1487239202);
        Instant d4 = Instant.ofEpochSecond(1518775202);

        List<Operation> operations = Arrays.asList(
                Operation.create(123L, Amount.create("100"), OperationType.DEPOSIT, d1),
                Operation.create(123L, Amount.create("78"), OperationType.WITHDRAWAL, d2),
                Operation.create(123L, Amount.create("98"), OperationType.DEPOSIT, d3),
                Operation.create(123L, Amount.create("45"), OperationType.DEPOSIT, d4)
        );

        String expected = "| AccountID : 123, Balance : 165" + lineSeparator() +
                "| Date : 2015-02-16, Amount : 100, Operation : Deposit" + lineSeparator() +
                "| Date : 2016-02-16, Amount : 78, Operation : Withdrawal" + lineSeparator() +
                "| Date : 2017-02-16, Amount : 98, Operation : Deposit" + lineSeparator() +
                "| Date : 2018-02-16, Amount : 45, Operation : Deposit" + lineSeparator();

        when(operationFormatter.format(operations, balance)).thenReturn(expected);

        textOperationPrinter.print(operations, balance);

        verify(operationFormatter, times(1)).format(operations, balance);

        String consoleOutput = new String(consoleOutputStream.toByteArray());

        assertEquals(expected, consoleOutput);

        verifyNoMoreInteractions(operationFormatter);
    }
}
