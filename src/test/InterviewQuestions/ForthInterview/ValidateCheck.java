package InterviewQuestions.ForthInterview;

import InterviewQuestions.ForthInterview.beans.Buyer;
import InterviewQuestions.ForthInterview.beans.Parameters;
import InterviewQuestions.ForthInterview.exceptions.ParameterException;
import InterviewQuestions.ForthInterview.features.Checker;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ValidateCheck {
    Checker checker;

    @Before
    public void setup() {
        checker = new Checker();
    }

    @Test
    public void ifDataRightCalculateThrowsNothing() {
        Parameters parameters = new Parameters(Arrays.asList(
                new Buyer("A", new LinkedList(Arrays.asList(110, 130))),
                new Buyer("B", new LinkedList(Arrays.asList(0))),
                new Buyer("C", new LinkedList(Arrays.asList(125))),
                new Buyer("D", new LinkedList(Arrays.asList(105, 115, 90))),
                new Buyer("E", new LinkedList((Arrays.asList(132, 135, 140))))
        ), 100);

        assertDoesNotThrow(() -> checker.check(parameters));
    }

    @Test
    public void ifNoBidsForOneOrMorePlayer() {
        Parameters parameters = new Parameters(new LinkedList(Arrays.asList(
                new Buyer("A", Arrays.asList(110, 130)),
                new Buyer("B", Arrays.asList(0)),
                new Buyer("C", Arrays.asList()),
                new Buyer("D", Arrays.asList()),
                new Buyer("E", Arrays.asList(132, 135, 140))
        )), 100);


        assertThrows(ParameterException.class, () -> {
            checker.check(parameters);
        });
    }

    @Test
    public void IfLessThanTwoPlayersCalculateThrowsException() {
        Parameters parameters = new Parameters(Arrays.asList(
                new Buyer("A", Arrays.asList(110, 130))
        ), 100);

        assertThrows(ParameterException.class, () -> {
            checker.check(parameters);
        });
    }

    @Test
    public void IfBidIsZeroCaculateThrowsException() {
        Parameters parameters = new Parameters(Arrays.asList(
                new Buyer("A", new LinkedList(Arrays.asList(0, 0))),
                new Buyer("B", new LinkedList(Arrays.asList(0))),
                new Buyer("C", new LinkedList(Arrays.asList(0))),
                new Buyer("D", new LinkedList(Arrays.asList(0, 0, 0))),
                new Buyer("E", new LinkedList(Arrays.asList(0, 0, 0)))
        ), 100);

        assertThrows(ParameterException.class,
                () -> checker.check(parameters));
    }

    @Test
    public void ifAllBidsLowerThanReferenceCalculateThrowsException() {
        Parameters parameters = new Parameters(Arrays.asList(
                new Buyer("A", new LinkedList(Arrays.asList(110, 130))),
                new Buyer("B", new LinkedList(Arrays.asList(0))),
                new Buyer("C", new LinkedList(Arrays.asList(125))),
                new Buyer("D", new LinkedList(Arrays.asList(105, 115, 90))),
                new Buyer("E", new LinkedList(Arrays.asList(132, 135, 140)))
        ), 200);

        assertThrows(ParameterException.class,
                () -> checker.check(parameters)
        );
    }

    @Test
    public void ifReservePriceLessOrEqualToZeroThrowsExeption() {
        Parameters parameters = new Parameters(Arrays.asList(
                new Buyer("A", new LinkedList(Arrays.asList(110, 130))),
                new Buyer("B", new LinkedList(Arrays.asList(0))),
                new Buyer("C", new LinkedList(Arrays.asList(125))),
                new Buyer("D", new LinkedList(Arrays.asList(105, 115, 90))),
                new Buyer("E", new LinkedList(Arrays.asList(132, 135, 140)))
        ), 0);

        assertThrows(ParameterException.class,
                () -> checker.check(parameters)
        );
    }
}
