package InterviewQuestions.FirstInterview.models;

public class LeafNode extends Node {
    private double value;

    public LeafNode(Double value, int nodeNumber) {
        super(nodeNumber);
        this.value = value;
    }

    public String toString() {
        return this.nodeNumber + ":leaf=" + this.value + "\n";
    }
}
