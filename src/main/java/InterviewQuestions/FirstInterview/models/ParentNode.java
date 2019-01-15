    package InterviewQuestions.FirstInterview.models;

public class ParentNode extends Node {
    private Node yes;
    private Node no;
    private String feature;

    public static class Builder {
        private int data;
        private Node yes;
        private Node no;
        private String feature;


        public Builder() {
        }

        public Builder addData(int data) {
            this.data = data;
            return this;
        }

        public Builder yes(Node yes) {
            this.yes = yes;
            return this;
        }

        public Builder no(Node no) {
            this.no = no;
            return this;
        }

        public Builder withFeature(String feature) {
            this.feature = feature;
            return this;
        }

        public ParentNode build() {
            return new ParentNode(this);
        }
    }


    public ParentNode(Node yes, Node no, String feature, int nodeNumber) {
        super(nodeNumber);
        this.yes = yes;
        this.no = no;
        this.feature = feature;
    }


    private ParentNode(Builder builder) {
        super(builder.data);
        this.yes = builder.yes;
        this.no = builder.no;
        this.feature = builder.feature;
    }

    public Node getYes() {
        return this.yes;
    }

    public void setYes(Node yes) {
        this.yes = yes;
    }

    public ParentNode addYes(Node yes) {
        this.yes = yes;
        return this;
    }

    public Node getNo() {
        return this.no;
    }

    public void setNo(Node no) {
        this.no = no;
    }

    public ParentNode addNo(Node no) {
        this.no = no;
        return this;
    }

    public String getFeature() {
        return this.feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
//        sb.append(this.data + ":" + this.feature + " " + "yes=" + this.yes.data + "," + "no=" + this.no.data + "\n");
//        sb.append(this.yes.toString());
//        sb.append(this.no.toString());
        return sb.toString();
    }
}
