package InterviewQuestions.FirstInterview.models;

public abstract class Node {
    protected int data;

    public Node() {
    }

    public Node(int data) {
        this.data = data;
    }

    public int getData() {
        return data;
    }
}
