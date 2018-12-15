package InterviewQuestions.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
        try (Stream<String> stream = Files.lines(Paths.get(path))) {
            stream.forEach(line -> parseLine(line));

        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    private void parseLine(String line) {
        String rgx = "([0-9]+):\\[(.*?)\\] (yes)=([0-9]+),(no)=([0-9]+)";
        Pattern p = Pattern.compile(rgx);
        Matcher m = p.matcher(line);

        if(m.find()) {
            Integer data = Integer.valueOf(m.group(0));
            String feature = String.valueOf(m.group(1));
            String e = String.valueOf(m.group(2));
            Node node = new Node.Builder(1, 1, 1);


//            System.out.println(line);
//            System.out.println(m.group(0));
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
            System.out.println(matcher.group(0));
            System.out.println(matcher.group(1));
            System.out.println(matcher.group(2));
//            System.out.println(m.group(3));
        }
    }
}
