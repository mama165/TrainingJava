package InterviewQuestions.FirstInterview.exceptions;

public class IllegalExtensionFile extends Exception {
    String extension;

    public IllegalExtensionFile(String extension) {
        this.extension = extension;

    }

    @Override
    public String toString() {
        return "This input is not valid [" + extension + "]";
    }
}
