import Trees.Node;

public class Main {

    public static void main(String[] args) {
        Node node = new Node(10);
        node.insert(5);
        node.insert(8);
        node.insert(15);

        node.printInOrder();

        System.out.println(node.contains(3));

    }

}
