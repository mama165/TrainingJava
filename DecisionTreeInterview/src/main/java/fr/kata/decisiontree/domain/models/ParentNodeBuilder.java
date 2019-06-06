package fr.kata.decisiontree.domain.models;

import java.util.Map;

public class ParentNodeBuilder extends BaseNodeBuilder {
    private ParentLine parentLine;

    public ParentNodeBuilder(Map<Integer, BaseLine> baseLineMap, ParentLine parentLine) {
        super(baseLineMap);
        this.parentLine = parentLine;
    }

    public Node build() {
        Node yes = buildNode(parentLine.getYes());
        Node no = buildNode(parentLine.getNo());

        return new ParentNode.Builder()
                .addData(parentLine.getData())
                .yes(yes)
                .no(no)
                .withFeature(parentLine.getFeature())
                .build();
    }

    private Node buildNode(Integer index) {
        BaseLine line = baseLineMap.get(index);

        if (!line.isLeaf()) {
            return new ParentNodeBuilder(baseLineMap, (ParentLine) line).build();
        } else {
            return new LeafNodeBuilder((LeafLine) line).build();
        }
    }
}

