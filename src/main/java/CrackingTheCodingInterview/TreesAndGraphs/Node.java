package CrackingTheCodingInterview.TreesAndGraphs;

import java.util.LinkedList;
import java.util.Queue;

public class Node {
    public int data;
    public Node [] adjacent;
    public boolean marked;

    public Node(int data) {
        this.data = data;
    }

    public void searchDFS(Node root) {
        if (root == null) return;
        root.marked = true;

        for (Node node : root.adjacent) {
            if (node.marked == false) {
                searchDFS(node);
            }
        }
    }

    public void searchBFS(Node root) {
        Queue<Node> queue = new LinkedList();
        root.marked = true;
        queue.add(root);

        while (!queue.isEmpty()) {
            Node node = ((LinkedList<Node>) queue).pop();
            for (Node child : node.adjacent) {
                if (child.marked == false) {
                    child.marked = true;
                    queue.add(child);
                }
            }
        }
    }
}
