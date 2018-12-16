package InterviewQuestions.model;

public class ParentNode extends Node {
    private Node yes;
    private Node no;
    private String feature;

    public ParentNode(Node yes, Node no, String feature, int nodeNumber) {
        super(nodeNumber);
        this.yes = yes;
        this.no = no;
        this.feature = feature;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.nodeNumber + ":" + this.feature + " " + "yes=" + this.yes.nodeNumber + "," + "no=" + this.no.nodeNumber + "\n");
        sb.append(this.yes.toString());
        sb.append(this.no.toString());
        return sb.toString();
    }
}