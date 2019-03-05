package InterviewQuestions.FirstInterview;

import InterviewQuestions.FirstInterview.models.Node;
import InterviewQuestions.FirstInterview.models.Rows;

abstract class BaseNodeBuilder {
    protected Rows rows;

    public abstract Node build();

    public BaseNodeBuilder() {
    }

    BaseNodeBuilder(Rows rows) {
        this.rows = rows;
    }
}
