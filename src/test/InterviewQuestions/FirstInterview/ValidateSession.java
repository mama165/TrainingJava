package InterviewQuestions.FirstInterview;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import static java.time.Duration.ofMillis;
import static junit.framework.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTimeout;

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

//        File output = decisionTree.doCalcul("");

        File expectedFirst = getFile("samples/expected/flattened_1.txt");
        File expectedSecond = getFile("samples/expected/flattened_2.txt");
        File expectedThird = getFile("samples/expected/flattened_3.txt");



        assertAll("Trees",
                () -> assertTrue(FileUtils.contentEquals(expectedFirst, expectedFirst)),
                () -> assertTrue(FileUtils.contentEquals(expectedSecond, expectedSecond)),
                () -> assertTrue(FileUtils.contentEquals(expectedThird, expectedThird))
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
        String path = "/home/mael/Documents/TrainingJava/src/main/java/InterviewQuestions/FirstInterview/samples/tree_to_convert.txt\n";

        long timeout = 2;  //ms

        assertTimeout(ofMillis(timeout), () -> {
            decisionTree.doCalcul(path);
        });
    }

    public File getFile(String path) {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(path);
        File file = new File(resource.getPath());

        return file;
    }
}
