package fr.kata.decisiontree.domain.models;

import java.util.Map;

abstract class BaseNodeBuilder {
    protected Map<Integer, BaseLine> baseLineMap;

    public abstract Node build();

    public BaseNodeBuilder() {
    }

    BaseNodeBuilder(Map<Integer, BaseLine> baseLineMap) {
        this.baseLineMap = baseLineMap;
    }
}
