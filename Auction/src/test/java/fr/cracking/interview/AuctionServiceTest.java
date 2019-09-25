package fr.cracking.interview;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class AuctionServiceTest {
    private AuctionService auctionService;

    @Mock
    private BidStarter bidStarter;

    @BeforeEach
    public void setup() {
        auctionService = new AuctionService(bidStarter);
    }

    @Test
    public void should_throw_exception_on_auction_when_negative_reserve_price() {
        String reservePrice = "-100";
        Throwable throwable = assertThrows(NegativePriceException.class, () ->
                auctionService.find(Collections.emptyMap(), reservePrice)
        );
        String expectedMessage = "The price is negative : -100";
        assertEquals(throwable.getMessage(), expectedMessage);
    }

    @Test
    public void should_throw_exception_on_auction_when_null_reserve_price() {
        Throwable throwable = assertThrows(NegativePriceException.class, () ->
                auctionService.find(Collections.emptyMap(), null)
        );
        String expectedMessage = "The price is null";
        assertEquals(throwable.getMessage(), expectedMessage);
    }

    @Test
    public void should_throw_exception_on_auction_when_one_negative_bid() {
        String reservePrice = "674";

        HashMap<String, List<String>> hashMap = new HashMap();
        hashMap.put("A", Arrays.asList("100", "3988"));
        hashMap.put("B", Arrays.asList("5646", "546"));
        hashMap.put("C", Arrays.asList("100", "-593"));
        hashMap.put("D", Arrays.asList("67'", "-3988", "566", "-98"));

        Throwable throwable = assertThrows(NegativePriceException.class, () ->
                auctionService.find(hashMap, reservePrice)
        );
        String expectedMessage = "The price is negative : -593,-3988,-98";
        assertEquals(throwable.getMessage(), expectedMessage);
    }
}