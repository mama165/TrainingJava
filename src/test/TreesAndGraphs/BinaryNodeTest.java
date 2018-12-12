package TreesAndGraphs;

import CrackingTheCodingInterview.TreesAndGraphs.BinaryNode;
import CrackingTheCodingInterview.TreesAndGraphs.Node;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BinaryNodeTest {

    private BinaryNode root;

    @Before
    public void setup() throws Exception {
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
    public void testMinimalTree() {
        Integer [] myArray = {1, 3, 4, 6, 7, 8, 10, 13, 14};
        Node node = root.minimalTree(new LinkedList(Arrays.asList(myArray)));
        assertFalse(true);
    }

    @Test
    public void testValidateBST() {
        assertTrue(root.validateBST());
    }

    @Test
    public  void testValidateBSTRecursive() {
        assertTrue(BinaryNode.validateBSTRecursive(root));
    }

}
