package InterviewQuestions.FirstInterview;

import InterviewQuestions.FirstInterview.models.Node;
import InterviewQuestions.FirstInterview.models.Rows;

import java.io.File;
import java.io.IOException;

public class DecisionTree {
    Extract extract;
    Convert convert;
    Write write;

    public DecisionTree(Extract extract, Convert convert, Write write) {
        this.extract = extract;
        this.convert = convert;
        this.write = write;
    }

    public File doCalcul(String path) {
        Rows rows = null;
        try {
            rows = extract.extractLines(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Node node = convert.convert(rows);
        return write.buildFile(node);
    }
}
