package CrackingTheCodingInterview.TreesAndGraphs;

import java.util.*;
import java.util.stream.Stream;

public class BinaryNode {
    public int data;
    public BinaryNode left;
    public BinaryNode right;
    public BinaryNode parent;
    public boolean visited;

    public BinaryNode(int data) {
        this.data = data;
    }

    public String inOrderTraversal(BinaryNode root) {
        return inOrderTraversal(root, new StringBuilder());
    }

    public String inOrderTraversal(BinaryNode root, StringBuilder sb) {
        if (root != null) {
            inOrderTraversal(root.left, sb);
            sb.append(root.data);
            inOrderTraversal(root.right, sb);
        }
        return sb.toString();
    }

    public String inOrderTraversalNoRecursive(BinaryNode root) {
        StringBuilder sb = new StringBuilder();
        Stack<BinaryNode> stack = new Stack<>();
        stack.add(root);

        while (!stack.isEmpty()) {
            root.visited = true;
            if (root.left != null && !root.left.visited) {
                stack.add(root);
                root = root.left;
                continue;
            }

            sb.append(root.data);

            if (root.right != null && !root.right.visited) {
                root = root.right;
                continue;
            }
            root = stack.pop();
        }

        return sb.toString();
    }

    public String preOrderTraversal(BinaryNode root) {
        return preOrderTraversal(root, new StringBuilder());
    }

    public String preOrderTraversal(BinaryNode root, StringBuilder sb) {
        if (root != null) {
            sb.append(root.data);
            preOrderTraversal(root.left, sb);
            preOrderTraversal(root.right, sb);
        }
        return sb.toString();
    }

    public String preOrderTraversalNoRecursive(BinaryNode root) {
        StringBuilder sb = new StringBuilder();
        Stack<BinaryNode> stack = new Stack<>();
        stack.add(root);

        while (!stack.isEmpty()) {
            if (!root.visited) sb.append(root.data);

            root.visited = true;

            if (root.left != null && !root.left.visited) {
                stack.add(root);
                root = root.left;
                continue;
            }

            if (root.right != null && !root.right.visited) {
                stack.add(root);
                root = root.right;
                continue;
            }

            root = stack.pop();

        }

        return sb.toString();
    }

    public String postOrderTraversal(BinaryNode root) {
        return postOrderTraversal(root, new StringBuilder());
    }

    public String postOrderTraversal(BinaryNode root, StringBuilder sb) {
        if (root != null) {
            postOrderTraversal(root.left, sb);
            postOrderTraversal(root.right, sb);
            sb.append(root.data);
        }
        return sb.toString();
    }

    public String postOrderTraversalNoRecursive(BinaryNode root) {
        StringBuilder sb = new StringBuilder();
        Stack<BinaryNode> stack = new Stack<>();
        stack.add(root);

        while (!stack.isEmpty()) {
            root.visited = true;

            if (root.left != null && !root.left.visited) {
                stack.add(root);
                root = root.left;
                continue;
            }

            if (root.right != null && !root.right.visited) {
                stack.add(root);
                root = root.right;
                continue;
            }

            sb.append(root.data);
            root = stack.pop();
        }
        return sb.toString();
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


    public static boolean validateBSTRecursive(BinaryNode root) {
        if (root != null) {
            if (root.left != null && root.left.data > root.data) {
                return false;
            }
            if (root.right != null && root.right.data < root.data) {
                return false;
            }
            validateBSTRecursive(root.left);
            validateBSTRecursive(root.right);
        }
        return true;
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

    public static BinaryNode successor(BinaryNode root, int data) {
        if (root != null) {
            successor(root.left, data);
            if (root.data == data) {
                // Value found !
                return reverseEngineer(root);
            }
            successor(root.right, data);
        }
        return new BinaryNode(1);
    }

    public static BinaryNode reverseEngineer(BinaryNode node) {
        BinaryNode root = node;
        int data = root.data;

        while (root.parent != null) {
            root = root.parent;
        }

        return findSuccessor(root, data);
    }

    public static BinaryNode findSuccessor(BinaryNode node, int data) {
        if (node != null) {
            findSuccessor(node.left, data);
            if (node.data > data) {
                // on a trouvé le prochain
                return node;
            }
            findSuccessor(node.right, data);
        }
        return null;
    }

    public int[] shortestPath(int value) {
        BinaryNode root = this;
        Stack<BinaryNode> stack = new Stack<>();
        stack.add(root);

        while (!stack.isEmpty()) {
            root.visited = true;

            if (root.data == value) {
                stack.add(root);
                return convert(stack);
            }

            if (root.left != null && !root.left.visited) {
                stack.add(root);
                root = root.left;
                continue;
            }

            if (root.right != null && !root.right.visited) {
                stack.add(root);
                root = root.right;
                continue;
            }

            root = stack.pop();
        }

        return null;
    }

    public int[] convert(Stack<BinaryNode> stack) {
        LinkedList<Integer> list = new LinkedList();
        for (BinaryNode node : stack) {
            list.add(node.data);
        }

        int[] a = new int[list.size()];

        for (int i = 0; i < list.size(); i++) {
            a[i] = list.get(i);
        }

        return a;
    }

    /**
     * Problem Statement : Given a Binary Search Tree, keyed on positive integers.
     * The task is to find the Shortest path in Binary Search Tree which adds up to the number K.
     * If no such path exists, return a message accordingly.
     * @param node
     * @param value
     * @return
     */
    public int[] shortestSum(BinaryNode node, int value) {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BinaryNode that = (BinaryNode) o;
        return data == that.data &&
                Objects.equals(left, that.left) &&
                Objects.equals(right, that.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data, left, right);
    }
}