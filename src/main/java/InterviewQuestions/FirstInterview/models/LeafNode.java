package InterviewQuestions.FirstInterview.models;

public class LeafNode extends Node {
    private double value;

    public LeafNode(Double value) {
        this.value = value;
    }

    public LeafNode(Double value, int nodeNumber) {
        super(nodeNumber);
        this.value = value;
    }

    public String toString() {
        return this.data + ":leaf=" + this.value + "\n";
    }

    @Override
    public Node getYes() {
        return null;
    }

    @Override
    public Node getNo() {
        return null;
    }

    @Override
    public boolean isLeaf() {
        return true;
    }

    @Override
    public String buidCondition(String side) {
        if("yes".equals(side)) {

        } else {

        }
        return null;
    }
}
