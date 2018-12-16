package InterviewQuestions.model;

public abstract class Node {
    protected int nodeNumber;
    protected int profondeur; // root : 1,

    public Node(int nodeNumber) {
        this.nodeNumber = nodeNumber;
    }

    public abstract String toString();

}