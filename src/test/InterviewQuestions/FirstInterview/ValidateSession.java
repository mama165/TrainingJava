package InterviewQuestions.FirstInterview;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

import static java.time.Duration.ofMillis;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidateSession {
    @Test
    public void allTreeSamplesShouldPass() {
//        Extract extract = new Extract(new Validate(), new ReadFile());

        DecisionTree decisionTree = new DecisionTree(
                new Extract(
                        new Validate(), new ReadFile()
                ),
                new Convert(), new Write()
        );

        File inputFirst = FileUtils.getFile("samples/inputs/tree_1.txt");
        File inputSecond = FileUtils.getFile("samples/inputs/tree_2.txt");
        File inputThird = FileUtils.getFile("samples/inputs/tree_3.txt");

        File expectedFirst = FileUtils.getFile("samples/outputs/strategie_1.txt");
        File expectedSecond = FileUtils.getFile("samples/outputs/strategie_2.txt");
        File expectedThird = FileUtils.getFile("samples/outputs/strategie_3.txt");

        File outputFirst = decisionTree.doCalcul(inputFirst);
        File outputSecond = decisionTree.doCalcul(inputSecond);
        File outputThird = decisionTree.doCalcul(inputThird);

        assertAll("Trees",
                () -> assertTrue(FileUtils.contentEquals(expectedFirst, outputFirst)),
                () -> assertTrue(FileUtils.contentEquals(expectedSecond, outputSecond)),
                () -> assertTrue(FileUtils.contentEquals(expectedThird, outputThird))
        );
    }

    @Test
    @DisplayName("Test with a timeout")
    public void testWithTimeout() {
        DecisionTree decisionTree = new DecisionTree(
                new Extract(
                        new Validate(), new ReadFile()
                ),
                new Convert(), new Write()
        );

        long timeout = 2;  //ms

        assertTimeout(ofMillis(timeout), () -> {
            File input = FileUtils.getFile("samples/inputs/tree_1");
            decisionTree.doCalcul(input);
        });
    }
}
