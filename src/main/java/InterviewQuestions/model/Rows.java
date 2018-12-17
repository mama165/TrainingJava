package InterviewQuestions.model;

import java.util.LinkedList;

public class Rows {
    LinkedList<Row> rows;

    public void addRow(Row row) {
        rows.add(row);
    }

    public LinkedList<Row> getRows() {
        return rows;
    }
}
