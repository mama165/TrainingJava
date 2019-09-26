package fr.coding.auction;

import java.math.BigDecimal;
import java.util.List;

public interface BidStarter {
    Winner acquire(List<BigDecimal> bids, BigDecimal reservePrice);
}
