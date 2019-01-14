package InterviewQuestions.FirstInterview;

import InterviewQuestions.FirstInterview.models.*;

public class Convert implements IConvert {
    public Node convert(Rows rows) {

        Integer rootIndex = rows.getRootIndex(); //0
        BaseLine rootLine = rows.getElement(rootIndex);
        Node rootNode = new ParentNodeBuilder(rows, (ParentLine) rootLine).Build();

        return rootNode;
    }

}
