package TreesAndGraphs.ParameterBinaryNode;

import CrackingTheCodingInterview.TreesAndGraphs.BinaryNode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SuccessorTest {


    @ParameterizedTest
    @MethodSource("createNodes")
    @DisplayName("Test validate a Binary search tree")
    public void testSuccessorRetrieve(BinaryNode root, int data, int expected) {
        BinaryNode output = root.successor(root, data);
        assertEquals(expected, output);
    }

    private static Stream<Arguments> createNodes() {
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

        return Stream.of(
                Arguments.of(root, 8, 10),
                Arguments.of(root, 14, 1));
    }
}
