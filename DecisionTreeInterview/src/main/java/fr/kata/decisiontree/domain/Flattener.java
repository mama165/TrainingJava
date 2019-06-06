package fr.kata.decisiontree.domain;

import fr.kata.decisiontree.domain.models.BaseLine;
import fr.kata.decisiontree.domain.models.Node;
import fr.kata.decisiontree.domain.models.ParentLine;
import fr.kata.decisiontree.domain.models.ParentNodeBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Flattener implements IFlattener {
    @Override
    public List<String> flatten(List<String> lines) {
        Map<Integer, BaseLine> baseLineMap = lines.stream()
                .map(BaseLineMapper::toBaseLine)
                .collect(Collectors.toMap(BaseLine::getData, Function.identity()));

        BaseLine rootLine = baseLineMap.get(0);
        Node root = new ParentNodeBuilder(baseLineMap, (ParentLine) rootLine).build();

        return buildRecursivelyConditions(root);
    }

    private List<String> buildRecursivelyConditions(Node root) {
        return buildRecursivelyConditions(root, new ArrayList<>());
    }

    private List<String> buildRecursivelyConditions(Node root, ArrayList<Node> list) {
        if (root == null) return null;

        list.add(root);
        if (root.getYes() == null && root.getNo() == null) {
            printlnArray(arr);
        } else {
            printPaths(r.left, arr);
            printPaths(r.right, arr);
        }

        arr.remove(arr.size() - 1);

    }
}
