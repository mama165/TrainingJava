package InterviewQuestions.FirstInterview.model;

public enum Orientation {
    YES("yes"),
    NO("no");

    private String symbol;

    Orientation(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
