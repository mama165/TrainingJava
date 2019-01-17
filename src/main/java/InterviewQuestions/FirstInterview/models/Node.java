package InterviewQuestions.FirstInterview.models;

import InterviewQuestions.FirstInterview.BaseCondition;
import InterviewQuestions.FirstInterview.Condition;

import java.util.*;

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

    public LinkedList<BaseCondition> baseConditions() {
        Node root = this;
        LinkedList<BaseCondition> baseConditions = null;
        ArrayDeque<Node> stack = new ArrayDeque();
        stack.add(root);

        while (!stack.isEmpty()) {
            if (root.getYes() != null) {

                stack.add(root);
                root = root.getYes();
                continue;
            }

            if (root.getNo() != null) {

                stack.add(root);
                root = root.getNo();
                continue;
            }

            // We've reached a leaf !!
            // Todo : Don't forget the leaf !
            baseConditions.add(build(stack));

        }
        return baseConditions;
    }

    private BaseCondition build(ArrayDeque<Node> stack) {
        ArrayList<Node> list = new ArrayList(stack);

        list.forEach(element -> {

        });

        BaseCondition baseCondition = new BaseCondition();
        Condition condition = new Condition();
        baseCondition.addCondition(condition);
        return baseCondition;
    }
}
