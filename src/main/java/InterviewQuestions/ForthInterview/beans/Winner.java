package InterviewQuestions.ForthInterview.beans;

import java.util.Objects;

public class Winner {
    private String name;
    Integer price;

    public Winner(String name, Integer price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Winner winner = (Winner) o;
        return Objects.equals(name, winner.name) &&
                Objects.equals(price, winner.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }
}
