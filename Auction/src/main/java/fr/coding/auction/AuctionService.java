package fr.coding.auction;

import java.util.List;
import java.util.Map;

class AuctionService implements IFindWinner {
    private final BidStarter bidStarter;

    AuctionService(BidStarter bidStarter) {
        this.bidStarter = bidStarter;
    }

    public Winner find(Map<String, List<String>> input, String reservePrice) throws NegativePriceException {
        Price reservePriceConverted = Price.create(reservePrice);
        Bids bids = Bids.create(input);
        return bidStarter.acquire(bids.getBidsValue(), reservePriceConverted.getPriceValue());
    }
}
