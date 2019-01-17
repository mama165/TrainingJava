package InterviewQuestions.FirstInterview;

import InterviewQuestions.FirstInterview.models.Node;

import java.util.List;

public class BaseConditionBuilder {
    Node root;

    public BaseConditionBuilder(Node root) {
        this.root = root;
    }

    private List<BaseCondition> builder() {
        return root.baseConditions();
    }

    public List<BaseCondition> build() {
        return builder();
    }
}
