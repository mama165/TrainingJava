package InterviewQuestions.FirstInterview;

import InterviewQuestions.FirstInterview.models.Node;

public class BaseConditionBuilder {
    Node root;

    public BaseConditionBuilder(Node root) {
        this.root = root;
    }

    private BaseCondition builder() {
        return root.baseCondition();
    }

    public BaseCondition build() {
        return builder();
    }
}
