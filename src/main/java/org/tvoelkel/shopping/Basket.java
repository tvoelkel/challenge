package org.tvoelkel.shopping;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

/**
 * Class for a user's shopping basket
 */
public class Basket {
    /**
     * Store items by key: itemName, value: itemAmount
     */
    private final HashMap<String, Integer> content;

    public Basket() {
        this.content = new HashMap<>();
    }

    /**
     * Add an item to the basket. If item already exists within the basket, increase its amount by 1
     * @param itemId ID of the item, by which it can be found in the warehouse
     */
    public void scan(String itemId) {
        this.content.put(itemId, this.content.getOrDefault(itemId, 0) + 1);
    }

    /**
     * Return the total cost of the basket
     * @return Total cost of the basket.
     */
    public double total(){
        double sum = 0.0;
        Warehouse warehouse = Warehouse.getInstance();

        // iterate through ItemName - ItemAmount map
        for (Map.Entry<String, Integer> entry : content.entrySet()) {
            Item item = warehouse.getItem(entry.getKey());

            // If item can't be found in warehouse, throw exception
            if (item == null) {
                throw new ItemNotInStockException(String.format("'%s' does not exist in warehouse!", entry.getKey()));
            }

            // only count every second item if 'buy one, get one' free (round up on odd amount)
            if (item.isGetOneFree()) {
                sum += Math.ceilDiv(entry.getValue(), 2) * item.price() * (100.0 - item.percentOff()) * 0.01;
            } else {
                sum += entry.getValue() * item.price() * (100.0 - item.percentOff()) * 0.01;
            }
        }

        // round to two decimal points
        return new BigDecimal(sum).setScale(2, RoundingMode.DOWN).doubleValue();
    }
}
