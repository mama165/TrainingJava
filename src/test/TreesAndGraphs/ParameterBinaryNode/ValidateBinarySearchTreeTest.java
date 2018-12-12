package TreesAndGraphs.ParameterBinaryNode;

import CrackingTheCodingInterview.TreesAndGraphs.BinaryNode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ValidateBinarySearchTreeTest {
    private BinaryNode node;
    private boolean expected;

    public ValidateBinarySearchTreeTest(BinaryNode node, boolean expected) {
        this.node = node;
        this.expected = expected;
    }

    @Parameters
    public static Collection<Object[]> sampleNodes() {
        BinaryNode root = new BinaryNode(30);
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

        //****

        BinaryNode second = new BinaryNode(15);
        second.left = new BinaryNode(86);

        return Arrays.asList(new Object[][]{
                {root, true},
                {second, false},
                {new BinaryNode(1), true},
        });
    }

    @Test
    public void testValidateBST() {
        assertEquals(expected, node.validateBST());
    }

    @Test
    public void testValidateBSTRecursive() {
        assertEquals(expected, BinaryNode.validateBSTRecursive(node));
    }
}
