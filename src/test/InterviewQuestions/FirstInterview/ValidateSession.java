package InterviewQuestions.FirstInterview;

import org.junit.Test;

import java.io.File;

import static junit.framework.Assert.assertEquals;

public class ValidateSession {
    @Test
    public void testShouldReturn() {
        Extract extract = new Extract(new Validate(), new ReadFile());

        DecisionTree decisionTree = new DecisionTree(
                new Extract(
                        new Validate(), new ReadFile()
                ),
                new Convert(), new Write()
        );
        String path = "/home/mael/Documents/TrainingJava/src/main/java/InterviewQuestions/FirstInterview/tree_to_convert.txt\n";

        File expected = null;
        File output = decisionTree.doCalcul(path);
        assertEquals(expected, output);
    }
}
