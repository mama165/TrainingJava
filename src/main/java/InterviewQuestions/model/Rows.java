package InterviewQuestions.model;

import java.util.LinkedList;

public class Rows {
    LinkedList<Row> rows;

    public void addRow(Row row) {
        this.getRows().add(row);
//        this.rows.add(row);
    }

    public void setRows(LinkedList<Row> rows) {
        this.rows = rows;
    }

    public LinkedList<Row> getRows() {
        return rows;
    }
}
