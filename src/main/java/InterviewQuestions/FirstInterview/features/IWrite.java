package InterviewQuestions.FirstInterview.features;

import InterviewQuestions.FirstInterview.models.Node;

import java.io.File;
import java.io.IOException;

public interface IWrite {
    File buildFile(Node nodes) throws IOException;
}
