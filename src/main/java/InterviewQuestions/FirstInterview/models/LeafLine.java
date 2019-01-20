package InterviewQuestions.FirstInterview.models;

public class LeafLine extends BaseLine {
    private double value;

    public LeafLine(Integer data, double value) {
        super(data);
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public boolean isLeaf() {
        return true;
    }
}
