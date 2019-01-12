package InterviewQuestions.FirstInterview;

import InterviewQuestions.FirstInterview.models.Node;
import InterviewQuestions.FirstInterview.models.Rows;

public class Operation {
    IMapper nodeMapper;

    public Operation(IMapper nodeMapper) {
        this.nodeMapper = nodeMapper;
    }

    public Node build(Rows rows) {
        return nodeMapper.build(rows);
    }
}
