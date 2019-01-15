package InterviewQuestions.FirstInterview;

import InterviewQuestions.FirstInterview.exceptions.IllegalExtensionFile;
import InterviewQuestions.FirstInterview.features.IValidateFile;
import org.apache.commons.io.FilenameUtils;

public class Validate implements IValidateFile {
    public void validate(String path) throws IllegalExtensionFile {
        String extension = FilenameUtils.getExtension(path);

        if (!"txt\n".equals(extension)) {
            throw new IllegalExtensionFile(extension);
        }
    }
}
