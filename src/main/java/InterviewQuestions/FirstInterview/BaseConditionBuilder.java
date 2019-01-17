package InterviewQuestions.FirstInterview;

import InterviewQuestions.FirstInterview.models.Node;


public class BaseConditionBuilder {
    Node node;

    public BaseConditionBuilder(Node node) {
        this.node = node;
    }

    private BaseCondition builder() {

        return null;
    }

    public BaseCondition build() {
        return builder();
    }
}
