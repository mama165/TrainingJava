package InterviewQuestions.FirstInterview.features;

import InterviewQuestions.FirstInterview.exceptions.IllegalExtensionFile;
import InterviewQuestions.FirstInterview.models.Rows;

import java.io.File;
import java.io.IOException;

public interface IExtract {
    Rows extractLines(File file) throws IOException, IllegalExtensionFile;
}
