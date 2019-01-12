package InterviewQuestions.FirstInterview.models;

public class ParentLine extends BaseLine {
    private Integer yes;
    private Integer no;
    private String feature;

    public ParentLine(Integer data, Integer yes, Integer no, String feature) {
        super(data);
        this.yes = yes;
        this.no = no;
        this.feature = feature;
    }

    public Integer getYes() {
        return yes;
    }

    public void setYes(Integer yes) {
        this.yes = yes;
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }
}