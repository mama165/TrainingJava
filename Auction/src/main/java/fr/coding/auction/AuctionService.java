package fr.coding.auction;

import java.util.List;
import java.util.Map;

class AuctionService {
    private final BidStarter bidStarter;

    AuctionService(BidStarter bidStarter) {
        this.bidStarter = bidStarter;
    }

    Winner find(Map<String, List<String>> input, String reservePrice) throws NegativePriceException {
        Price reservePriceConverted = Price.create(reservePrice);
        Bids bids = Bids.create(input);
        return bidStarter.acquire(bids.getBidsValue(), reservePriceConverted.getPriceValue());
    }
}
