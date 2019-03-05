package InterviewQuestions.FirstInterview;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Optional;

import static java.time.Duration.ofMillis;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidateDecisionTree {
    DecisionTree decisionTree;
    Extract extract;

    @BeforeEach
    public void setup() {
        extract = new Extract(new Validate(), new ReadFile());
        decisionTree = new DecisionTree(extract, new Convert(), new Write());
    }

    @Test
    public void allTreeSamplesShouldPass() {
//        Extract extract = new Extract(new Validate(), new ReadFile());

        File inputFirst = FileUtils.getFile("samples/inputs/tree_1.txt");
        File inputSecond = FileUtils.getFile("samples/inputs/tree_2.txt");
        File inputThird = FileUtils.getFile("samples/inputs/tree_3.txt");

        File expectedFirst = FileUtils.getFile("samples/outputs/strategie_1.txt");
        File expectedSecond = FileUtils.getFile("samples/outputs/strategie_2.txt");
        File expectedThird = FileUtils.getFile("samples/outputs/strategie_3.txt");

        Optional<File> optionalOutputFirst = decisionTree.doCalcul(inputFirst);
        File outputFirst = optionalOutputFirst.orElse(null);

        Optional<File> optionalOutputSecond = decisionTree.doCalcul(inputSecond);
        File outputSecond = optionalOutputSecond.orElse(null);

        Optional<File> optionalOutputThird = decisionTree.doCalcul(inputThird);
        File outputThird = optionalOutputThird.orElse(null);


        assertAll("Trees",
                () -> assertTrue(FileUtils.contentEquals(expectedFirst, outputFirst)),
                () -> assertTrue(FileUtils.contentEquals(expectedSecond, outputSecond)),
                () -> assertTrue(FileUtils.contentEquals(expectedThird, outputThird))
        );
    }

    @Test
    @DisplayName("Test with a timeout")
    public void testWithTimeout() {
        long timeout = 2;  //ms

        assertTimeout(ofMillis(timeout), () -> {
            File input = FileUtils.getFile("samples/inputs/tree_1.txt");
            decisionTree.doCalcul(input);
        });
    }
}
