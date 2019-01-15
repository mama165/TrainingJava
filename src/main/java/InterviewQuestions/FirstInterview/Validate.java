package InterviewQuestions.FirstInterview;

import InterviewQuestions.FirstInterview.exceptions.IllegalExtensionFile;
import InterviewQuestions.FirstInterview.features.IValidateFile;
import org.apache.commons.io.FilenameUtils;

import java.io.File;

public class Validate implements IValidateFile {
    public void validate(File file) throws IllegalExtensionFile {
        String extension = FilenameUtils.getExtension(file.getPath());

        if (!"txt".equals(extension)) {
            throw new IllegalExtensionFile(extension);
        }
    }
}
