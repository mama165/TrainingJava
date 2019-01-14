package InterviewQuestions.FirstInterview;

import InterviewQuestions.FirstInterview.models.LeafLine;
import InterviewQuestions.FirstInterview.models.LeafNode;
import InterviewQuestions.FirstInterview.models.Node;

class LeafNodeBuilder extends BaseNodeBuilder {
    private LeafLine leafLine;

    public LeafNodeBuilder(LeafLine leafLine) {
        this.leafLine = leafLine;
    }

    public Node Build() {
        return new LeafNode(leafLine.getValue());
    }
}