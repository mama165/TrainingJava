package InterviewQuestions.FirstInterview.models;

import InterviewQuestions.FirstInterview.BaseCondition;

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

    public abstract boolean isLeaf();

    public abstract String buidCondition(String side);

    public List<BaseCondition> getAllBaseConditions() {
        List<BaseCondition> baseConditions = new LinkedList();
        Queue<String> queue = new LinkedList();
        return getAllBaseConditions(this, queue, baseConditions, "yes");
    }

    private List<BaseCondition> getAllBaseConditions(Node node, Queue queue, List<BaseCondition> baseConditions, String side) {
        if (node != null) {
            queue.add(node.buidCondition(side));
            getAllBaseConditions(node.getYes(), queue, baseConditions, "yes");
            // We've reached a leaf
            if (node.isLeaf()) {
                // Build our BaseCondition with elements from the queue
                // Add our BaseCondition into the list
                BaseCondition baseCondition = new BaseCondition();
                baseCondition.setConditions(new ArrayList(queue));
//                queue.forEach(element -> baseCondition.addCondition((String) element));
                baseConditions.add(baseCondition);
            }
            getAllBaseConditions(node.getNo(), queue, baseConditions, "no");
            queue.remove();
        }

        return baseConditions;
    }
}
