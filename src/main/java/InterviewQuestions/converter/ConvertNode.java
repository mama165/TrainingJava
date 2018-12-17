package InterviewQuestions.converter;

import InterviewQuestions.model.Node;

import java.util.regex.Matcher;

public interface ConvertNode {

    public Node convertIntoNode(Matcher matcher);

    public Node convert(String row);
}
