package InterviewQuestions.FirstInterview;

import InterviewQuestions.FirstInterview.features.IWrite;
import InterviewQuestions.FirstInterview.models.Node;

import java.io.*;

public class Write implements IWrite {
    public File buildFile(Node node) throws IOException {
        BaseCondition baseCondition = new BaseConditionBuilder(node).build();

        return new FileBuilder(baseCondition).build();
    }
}
