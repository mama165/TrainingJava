package InterviewQuestions.ForthInterview.exceptions;

public class ParameterException extends Exception {
    Integer value;

    public ParameterException(Integer value) {
        this.value = value;

    }

    @Override
    public String toString() {
        return "This input is not valid [" + value + "]";
    }
}
