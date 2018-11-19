package ScibidsTest;

public class Node {
    int data;
    boolean feature;
    Node left, right;
    Boolean leftValue;
    Boolean rightValue;
    double leafValue;

    public Node(int data) {
        this.data = data;
        this.left = this.right = null;
    }
}