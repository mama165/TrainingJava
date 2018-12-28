package TreesAndGraphs.ParameterBinaryNode;

import CrackingTheCodingInterview.TreesAndGraphs.BinaryNode;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class TraversalTreeTest {
    private BinaryNode root;

    @Before
    public void setup() {
        root = new BinaryNode(30);
        BinaryNode a = new BinaryNode(20);
        BinaryNode b = new BinaryNode(50);
        BinaryNode c = new BinaryNode(15);
        BinaryNode d = new BinaryNode(22);
        BinaryNode e = new BinaryNode(38);
        BinaryNode f = new BinaryNode(57);
        BinaryNode g = new BinaryNode(4);
        BinaryNode h = new BinaryNode(25);
        BinaryNode i = new BinaryNode(17);
        BinaryNode j = new BinaryNode(45);
        BinaryNode k = new BinaryNode(32);
        BinaryNode l = new BinaryNode(40);
        BinaryNode m = new BinaryNode(53);
        BinaryNode n = new BinaryNode(60);

        root.left = a;
        root.right = b;

        a.left = c;
        a.right = d;

        b.left = e;
        b.right = f;

        c.left = g;
        c.right = h;

        d.left = i;
        d.right = j;

        e.left = k;
        e.right = l;

        f.left = m;
        f.right = n;

    }

    @Test
    @DisplayName("Test if we can traverse the tree in order")
    public void testInOrderTraversal() {
        String expected = "41525201722453032384050535760";
        String output = root.inOrderTraversal(root);
        assertEquals(expected, output);
    }

    @Test
    @DisplayName("Test if we can traverse the tree pre order")
    public void testPreOrderTraversal() {
        String expected = "30201542522174550383240575360";
        String output = root.preOrderTraversal(root);
        assertEquals(expected, output);
    }

    @Test
    @DisplayName("Test if we can traverse the tree post order")
    public void testPostOrderTraversal() {
        String expected = "42515174522203240385360575030";
        String output = root.postOrderTraversal(root);
        assertEquals(expected, output);
    }
}
