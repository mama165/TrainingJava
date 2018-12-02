package CrackingTheCodingInterview.TreesAndGraphs;

public class Main {
    public static void main(String[] args) {
        Node root = new Node(0);
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);

        root.adjacent = new Node[]{
                node1,
                node2
        };

        node1.adjacent = new Node[]{
                node2,
                node3
        };

        node2.adjacent = new Node[]{
                node4
        };

        node3.adjacent = new Node[]{
                node2,
                node6
        };

        node4.adjacent = new Node[]{
                node3,
                node6,
                node5,
        };

//        Node.searchBFS(root);
//        Node.searchDFS(root);

        System.out.println(Node.routeBetweenTwoNodes(root, 4, 5));

    }
}
