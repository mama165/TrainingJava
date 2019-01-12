package InterviewQuestions.FirstInterview;

import InterviewQuestions.FirstInterview.models.Rows;

import java.io.IOException;

public interface IReadFile {
    Rows read(String file) throws IOException;
}
