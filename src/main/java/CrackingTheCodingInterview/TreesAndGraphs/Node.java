package CrackingTheCodingInterview.TreesAndGraphs;

import java.lang.reflect.Array;
import java.util.*;

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

    public static Node minimalTree2(int[] values) {

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
     * @param values
     * @return
     */
    public static Node minimalTree(List<Integer> values) {

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

    public static LinkedList<Node[]> listOfDepths(Node root) {

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

    public static boolean checkBalanced(Node root) {
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
