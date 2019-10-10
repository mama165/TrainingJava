package fr.coding.auction;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BidStarterTest {
    BidStarterImpl bidStarter = new BidStarterImpl();

    @Test
    void should_return_a_winner_on_auction_started() {
        String reservePrice = "";
        Winner winnerExpected = new Winner();

        List<BigDecimal> bids = Arrays.asList(new BigDecimal(""));
        BigDecimal reservePriceValue = new BigDecimal(reservePrice);

        Winner winnerOutput = bidStarter.acquire(bids, reservePriceValue);

        assertEquals(winnerExpected, winnerOutput);
    }
}
