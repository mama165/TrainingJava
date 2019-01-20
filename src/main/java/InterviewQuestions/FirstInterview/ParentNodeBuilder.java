package InterviewQuestions.FirstInterview;

import InterviewQuestions.FirstInterview.models.*;

class ParentNodeBuilder extends BaseNodeBuilder {
    private ParentLine parentLine;

    public ParentNodeBuilder(Rows rows, ParentLine parentLine) {
        super(rows);
        this.parentLine = parentLine;
    }

    public Node Build() {
        Node yes = buildNode(parentLine.getYes());
        Node no = buildNode(parentLine.getNo());

        return new ParentNode.Builder()
                .addData(parentLine.getData())
                .yes(yes)
                .no(no)
                .withFeature(parentLine.getFeature())
                .build();
    }

    private Node buildNode(Integer index) {
        BaseLine line = rows.getElement(index);
        if (line instanceof ParentLine) {
            return new ParentNodeBuilder(rows, (ParentLine) line).Build();
        } else {
            return new LeafNodeBuilder((LeafLine) line).Build();
        }
    }
}
