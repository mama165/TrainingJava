package InterviewQuestions.FirstInterview;

import InterviewQuestions.FirstInterview.exceptions.IllegalExtensionFile;
import InterviewQuestions.FirstInterview.models.Node;
import InterviewQuestions.FirstInterview.models.Rows;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import static java.util.Optional.*;

public class DecisionTree {
    Extract extract;
    Convert convert;
    Write write;

    public DecisionTree(Extract extract, Convert convert, Write write) {
        this.extract = extract;
        this.convert = convert;
        this.write = write;
    }

    public Optional<File> doCalcul(File file) {
        Rows rows = null;
        try {
            rows = extract.extractLines(file);
        } catch (IOException | IllegalExtensionFile e) {
            e.printStackTrace();
        }
        Node node = convert.convert(rows);

        Optional<File> optional = empty();
        try {
            optional = write.buildFile(node);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return optional;
    }
}
