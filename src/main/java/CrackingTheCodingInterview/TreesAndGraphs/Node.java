package CrackingTheCodingInterview.TreesAndGraphs;

import java.util.LinkedList;
import java.util.Queue;

public class Node {
    public int data;
    public Node[] adjacent;
    //    public Node left;
//    public Node right;
    public boolean marked;

    public Node(int data) {
        this.data = data;
    }

    public Node(Node node) {
        this.data = node.data;
        this.adjacent = node.adjacent;
        this.marked = node.marked;
    }

    public static void searchDFS(Node root) {
        if (root == null) return;
        root.marked = true;
        System.out.println(root.data);
        if (root.adjacent != null) {
            for (Node node : root.adjacent) {
                if (node.marked == false) {
                    searchDFS(node);
                }
            }
        }
    }

    public static void searchBFS(Node root) {
        if (root == null) return;

        Queue<Node> queue = new LinkedList();
        root.marked = true;
        queue.add(root);

        while (!queue.isEmpty()) {
            Node node = ((LinkedList<Node>) queue).pop();
            System.out.println(node.data);
            if (node.adjacent != null) {
                for (Node child : node.adjacent) {
                    if (child.marked == false) {
                        child.marked = true;
                        queue.add(child);
                    }
                }
            }
        }
    }

    public static boolean routeBetweenTwoNodes(Node root, int first, int second) {
        if (first == second) return true;

        boolean foundFirst = false;
        boolean foundSecond = false;

        Queue<Node> queue = new LinkedList();
        root.marked = true;
        queue.add(root);

        while (!queue.isEmpty()) {
            Node node = ((LinkedList<Node>) queue).pop();
            System.out.println(node.data);

            if (node.data == first) {
                foundFirst = true;
            }

            if (node.data == second) {
                foundSecond = true;
            }

            if (node.adjacent != null) {
                for (Node child : node.adjacent) {
                    if (child.marked == false) {
                        child.marked = true;
                        queue.add(child);
                    }
                }
            }
        }

        return foundFirst & foundSecond;
    }

    public static Node minimalTree(int[] values) {
//        if (values.length == 1) return new Node(values[0]);
        if (values.length == 2) {
            Node n = new Node(values[1]);
            n.adjacent = new Node[]{new Node(values[0])};
            return n;
        }

        int index = values.length / 2;
        int middle = values[index];
        Node root = new Node(middle);

        if (values.length != 1) {

            int[] subNodesLeft = new int[values.length / 2];
            int[] subNodesRight = new int[values.length - values.length / 2 -1];

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
                    minimalTree(subNodesLeft),
                    minimalTree(subNodesRight)
            };
        }
        return root;
    }
}
