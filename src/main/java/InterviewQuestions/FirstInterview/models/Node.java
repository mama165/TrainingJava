package InterviewQuestions.FirstInterview.models;

import InterviewQuestions.FirstInterview.BaseCondition;

import java.util.Stack;

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

    public BaseCondition baseCondition() {
        Node root = this;
        Stack<Node> stack = new Stack();
        stack.add(root);

        while (!stack.isEmpty()) {
            if (root.getYes() != null) {

                root = root.getYes();
                continue;
            }

            if (root.getNo() != null) {

                root = root.getNo();
                continue;
            }

            // We've reached a leaf !!


        }
        return new BaseCondition();
    }
}
