package InterviewQuestions.ForthInterview;

import InterviewQuestions.ForthInterview.beans.Buyer;
import InterviewQuestions.ForthInterview.beans.Parameters;
import InterviewQuestions.ForthInterview.beans.Winner;
import InterviewQuestions.ForthInterview.features.Algo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ValidateAlgo {
    Algo algo;

    @BeforeEach
    public void setup() {
        algo = new Algo();
    }

    @Test
    public void ifFiveBuyers() {
        LinkedList buyers = new LinkedList(Arrays.asList(
                new Buyer("A", Arrays.asList(110, 130)),
                new Buyer("B", Arrays.asList(0)),
                new Buyer("C", Arrays.asList(125)),
                new Buyer("D", Arrays.asList(105, 115, 90)),
                new Buyer("E", Arrays.asList(132, 135, 140))
        ));
        Parameters parameters = new Parameters(buyers, 100);

        Winner expected = new Winner("E", 130);
        Winner output = algo.calculate(parameters);

        assertEquals(expected, output);
    }
}
