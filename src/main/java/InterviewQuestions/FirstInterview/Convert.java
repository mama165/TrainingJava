package InterviewQuestions.FirstInterview;

import InterviewQuestions.FirstInterview.models.Node;
import InterviewQuestions.FirstInterview.models.Rows;

public class Convert implements IConvert{
    public Node convert(Rows rows) {
        Operation operation = new Operation(new Mapper());
        Node node = operation.build(rows);
        return node;
    }
}
