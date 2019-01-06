package InterviewQuestions.ForthInterview.features;

import InterviewQuestions.ForthInterview.beans.Buyer;
import InterviewQuestions.ForthInterview.beans.Parameters;
import InterviewQuestions.ForthInterview.exceptions.ParameterException;
import InterviewQuestions.ForthInterview.services.IChecker;

import java.util.List;

public class Checker implements IChecker {
    @Override
    public void check(Parameters parameters) throws ParameterException {
        List<Buyer> buyers = parameters.getBuyers();
        Integer reservePrice = parameters.getReservePrice();

        if (!atLeastOneBid(buyers)) {
            throw new ParameterException(reservePrice);
        } else {
            int highestBid = findMaximum(buyers);
            if (reservePrice <= 0
                    | highestBid == 0
                    | highestBid < reservePrice
                    | buyers.size() < 2) {
                throw new ParameterException(reservePrice);
            }
        }
    }

    public boolean atLeastOneBid(List<Buyer> buyers) {
        for (Buyer buyer : buyers) {
            if(buyer.getBids().isEmpty()) {
                return false;
            }
        }
        return true;
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
