package CrackingTheCodingInterview.TreesAndGraphs;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Deque;
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

        Deque<Node> queue = new LinkedList();
        root.marked = true;
        queue.add(root);

        while (!queue.isEmpty()) {
            Node node = queue.pop();
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

    public static Node minimalTree(int[] values) {

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
                minimalTree(subNodesLeft),
                minimalTree(subNodesRight)
        };

        return root;
    }

    public static LinkedList[] listOfDepths(Node root) {

        LinkedList<ArrayList<Node>> list = new LinkedList();
        root.marked = true;


        /*

                    A
                B       C
            D   E       F   G
            HI  JK      LM  NO


        */
        Deque<Node> q1 = new LinkedList();
        q1.add(root);

        Deque<Node> q2 = new LinkedList();
        q2.add(root);

        ArrayList<Node> nodes = new ArrayList();

        while (!q1.isEmpty()) {

            Node n1 = q1.pop();


            //***************
            Node n2 =  q2.pop();
            nodes.add(n2); // On ajoute directement l'élément  dans la liste

            if (q2.isEmpty()) {
                // On  ajoute à la liste des noeuds
                list.add(nodes);
                // On réinit la liste
                nodes = new ArrayList();
                // On doit ajouter tout ce qui est dans la derniere queue
//                ((LinkedList<Node>) q2).add(n1);

            } else {
                // Non vide !
                // On doit ajouter à la liste car il reste encore des enfants !!
            }
            //***************

            if(n1.adjacent == null) {
                // On arrive aux feuilles
                // On ajoute tous les éléments de "q1" dans la liste
            }

            if (n1.adjacent != null) {
                for (Node child : n1.adjacent) {
                    if (child.marked == false) {
                        child.marked = true;
                        q1.add(child);

                        if(q2.isEmpty()) {
                            // On ajoute seulement si c'est vide
                            // On quitte cette ligne
                            q2.add(child);
                        }
                    }
                }
            }
        }

        return null;
    }
}
