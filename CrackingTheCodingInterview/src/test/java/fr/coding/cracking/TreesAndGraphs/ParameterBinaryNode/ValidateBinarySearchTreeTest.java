package fr.coding.cracking.TreesAndGraphs.ParameterBinaryNode;

import fr.coding.cracking.TreesAndGraphs.BinaryNode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ValidateBinarySearchTreeTest {

    @ParameterizedTest
    @MethodSource("createNodes")
    @DisplayName("Test validate a Binary search tree")
    public void given_nodes_should_validate_BST(BinaryNode node, boolean expected) {
        assertEquals(expected, node.validateBST());
    }

    private static Stream<Arguments> createNodes() {
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

        BinaryNode second = new BinaryNode(15);
        second.left = new BinaryNode(86);

        return Stream.of(
                Arguments.of(root, true),
                Arguments.of(second, false),
                Arguments.of(new BinaryNode(1), true));
    }

    @ParameterizedTest
    @MethodSource("createNodes")
    @DisplayName("Test validate a Binary search tree with recursive call")
    public void given_nodes_should_validate_BST_recursive(BinaryNode node, boolean expected) {
        assertEquals(expected, BinaryNode.validateBSTRecursive(node));

    }
}
