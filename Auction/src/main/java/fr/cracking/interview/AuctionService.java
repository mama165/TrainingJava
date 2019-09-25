package fr.cracking.interview;

import java.util.List;
import java.util.Map;

public class AuctionService {
    private final BidStarter bidStarter;

    public AuctionService(BidStarter bidStarter) {
        this.bidStarter = bidStarter;
    }

    public Winner find(Map<String, List<String>> input, String reservePrice) throws NegativePriceException {
        Price reservePriceConverted = Price.create(reservePrice);
        Bids bids = Bids.create(input);
        return bidStarter.acquire(bids.getBidsValue(), reservePriceConverted.getPriceValue());
    }
}
