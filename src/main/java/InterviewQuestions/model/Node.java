package InterviewQuestions.model;

public class Node {
    private int data;
    private String feature;
    private Orientation orientation; // yes or no pas obligatoire

    private Node() {
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public Bar getBar() {
        return bar;
    }

    public void setBar(Bar bar) {
        this.bar = bar;
    }

    public static class Builder {
        private int data;
        private String feature;
        private Bar bar;

        public Builder(int data, String feature, Bar bar) {
            this.data = data;
            this.feature = feature;
            this.bar = bar;
        }
    }

    private Node(Builder builder) {
        data = builder.data;
        feature = builder.feature;
        orientation = builder.orientation;
        bar = builder.bar;
    }
}
