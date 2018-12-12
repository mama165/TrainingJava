package TreesAndGraphs.ParameterBinaryNode;

import CrackingTheCodingInterview.TreesAndGraphs.BinaryNode;
import CrackingTheCodingInterview.TreesAndGraphs.Node;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class MinimalTreeTest {
    private LinkedList<Integer> values;
    private BinaryNode expected;

    public MinimalTreeTest(LinkedList<Integer> values, BinaryNode expected) {
        this.values = values;
        this.expected = expected;
    }

    @Parameters
    public static Collection<Object[]> valuesSamples() {
        Integer[] myArray = {1, 3, 4, 6, 7, 8, 10, 13, 14};
        LinkedList<Integer> values = new LinkedList(Arrays.asList(myArray));

        BinaryNode node = new BinaryNode(1);

        return Arrays.asList(new Object[][]{
                {values, node},
                {values, node},
        });
    }

    @Test
    public void testMinimalTree() {
        Node output = this.expected.minimalTree(values);

        assertEquals(expected, output);
    }
}
