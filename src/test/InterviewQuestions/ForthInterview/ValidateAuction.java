package InterviewQuestions.ForthInterview;

import InterviewQuestions.ForthInterview.beans.Buyer;
import InterviewQuestions.ForthInterview.beans.Parameters;
import InterviewQuestions.ForthInterview.beans.Winner;
import InterviewQuestions.ForthInterview.features.Algo;
import InterviewQuestions.ForthInterview.features.Auction;
import InterviewQuestions.ForthInterview.features.Checker;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;

import static junit.framework.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ValidateAuction {
    private Parameters parameters;
    private Winner expected;

    public ValidateAuction(Parameters parameters, Winner expected) {
        this.parameters = parameters;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> valuesSamples() {
        Parameters inputFirst = new Parameters(new LinkedList(Arrays.asList(
                new Buyer("A", Arrays.asList(110, 130)),
                new Buyer("B", Arrays.asList(0)),
                new Buyer("C", Arrays.asList(125)),
                new Buyer("D", Arrays.asList(105, 115, 90)),
                new Buyer("E", Arrays.asList(132, 135, 140))
        )), 100);

        Winner winnerFirst = new Winner("E", 130);

        Parameters inputSecond = new Parameters(new LinkedList(Arrays.asList(
                new Buyer("I", Arrays.asList(98, 7)),
                new Buyer("M", Arrays.asList(45, 53)),
                new Buyer("W", Arrays.asList(23)),
                new Buyer("O", Arrays.asList(15, 78)),
                new Buyer("X", Arrays.asList(25))
        )), 50);

        Winner winnerSecond = new Winner("I", 78);

        Parameters inputThird = new Parameters(new LinkedList(Arrays.asList(
                new Buyer("A", Arrays.asList(39, 25)),
                new Buyer("B", Arrays.asList(45, 23)),
                new Buyer("C", Arrays.asList(76)),
                new Buyer("D", Arrays.asList(10, 15, 90, 643)),
                new Buyer("E", Arrays.asList(79, 165))
        )), 63);

        Winner winnerThird = new Winner("D", 165);

        return Arrays.asList(new Object[][]{
                {inputFirst, winnerFirst},
                {inputSecond, winnerSecond},
                {inputThird, winnerThird},
        });
    }

    @Test
    public void validateAuctionWithCheckAndAlgo() {
        Auction auction = new Auction(new Checker(), new Algo());
        Winner output = auction.doCalcul(parameters);
        assertEquals(expected, output);
    }
}
