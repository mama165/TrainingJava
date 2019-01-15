package InterviewQuestions.FirstInterview.features;

import InterviewQuestions.FirstInterview.exceptions.IllegalExtensionFile;

import java.io.File;

public interface IValidateFile {
    void validate(File file) throws IllegalExtensionFile;
}
