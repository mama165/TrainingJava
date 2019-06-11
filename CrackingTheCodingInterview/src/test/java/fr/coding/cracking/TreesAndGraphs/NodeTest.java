package fr.coding.cracking.TreesAndGraphs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NodeTest {
    private Node root;

    @BeforeEach
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
    public void testRouteBetweenNodes() {
        assertTrue(root.routeBetweenTwoNodes(20, 25));
    }

    @Test
    public void testSearchBFS() {
        String expected = "30205015223857425174532405360";
        String output =  root.searchBFS();
        assertEquals(expected, output);
    }

    @Test
    public void testListOfDepths() {
        assertTrue(root.listOfDepths().size() == 4);
    }

    @Test
    public void testCheckBalanced() {
        assertTrue(root.checkBalanced());
    }
}