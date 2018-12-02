package CrackingTheCodingInterview.TreesAndGraphs;

import java.util.LinkedList;
import java.util.Queue;

public class Node {
    public int data;
    public Node[] adjacent;
    public boolean marked;

    public Node(int data) {
        this.data = data;
    }

    public static void searchDFS(Node root) {
        if (root == null) return;
        root.marked = true;
        System.out.println(root.data);
        if(root.adjacent != null) {
            for (Node node : root.adjacent) {
                if (node.marked == false) {
                    searchDFS(node);
                }
            }
        }
    }

    public static void searchBFS(Node root) {
        if(root == null) return;

        Queue<Node> queue = new LinkedList();
        root.marked = true;
        queue.add(root);

        while (!queue.isEmpty()) {
            Node node = ((LinkedList<Node>) queue).pop();
            System.out.println(node.data);
            if(node.adjacent != null) {
                for (Node child : node.adjacent) {
                    if (child.marked == false) {
                        child.marked = true;
                        queue.add(child);
                    }
                }
            }
        }
    }

    public static boolean routeBetweenTwoNodes(Node root,  int first, int second) {
        if (first == second) return true;

        boolean foundFirst = false;
        boolean foundSecond = false;

        Queue<Node> queue = new LinkedList();
        root.marked = true;
        queue.add(root);

        while (!queue.isEmpty()) {
            Node node = ((LinkedList<Node>) queue).pop();
            System.out.println(node.data);

            if(node.data == first) {
                foundFirst = true;
            }

            if(node.data == second) {
                foundSecond = true;
            }

            if(node.adjacent != null) {
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
}
