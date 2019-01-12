package InterviewQuestions.FirstInterview;

import InterviewQuestions.FirstInterview.models.*;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Mapper implements IMapper {
    @Override
    public Node build(Rows rows) {
        List<Node> nodes = rows.createNode();
        Node node = buildTree(nodes);
        return node;
    }



    public Node buildTree(List<Node> nodes) {

        return null;
    }

    private void search(Node node, Rows rows) {
        if (node instanceof ParentNode) {
            ParentNode parentNode = (ParentNode) node;

            int dataYes = parentNode.getYes().getNodeNumber();
            int dataNo = parentNode.getNo().getNodeNumber();

            Row rowYes = findRow(dataYes, rows);
            Row rowNo = findRow(dataNo, rows);

            Node nodeYes = convert(rowYes);
            Node nodeNo = convert(rowNo);

            parentNode.setYes(nodeYes);
            parentNode.setNo(nodeNo);

            search(nodeYes, rows);
            search(nodeNo, rows);
        }
    }

    public Node convert(Row row) {
        Node node = null;
        String value = row.getLine();

        String regexParent =  "([0-9]+):\\[(.*?)\\] (yes)=([0-9]+),(no)=([0-9]+)";
        String regexLeaf = "([0-9]+):(leaf)=([+-]?[1-9][0-9]*|0[.,][0-9]+)";

        Pattern patternParent = Pattern.compile(regexParent);
        Matcher matcherParent = patternParent.matcher(value);

        Pattern patternLeaf = Pattern.compile(regexLeaf);
        Matcher matcherLeaf = patternLeaf.matcher(value);


        if(matcherParent.find()) {
            int data = Integer.parseInt(matcherParent.group(1));
            String feature = String.valueOf(matcherParent.group(2));
            int yes = Integer.parseInt(matcherParent.group(4));
            int no = Integer.parseInt(matcherParent.group(6));

            node = new ParentNode.Builder()
                    .addData(data)
                    .withFeature(feature)
                    .build();
        }
        if (matcherLeaf.find()) {
            int data = Integer.parseInt(matcherLeaf.group(1));
            Double element = Double.parseDouble(matcherLeaf.group(3));
            node = new LeafNode(element, data);
        }




        return node;
    }

    public Row findRow(int data, Rows rows) {

        rows.getRows().stream().forEach(row -> {

        });


        return new Row();
    }
}
