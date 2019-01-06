package InterviewQuestions.ForthInterview.features;

import InterviewQuestions.ForthInterview.beans.Buyer;
import InterviewQuestions.ForthInterview.beans.Parameters;
import InterviewQuestions.ForthInterview.beans.Winner;
import InterviewQuestions.ForthInterview.services.IAlgo;

import java.util.List;

public class Algo implements IAlgo {
    @Override
    public Winner calculate(Parameters parameters) {
        List<Buyer> buyers = parameters.getBuyers();
        Integer reservePrice = parameters.getReservePrice();

        Buyer winningBuyer = findWinner(buyers);
        buyers.remove(winningBuyer);
        Integer price = findMaximum(buyers);

        if (reservePrice > price) {
            price = reservePrice;
        }

        return new Winner(winningBuyer.getName(), price);
    }

    public Buyer findWinner(List<Buyer> buyers) {
        Buyer max = buyers.get(0);
        for (Buyer buyer : buyers) {
            if (buyer.maxBid() > max.maxBid()) {
                max = buyer;
            }
        }
        return max;
    }

    public Integer findMaximum(List<Buyer> buyers) {
        int max = buyers.get(0).maxBid();
        for (Buyer buyer : buyers) {
            int value = buyer.maxBid();
            if (value > max) {
                max = value;
            }
        }

        return max;
    }
}
