package InterviewQuestions.FirstInterview;

import InterviewQuestions.FirstInterview.features.IWrite;
import InterviewQuestions.FirstInterview.models.Node;

import java.io.*;
import java.util.List;

public class Write implements IWrite {
    public File buildFile(Node node) throws IOException {
        List<BaseCondition> baseConditions = new BaseConditionBuilder(node).build();

        return new FileBuilder(baseConditions).build();
    }
}
