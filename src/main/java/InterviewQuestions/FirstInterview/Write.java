package InterviewQuestions.FirstInterview;

import InterviewQuestions.FirstInterview.features.IWrite;
import InterviewQuestions.FirstInterview.models.Node;

import java.io.*;
import java.util.List;
import java.util.Optional;

public class Write implements IWrite {
    public Optional<File> buildFile(Node node) throws IOException {
        List<BaseCondition> baseConditions = new BaseConditionBuilder(node).build();

        return new FileBuilder(baseConditions).build();
    }
}
