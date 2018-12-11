package CrackingTheCodingInterview.TreesAndGraphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BinaryNode {
    public int data;
    public BinaryNode left;
    public BinaryNode right;

    public BinaryNode(int data) {
        this.data = data;
    }

    public Node minimalTree2(int[] values) {

        if (values.length == 2) {
            Node n = new Node(values[1]);
            n.adjacent = new Node[]{new Node(values[0])};
            return n;
        }

        int index = values.length / 2;
        int middle = values[index];
        Node root = new Node(middle);

        if (values.length == 1) return root;

        int[] subNodesLeft = new int[values.length / 2];
        int[] subNodesRight = new int[values.length - values.length / 2 - 1];

        int j = 0;
        for (int i = 0; i < values.length / 2; i++) {
            subNodesLeft[j] = values[i];
            j++;
        }

        int k = 0;
        for (int i = values.length / 2 + 1; i < values.length; i++) {
            subNodesRight[k] = values[i];
            k++;
        }

        root.adjacent = new Node[]{
                minimalTree2(subNodesLeft),
                minimalTree2(subNodesRight)
        };

        return root;

    }

    /**
     * By a teamate
     *
     * @param values
     * @return
     */
    public Node minimalTree(List<Integer> values) {

        Node parentNode = new Node(values.get(0));
        List<Integer> valuesrestantes = values.subList(1, values.size() - 1);
        if (valuesrestantes.isEmpty()) {
            return parentNode;
        }
        int middleLength = valuesrestantes.size() / 2;
        List<Node> children = new LinkedList();
        List<Integer> subListLeft = valuesrestantes.subList(0, middleLength - 1);
        if (!subListLeft.isEmpty()) {
            children.add(minimalTree(subListLeft));
        }
        List<Integer> subListRight = valuesrestantes.subList(middleLength, valuesrestantes.size() - 1);
        if (!subListRight.isEmpty()) {
            children.add(minimalTree(subListRight));
        }
        parentNode.adjacent = (Node[]) children.toArray();
        return parentNode;
    }


    public boolean validateBSTRecursive(BinaryNode root) {
        if (root != null
                && root.left != null && root.right != null) {

            boolean required = root.left.data < root.data
                    && root.data < root.right.data;

            if (root.left.data < root.data && root.data < root.right.data) {

            }
            validateBSTRecursive(root.left);
            validateBSTRecursive(root.right);
        }
        return false;
    }

    public boolean validateBST() {
        BinaryNode root = this;
        ArrayList<BinaryNode> nodes = new ArrayList();
        ArrayList<BinaryNode> tmpList = new ArrayList();

        nodes.add(root);

        while (!nodes.isEmpty()) {
            for (BinaryNode node : nodes) {
                if (!isBST(node)) {
                    return false;
                }

                // On ajoute les enfants
                if (node.left != null) {
                    tmpList.add(node.left);
                }
                if (node.right != null) {
                    tmpList.add(node.right);
                }
            }
            nodes = tmpList;
            tmpList = new ArrayList<>();
        }

        return true;
    }

    public boolean isBST(BinaryNode root) {
        // On est sur une feuille
        if (root.left == null && root.right == null) return true;

        // On est pas sur une feuille !

        boolean left = true;
        boolean right = true;

        if (root.left != null && root.left.data > root.data) {
            left = false;
        }

        if (root.right != null && root.right.data < root.data) {
            right = false;
        }

        return left & right;
    }
}