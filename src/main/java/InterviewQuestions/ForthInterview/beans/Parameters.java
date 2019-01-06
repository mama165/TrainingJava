package InterviewQuestions.ForthInterview.beans;

import java.util.List;

public class Parameters {
    List<Buyer> buyers;
    Integer reservePrice;

    public Parameters(List<Buyer> buyers, Integer reservePrice) {
        this.buyers = buyers;
        this.reservePrice = reservePrice;
    }

    public List<Buyer> getBuyers() {
        return buyers;
    }

    public Integer getReservePrice() {
        return reservePrice;
    }

}
