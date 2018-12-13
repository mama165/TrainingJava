package TreesAndGraphs.ParameterBinaryNode;

import CrackingTheCodingInterview.TreesAndGraphs.BinaryNode;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class SuccessorTest {
    private BinaryNode root;
    private int data;
    private int expected;

    public SuccessorTest(BinaryNode root, int data, int expected) {
        this.root = root;
        this.data = data;
        this.expected = expected;
    }

    @Parameters
    public static Collection<Object[]> valuesSamples() {


        BinaryNode root = new BinaryNode(20);
        BinaryNode a = new BinaryNode(8);
        BinaryNode b = new BinaryNode(22);
        BinaryNode c = new BinaryNode(4);
        BinaryNode d = new BinaryNode(12);
        BinaryNode e = new BinaryNode(10);
        BinaryNode f = new BinaryNode(14);

        root.left = a;
        root.right = b;

        a.left = c;
        a.right = d;
        a.parent = root;

        b.parent = root;

        c.parent = a;

        d.left = e;
        d.right = f;
        d.parent = a;

        e.parent = e;

        f.parent = d;

        return Arrays.asList(new Object[][]{
                {root, 8, 10},
//                {root, 14, 1}
        });
    }

    @Test
    public void testSuccessorRetrieve() {
        BinaryNode output = root.successor(root, data);
        assertEquals(expected, output);
    }
}
