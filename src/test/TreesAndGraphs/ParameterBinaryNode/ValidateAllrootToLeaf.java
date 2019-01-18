package TreesAndGraphs.ParameterBinaryNode;

import CrackingTheCodingInterview.TreesAndGraphs.BinaryNode;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;

public class ValidateAllrootToLeaf {
    BinaryNode root;
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
    public void testAllRootToLeaf() {
        ArrayList<int []> output = root.findAllRootToLeaf();
        ArrayList<int []> expected = new ArrayList();
        expected.add(new int[]{30, 20, 15, 4});
        expected.add(new int[]{30, 20, 15, 25});
        expected.add(new int[]{30, 20, 22, 17});
        expected.add(new int[]{30, 20, 22, 45});
        expected.add(new int[]{30, 50, 38, 32});
        expected.add(new int[]{30, 50, 38, 40});
        expected.add(new int[]{30, 50, 57, 53});
        expected.add(new int[]{30, 50, 57, 60});

        assertArrayEquals(expected.toArray(), output.toArray());

    }
}
