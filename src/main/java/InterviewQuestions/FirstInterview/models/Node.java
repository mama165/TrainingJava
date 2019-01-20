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

    public abstract Node getYes();

    public abstract Node getNo();

    public abstract boolean isLeaf();

    public abstract String buildCondition(boolean side);
}
