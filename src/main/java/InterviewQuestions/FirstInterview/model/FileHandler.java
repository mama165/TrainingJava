package InterviewQuestions.FirstInterview.model;

import InterviewQuestions.FirstInterview.mapper.NodeMapper;
import InterviewQuestions.FirstInterview.mapper.RowMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class FileHandler {
    String path;

    public FileHandler(String path) {
        this.path = path;
    }

    public void extractLines() throws IOException {
        String path = this.path;
        Rows rows = new Rows();
        LinkedList<Row> rowList = new LinkedList();
        RowMapper rowMapper = new RowMapper();
        NodeMapper nodeMapper = new NodeMapper();



        try (Stream<String> stream = Files.lines(Paths.get(path))) {
            stream.forEach(line -> {
                Row row = getRowFromLine(rowMapper, line);
                rowList.add(row);
            });

            rows.setRows(rowList);



            Node root = nodeMapper.map(rows);


        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public Row getRowFromLine(RowMapper rowMapper, String line) {
        return rowMapper.map(line);
    }

    public void getNode() {

    }

    public void method(LinkedList<String> list) {
        String str = list.get(0);

        Node node = convert(str);

        search(node, list);


    }

    public void search(Node node, LinkedList<String> list) {
//        if(node instanceof LeafNode) {
//            // do Nothing
//        }
//        if(node instanceof ParentNode) {
//            int dataYes = node.getYes();
//            int dataNo = node.getNo();
//            String lineYes = findLine(list, dataYes);
//            String lineNo = findLine(list, dataNo);
//            Node nodeYes = convert(lineYes);
//            Node nodeNo = convert(lineNo);
//
//            node.yes = nodeYes;
//            node.no = nodeNo;
//
//            search(nodeYes, list);
//            search(nodeNo, list);
//        }
    }

    public String findLine(LinkedList<String> list, int data) {

        return null;
    }

    public Node convert(String line) {

        return null;
    }

    private void parseLine(String line) {
        //****

        Pattern pat = Pattern.compile("\t");
        Matcher mat = pat.matcher(line);

        int count = 1;
        while (mat.find()) {
            count++;
        }
        System.out.println("line :      " + line + " found : " + count + " occurences !");
        System.out.println(count);

        //****


        String rgx = "([0-9]+):\\[(.*?)\\] (yes)=([0-9]+),(no)=([0-9]+)";
        Pattern p = Pattern.compile(rgx);
        Matcher m = p.matcher(line);



        if(m.find()) {
//            Integer data = Integer.valueOf(m.group(0));
//            String withFeature = String.valueOf(m.group(1));
//            String e = String.valueOf(m.group(2));
//            Node node = new Node.Builder(1, 1, 1);


//            System.out.println(line);
//            System.out.println("group : " + m.group(0));
//            System.out.println(m.group(1));
//            System.out.println(m.group(2));
//            System.out.println(m.group(3));
//            System.out.println(m.group(4));
//            System.out.println(m.group(5));
//            System.out.println(m.group(6));
        }

        //**** leaf

        //regex : ([0-9]+):(leaf)=([+-]?[1-9][0-9]*|0[.,][0-9]+)

        Pattern pattern = Pattern.compile("([0-9]+):(leaf)=([+-]?[1-9][0-9]*|0[.,][0-9]+)");

        Matcher matcher = pattern.matcher(line);

        if(matcher.find()) {
//            System.out.println(line);
//            System.out.println(matcher.group(0));
//            System.out.println(matcher.group(1));
//            System.out.println(matcher.group(2));
//            System.out.println(m.group(3));
        }
    }
}
