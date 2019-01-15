package InterviewQuestions.FirstInterview;

import InterviewQuestions.FirstInterview.exceptions.IllegalExtensionFile;
import InterviewQuestions.FirstInterview.features.IExtract;
import InterviewQuestions.FirstInterview.features.IReadFile;
import InterviewQuestions.FirstInterview.features.IValidateFile;
import InterviewQuestions.FirstInterview.models.Rows;

import java.io.File;
import java.io.IOException;

public class Extract implements IExtract {
    IValidateFile validateFile;
    IReadFile readFile;

    public Extract(IValidateFile foo, IReadFile bar) {
        this.validateFile = foo;
        this.readFile = bar;
    }

    public Rows extractLines(File file) throws IllegalExtensionFile, IOException {
        validateFile.validate(file);
        return readFile.read(file);
    }
}
