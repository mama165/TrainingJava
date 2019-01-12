package InterviewQuestions.FirstInterview;

import InterviewQuestions.FirstInterview.models.BaseLine;
import InterviewQuestions.FirstInterview.models.LeafLine;
import InterviewQuestions.FirstInterview.models.ParentLine;
import InterviewQuestions.FirstInterview.models.Rows;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class ReadFile implements IReadFile {
    @Override
    public Rows read(String path) throws IOException {
        Rows rows = new Rows();
        HashMap<Integer, BaseLine> map = new HashMap();
        try (Stream<String> stream = Files.lines(Paths.get(path))) {
            stream.forEach(line -> {

                String regexParent = "([0-9]+):\\[(.*?)\\] (yes)=([0-9]+),(no)=([0-9]+)";
                String regexLeaf = "([0-9]+):(leaf)=([+-]?[1-9][0-9]*|0[.,][0-9]+)";

                Pattern patternParent = Pattern.compile(regexParent);
                Matcher matcherParent = patternParent.matcher(line);

                Pattern patternLeaf = Pattern.compile(regexLeaf);
                Matcher matcherLeaf = patternLeaf.matcher(line);

                if (matcherParent.find()) {
                    Integer parentData = Integer.parseInt(matcherParent.group(1));
                    Integer yes = Integer.parseInt(matcherParent.group(4));
                    Integer no = Integer.parseInt(matcherParent.group(6));
                    String feature = String.valueOf(matcherParent.group(2));

                    map.put(parentData, new ParentLine(parentData, yes, no, feature));
                }

                if (matcherLeaf.find()) {
                    Integer leafData = Integer.parseInt(matcherLeaf.group(1));
                    Double element = Double.parseDouble(matcherLeaf.group(3));

                    map.put(leafData, new LeafLine(leafData, element));
                }

                //                Row row = new Row(line);
                //                rows.addRow(row);
            });
        }
        return rows;
    }
}
