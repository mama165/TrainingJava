package InterviewQuestions.mapper;

import InterviewQuestions.model.*;

import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NodeMapper  implements GenericMapper<Node, Rows > {

    @Override
    public Node map(Rows rows) {
        LinkedList<Row> rowList = rows.getRows();

        Row row = rowList.get(0);
        Node node =  convert(row);

//        rowList.stream().forEach(row -> {
//
//            System.out.println("");
//        });

        return null;
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
}
