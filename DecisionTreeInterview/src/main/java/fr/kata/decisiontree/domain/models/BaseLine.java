package fr.kata.decisiontree.domain.models;

public abstract class BaseLine {
    private Integer data;

    public BaseLine() {
    }

    public BaseLine(Integer data) {
        this.data = data;
    }

    public Integer getData() {
        return data;
    }

    public void setData(Integer data) {
        this.data = data;
    }

    public abstract boolean isLeaf();
}
