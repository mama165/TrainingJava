package fr.kata.decisiontree.domain.models;

public class ParentLine extends BaseLine {
    private int yes;
    private int no;
    public ParentLine(Integer data, Integer yes, Integer no, String feature) {
        super(data);
        this.yes = yes;
        this.no = no;
        this.feature = feature;
    }

    public Integer getYes() {
        return yes;
    }

    public Integer getNo() {
        return no;
    }

    public String getFeature() {
        return feature;
    }

    private String feature;

    public ParentLine(int yes, int no, String feature) {
        this.yes = yes;
        this.no = no;
        this.feature = feature;
    }

    public ParentLine(Integer data, int yes, int no, String feature) {
        super(data);
        this.yes = yes;
        this.no = no;
        this.feature = feature;
    }

    @Override
    public boolean isLeaf() {
        return false;
    }
}
