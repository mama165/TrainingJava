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
        String value = row.getLine();
        String [] rgxs = {
                "([0-9]+):\\[(.*?)\\] (yes)=([0-9]+),(no)=([0-9]+)",
                "([0-9]+):(leaf)=([+-]?[1-9][0-9]*|0[.,][0-9]+)"
        };

        for(String regex  : rgxs) {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(value);
            if(m.find()) {
                if(true) {
                   Node parentNode = new ParentNode();
                } else {
                    Node leaf = new LeafNode();

                }
            }
        }

        return null;
    }
}
