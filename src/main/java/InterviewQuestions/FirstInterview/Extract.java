package InterviewQuestions.FirstInterview;

import InterviewQuestions.FirstInterview.models.Rows;
import java.io.IOException;

public class Extract  implements IExtract {
    IValidateFile validateFile;
    IReadFile readFile;

    public Extract(IValidateFile foo, IReadFile bar) {
        this.validateFile = foo;
        this.readFile = bar;
    }

    public Rows extractLines(String path) throws IOException {
        validateFile.validate(path);
        return readFile.read(path);
    }
}
