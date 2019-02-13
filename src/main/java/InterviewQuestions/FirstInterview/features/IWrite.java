package InterviewQuestions.FirstInterview.features;

import InterviewQuestions.FirstInterview.models.Node;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

public interface IWrite {
    Optional<File> buildFile(Node nodes) throws IOException;
}
