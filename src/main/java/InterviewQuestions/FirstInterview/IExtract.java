package InterviewQuestions.FirstInterview;

import InterviewQuestions.FirstInterview.models.Rows;

import java.io.IOException;

public interface IExtract {
    Rows extractLines(String path) throws IOException;
}
