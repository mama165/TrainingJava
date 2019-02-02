package TreesAndGraphs.ParameterBinaryNode;


import CrackingTheCodingInterview.TreesAndGraphs.BinaryNode;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class FirstCommonAncestorTest {
    private static Stream<Arguments> createValues() {
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
        return Stream.of(
                Arguments.of(root, 20, 50, root),
                Arguments.of(root, 4, 32, root),
                Arguments.of(root, 38, 57, root.right),
                Arguments.of(root, 17, 15, root.left),
                Arguments.of(root, 32, 40, root.right.left),
                Arguments.of(root, 53, 4, root));
    }

    @ParameterizedTest
    @MethodSource("createValues")
    @DisplayName("Test find the first common ancestor")
    public void given_node_should_return_first_common_ancestor(BinaryNode root, int p, int q, BinaryNode expected) {
        BinaryNode output = root.firstCommonAncestor(p, q);
        assertEquals(expected, output);
    }
}
