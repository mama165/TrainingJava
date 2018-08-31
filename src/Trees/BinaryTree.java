package Trees;

public class BinaryTree {
    Node root;

    public BinaryTree(){
        this.root = root;
    }

    public BinaryTree(int key){
        this.root = new Node(key);
    }

    public void createBinaryTree(){
        BinaryTree binaryTree = new BinaryTree(1);
        binaryTree.root.left = new Node(2);
        binaryTree.root.right = new Node(3);
        binaryTree.root.left.left = new Node(4);
        binaryTree.root.left.right = new Node(5);
    }
}
