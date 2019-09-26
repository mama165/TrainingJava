package fr.coding.auction;

import java.util.List;
import java.util.Map;

public interface IFindWinner {
    Winner find(Map<String, List<String>> input, String price) throws NegativePriceException;
}
