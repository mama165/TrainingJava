package InterviewQuestions;

import InterviewQuestions.model.FileHandler;
import InterviewQuestions.model.ToDeleteClass;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) /*throws IOException*/ {
        ToDeleteClass toDeleteClass = new ToDeleteClass();
        toDeleteClass.DoSomething();

        
        // String path = "/home/takima/Documents/projetsPerso/CrackingTheCodingInterview/src/main/java/InterviewQuestions/tree_to_convert.txt";
        // FileHandler fileHandler = new FileHandler(path);
        // fileHandler.extractLines();
    }


    public static void parseLine(String line) {
        // https://regex101.com/
        // regex ([0-9]+):\[(.*?)\] (yes)=([0-9]+),(no)=([0-9]+)
        // exemple : 0:[device_type=pc||or||browser=7] yes=2,no=1

        Pattern p = Pattern.compile("([0-9]+):\\[(.*?)\\] (yes)=([0-9]+),(no)=([0-9]+)");

        Matcher m = p.matcher(line);

        if(m.find()) {
//            System.out.println(line);
//            System.out.println(m.group(0));
//            System.out.println(m.group(1));
//            System.out.println(m.group(2));
//            System.out.println(m.group(3));
//            System.out.println(m.group(4));
//            System.out.println(m.group(5));
//            System.out.println(m.group(6));
//            System.exit(1);
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
