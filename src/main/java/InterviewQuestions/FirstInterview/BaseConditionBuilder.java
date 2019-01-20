package InterviewQuestions.FirstInterview;

import InterviewQuestions.FirstInterview.models.Node;

import java.util.LinkedList;
import java.util.List;

public class BaseConditionBuilder {
    Node root;

    public BaseConditionBuilder(Node root) {
        this.root = root;
    }

    private List<BaseCondition> builder() {
        return getAllBaseConditions();
    }

    public List<BaseCondition> build() {
        return builder();
    }

    public List<BaseCondition> getAllBaseConditions() {
        String path[] = new String[1000];
        return getAllBaseConditions(root, path, 0, new LinkedList());
    }

    private LinkedList<BaseCondition> getAllBaseConditions(Node node, String path[], int pathLen, LinkedList<BaseCondition> baseConditions) {
        if (node != null) {
            path[pathLen] = node.buildCondition(node.isLeaf());
            pathLen++;

            if (node.getYes() == null && node.getNo() == null)
                storeElements(path, pathLen, baseConditions);
            else {
                getAllBaseConditions(node.getYes(), path, pathLen, baseConditions);
                getAllBaseConditions(node.getNo(), path, pathLen, baseConditions);
            }
        }
        return baseConditions;
    }

    private void storeElements(String path[], int length, LinkedList baseConditions) {
        BaseCondition baseCondition = new BaseCondition();
        for (int i = 0; i < length; i++) {
            baseCondition.addCondition(path[i]);
        }

        baseConditions.add(baseCondition);
    }
}
