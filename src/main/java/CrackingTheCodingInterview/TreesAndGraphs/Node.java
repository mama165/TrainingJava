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

    public Node minimalTree(int[] nodes) {
        Node root = new Node(nodes.length / 2);

        if (nodes.length != 1) {

//        minimalTree(root, nodes);

            int[] subNodesLeft = null;
            int[] subNodesRight = null;

//        int[] subNodesLeft = new int[nodes.length / 2];
//        int[] subNodesRight = new int[nodes.length - nodes.length / 2 -1];

            for (int i = 0; i < nodes.length / 2; i++) {
                subNodesLeft[i] = nodes[i];
            }

            for (int i = nodes.length / 2; i < nodes.length; i++) {
                subNodesRight[i] = nodes[i];
            }

            root.adjacent = new Node[]{
                    minimalTree(subNodesLeft),
                    minimalTree(subNodesRight)
            };
        }
        return root;
    }
}
