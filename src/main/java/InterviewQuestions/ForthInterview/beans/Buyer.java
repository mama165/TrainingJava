package InterviewQuestions.ForthInterview.beans;

import java.util.List;
import java.util.Objects;

public class Buyer {
    private String name;
    private List<Integer> bids;


    public Buyer(String name, List<Integer> bids) {
        this.name = name;
        this.bids = bids;
    }

    public String getName() {
        return name;
    }

    public List<Integer> getBids() {
        return this.bids;
    }

    public int maxBid() {
        Integer max = bids.get(0);
        for (Integer integer : bids) {
            int value = integer.intValue();
            if (value > max) {
                max = value;
            }
        }

        return max;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Buyer buyer = (Buyer) o;
        return Objects.equals(name, buyer.name) &&
                Objects.equals(bids, buyer.bids);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, bids);
    }
}
