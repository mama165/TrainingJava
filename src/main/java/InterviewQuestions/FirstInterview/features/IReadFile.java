package InterviewQuestions.FirstInterview.features;

import InterviewQuestions.FirstInterview.models.Rows;

import java.io.File;
import java.io.IOException;

public interface IReadFile {
    Rows read(File file) throws IOException;
}
