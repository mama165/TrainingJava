package InterviewQuestions.FirstInterview.model;

public abstract class Node {
    protected int nodeNumber;
    protected int profondeur; // root : 1,


    public Node() {
    }

    public Node(int nodeNumber) {
        this.nodeNumber = nodeNumber;
    }

    public int getNodeNumber() {
        return nodeNumber;
    }

    //    public abstract String toString();

}