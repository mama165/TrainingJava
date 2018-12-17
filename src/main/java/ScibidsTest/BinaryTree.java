package ScibidsTest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BinaryTree {
    Node root;


    void printPaths(Node node) {
        int path[] = new int[1000];
        Node[] path_test = new Node[1000];
        printPathsRecur(node, path, path_test, 0);
    }

    void printPathsRecur(Node node, int path[],  Node[] path_test, int pathLen) {
        if (node == null)
            return;

        /* append this node to the path array */
        path[pathLen] = node.data;
        path_test[pathLen] = node;
        pathLen++;

        /* it's a leaf, so print the path that led to here  */
        if (node.left == null && node.right == null)
            printArray(path, path_test, pathLen);
        else {
            /* otherwise try both subtrees */
            printPathsRecur(node.left, path, path_test, pathLen);
            printPathsRecur(node.right, path, path_test, pathLen);
        }
    }

    /* Utility function that prints out an array on a line. */
    void printArray(int ints[], Node[] path_test, int len) {
        int i;
        String str = "";
        for (i = 0; i < len; i++) {
            Node node = path_test[i];
            if(node.left != null || node.right != null){
                str = str + Boolean.toString(node.leftValue ? node.feature : !node.feature) + " ";
            }
            else {

            }

            System.out.println(str);

            //System.out.print(path_test[i].data + " ");
        }
        System.out.println("");
    }

    // driver program to test above functions
    public static void main(String args[]) {
        BinaryTree tree = new BinaryTree();

        /////////////////
        tree.root = new Node(0);

        tree.root.left = new Node(1);
        tree.root.right = new Node(2);

        tree.root.left.left = new Node(3);
        tree.root.left.right = new Node(4);

        tree.root.right.left = new Node(5);
        tree.root.right.right = new Node(6);

        tree.root.feature = true;
        tree.root.left.feature = true;
        tree.root.right.feature = true;
        tree.root.right.left.feature = true;
        tree.root.right.right.feature = true;

        tree.root.leftValue = true;
        tree.root.left.leftValue = true;
        tree.root.right.leftValue = true;
        tree.root.right.left.leftValue = true;
        tree.root.right.right.leftValue = true;


        /* Let us test the built tree by printing Insorder traversal */
        tree.printPaths(tree.root);

        List<String> list = new ArrayList<>();

        try (Stream<String> stream = Files.lines(Paths.get("C:\\Users\\Mael\\Desktop\\TrainingJava\\src\\ScibidsTest\\tree_to_convert.txt"))) {

            //1. filter line 3
            //2. map all content to upper case
            //3. map it into a List
            list = stream
                    //.map(line -> line.split("\t")[1])
                    .collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }

        list.forEach(System.out::println);

    }
}