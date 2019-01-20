package InterviewQuestions.FirstInterview.models;

public class LeafNode extends Node {
    private double value;

    public LeafNode(Double value) {
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
    public String buildCondition(boolean condition) {
        return "leaf=" + this.value;
    }
}
