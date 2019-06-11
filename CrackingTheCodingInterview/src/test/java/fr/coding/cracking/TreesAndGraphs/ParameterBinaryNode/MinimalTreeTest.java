package fr.coding.cracking.TreesAndGraphs.ParameterBinaryNode;

import fr.coding.cracking.TreesAndGraphs.BinaryNode;
import fr.coding.cracking.TreesAndGraphs.Node;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.stream.Stream;


public class MinimalTreeTest {
    private static Stream<Arguments> createValues() {
        Integer[] myArray = {1, 3, 4, 6, 7, 8, 10, 13, 14};
        LinkedList<Integer> values = new LinkedList(Arrays.asList(myArray));
        BinaryNode node = new BinaryNode(1);

        return Stream.of(
                Arguments.of(values, node),
                Arguments.of(values, node));
    }

    @ParameterizedTest
    @MethodSource("createValues")
    @DisplayName("Test for checking if a tree is minimal")
    public void given_tree_should_return_isMinimal(LinkedList<Integer> values, BinaryNode expected) {
        Node output = BinaryNode.minimalTree(values);

        assertEquals(expected, output);
    }
}
