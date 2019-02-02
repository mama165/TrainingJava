package InterviewQuestions.FirstInterview;

import InterviewQuestions.FirstInterview.exceptions.IllegalExtensionFile;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class testValidate {
    @Test
    public void shouldThrowAnExceptionWhenFileIsEmpty() {
        assertEquals(false, true);
    }

    @Test
    public void shouldThrowAnExceptionWhenExtensionFalse() {
        String path = "/home/mael/Documents/TrainingJava/src/main/java/InterviewQuestions/FirstInterview/samples/tree_to_convert.txt\n";

        assertThrows(IllegalExtensionFile.class,
                () -> System.out.printf("Hello")  /*validateFile.validate(path);*/
        );
    }
}