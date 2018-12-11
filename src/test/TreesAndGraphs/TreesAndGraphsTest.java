package TreesAndGraphs;

import static org.junit.Assert.*;

import CrackingTheCodingInterview.TreesAndGraphs.Node;
import org.junit.Before;
import org.junit.Test;

public class TreesAndGraphsTest {

    private Node root;

    @Before
    public void setup() throws Exception {
        root = new Node(30);
        Node a = new Node(20);
        Node b = new Node(50);
        Node c = new Node(15);
        Node d = new Node(22);
        Node e = new Node(38);
        Node f = new Node(57);
        Node g = new Node(4);
        Node h = new Node(25);
        Node i = new Node(17);
        Node j = new Node(45);
        Node k = new Node(32);
        Node l = new Node(40);
        Node m = new Node(53);
        Node n = new Node(60);

        root.adjacent = new Node[]{a, b};
        a.adjacent = new Node[]{c, d};
        b.adjacent = new Node[]{e, f};
        c.adjacent = new Node[]{g, h};
        d.adjacent = new Node[]{i, j};
        e.adjacent = new Node[]{k, l};
        f.adjacent = new Node[]{m, n};
    }

    @Test
    public void testSearchBFS() {
        String expected = "30205015223857425174532405360";
        String output =  Node.searchBFS(root);
        assertEquals(expected, output);
    }

    @Test
    public void testListOfDepths() {
        assertTrue(Node.listOfDepths(root).size() == 4);
    }

    @Test
    public void testCheckBalanced() {
        assertTrue(Node.checkBalanced(root));
    }

    @Test
    public void testValidateBST() {

        assertTrue(Node.validateBST(root));
    }
}
