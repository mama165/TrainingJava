package CrackingTheCodingInterview.TreesAndGraphs;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Node {
    public int data;
    public Node[] adjacent;
    public boolean marked;

    public Node(int data) {
        this.data = data;
    }

    public Node(Node node) {
        this.data = node.data;
        this.adjacent = node.adjacent;
        this.marked = node.marked;
    }

    public void searchDFS(Node root) {
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

    public String searchBFS() {
        Node root = this;

        Deque<Node> queue = new LinkedList();
        root.marked = true;
        queue.add(root);

        StringBuilder sb = new StringBuilder();

        while (!queue.isEmpty()) {
            Node node = queue.pop();
            sb.append(node.data);
            if (node.adjacent != null) {
                for (Node child : node.adjacent) {
                    if (child.marked == false) {
                        child.marked = true;
                        queue.add(child);
                    }
                }
            }
        }
        return sb.toString();
    }

    public boolean routeBetweenTwoNodes(int first, int second) {
        if (first == second) return true;

        Node root = this;
        boolean foundFirst = false;
        boolean foundSecond = false;

        Deque<Node> queue = new LinkedList();
        root.marked = true;
        queue.add(root);

        while (!queue.isEmpty()) {
            Node node = queue.pop();
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

    public LinkedList<Node[]> listOfDepths() {

        Node root = this;
        LinkedList<Node[]> results = new LinkedList<Node[]>();
        /*

                    A           -debut de boucle
                B       C       -fin de boucle
            D   E       F   G
            HI  JK      LM  NO

        */
        results.add(new Node[]{root});

        LinkedList<Node> intermediateList = new LinkedList<Node>();
        intermediateList.add(root);

        do {
            //A chaque itération, on se trouve dans un étage différent.

            //todo faire en sorte que l'intermedaite list contiennent seulement les nodes de la rangée encore.

            LinkedList<Node> tmpList = new LinkedList<Node>();
            for (Node node : intermediateList) { // { HI  JK      LM  NO }
                for (Node n : node.adjacent) { //{   }
                    tmpList.add(n);  //vide
                }
            }

            intermediateList = tmpList;  //vide

            //rajouter dans results
            if (!intermediateList.isEmpty()) {
                results.add((Node[]) intermediateList.toArray());
            }
        }
        while (!intermediateList.isEmpty());

        return results;
    }




    public boolean checkBalanced() {
        Node root = this;

        ArrayList<Node> nodes = new ArrayList();
        ArrayList<Node> tmpnodes = new ArrayList();
        nodes.add(root);

        do {
            for (Node node : nodes) {
                if (node.adjacent != null) {
                    for (Node child : node.adjacent) {
                        if (child.adjacent != null) {
                            if (child.adjacent.length == 1
                                    && child.adjacent[0].adjacent != null) {
                                return false;
                            }
                        }
                        tmpnodes.add(child);
                    }
                }
                nodes = tmpnodes;
                tmpnodes = new ArrayList<>();
            }
        } while (!nodes.isEmpty());
        return true;
    }
}
