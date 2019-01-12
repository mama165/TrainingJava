package InterviewQuestions.FirstInterview.models;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Rows {
    private List<Row> rowList;

    public Rows() {
    }

    public List<Row> getRows() {
        return rowList;
    }

    public void setRows(List<Row> rowList) {
        this.rowList = rowList;
    }

    public void addRow(Row row) {
        if (rowList.isEmpty()) {
            rowList = new ArrayList();
            rowList.add(row);
        } else {
            rowList.add(row);
        }
    }

    public List<Node> createNode() {
        Rows rows = this;
        List<Node> nodes = new LinkedList();
        rows.getRows().stream().forEach(row -> {
            Node node = row.buildNode();
            nodes.add(node);
        });

        return nodes;
    }
}
