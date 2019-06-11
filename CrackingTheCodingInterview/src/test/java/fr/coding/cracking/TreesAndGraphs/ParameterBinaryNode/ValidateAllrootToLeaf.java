package fr.coding.cracking.TreesAndGraphs.ParameterBinaryNode;

import fr.coding.cracking.TreesAndGraphs.BinaryNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ValidateAllrootToLeaf {
    BinaryNode root;

    @BeforeEach
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
    public void testPrint() {
        //"30 20 15 4 30 20 15 25 30 20 22 17 30 20 22 45 30 50 38 32 30 50 38 40 30 50 57 53 30 50 57 60";
        String output = root.leafPaths();
        String expected = "302015430201525302022173020224530503832305038403050575330505760";
        assertEquals(expected, output);
    }
}
