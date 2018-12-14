package InterviewQuestions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Flatten {
    public static void main(String[] args) {

        String fileName = "/home/takima/Documents/projetsPerso/CrackingTheCodingInterview/src/main/java/InterviewQuestions/tree_to_convert.txt";

        //read file into stream, try-with-resources
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {

//            stream.forEach(System.out::println);
            stream.forEach(line-> print(line));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void print(String line) {
        System.out.println(line);
        System.out.println("line");
    }

    public static void parseLine(String line) {
        Pattern p = Pattern.compile("");

        Matcher m = p.matcher(line);

        if(m.find()) {
            System.out.println(m.group(0));
            System.out.println(m.group(1));
            System.out.println(m.group(2));
            System.out.println(m.group(3));
        }
    }
}
