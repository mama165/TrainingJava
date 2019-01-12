package InterviewQuestions.FirstInterview.models;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Row {
    private String line;

    public Row() {}

    public Row(String line) {
        this.line = line;
    }

    public String getLine() {
        return line;
    }

    public Node buildNode() {
        Row row = this;
        String line = row.getLine();
        Node node = null;

        String regexParent =  "([0-9]+):\\[(.*?)\\] (yes)=([0-9]+),(no)=([0-9]+)";
        String regexLeaf = "([0-9]+):(leaf)=([+-]?[1-9][0-9]*|0[.,][0-9]+)";

        Pattern patternParent = Pattern.compile(regexParent);
        Matcher matcherParent = patternParent.matcher(line);

        Pattern patternLeaf = Pattern.compile(regexLeaf);
        Matcher matcherLeaf = patternLeaf.matcher(line);

        if(matcherParent.find()) {
            int data = Integer.parseInt(matcherParent.group(1));
            String feature = String.valueOf(matcherParent.group(2));
            int yes = Integer.parseInt(matcherParent.group(4));
            int no = Integer.parseInt(matcherParent.group(6));

            node = new ParentNode.Builder()
                    .addData(data)
                    .withFeature(feature)
//                    .yes(yes)
//                    .no(no)
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
