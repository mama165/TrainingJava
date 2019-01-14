package InterviewQuestions.FirstInterview.models;

import java.util.HashMap;

public class Rows {
    private HashMap<Integer, BaseLine> map = new HashMap();
    private Integer rootIndex = 0; //0

    public Integer getRootIndex() {
        return rootIndex;
    }

    public BaseLine getElement(Integer index) {
        return map.get(index);
    }

    public Rows() {
    }

    public void addRow(Integer index, BaseLine row) {
        map.put(index, row);
    }
}
