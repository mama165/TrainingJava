package fr.kata.decisiontree.domain.models;

class LeafNodeBuilder extends BaseNodeBuilder {
    private LeafLine leafLine;

    public LeafNodeBuilder(LeafLine leafLine) {
        this.leafLine = leafLine;
    }

    public Node build() {
        return new LeafNode(leafLine.getValue());
    }
}