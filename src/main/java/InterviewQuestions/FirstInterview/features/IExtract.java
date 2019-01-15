package InterviewQuestions.FirstInterview.features;

import InterviewQuestions.FirstInterview.exceptions.IllegalExtensionFile;
import InterviewQuestions.FirstInterview.models.Rows;

import java.io.IOException;

public interface IExtract {
    Rows extractLines(String path) throws IOException, IllegalAccessException, IllegalExtensionFile;
}
