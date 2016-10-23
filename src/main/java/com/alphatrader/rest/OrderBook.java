package com.alphatrader.rest;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

/**
 * Represents a company order book in the game.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0.0
 */
public class OrderBook {
   /**
     * The maximum buy size.
     */
    private final DoubleProperty maxBuySize = new SimpleDoubleProperty();

    /**
     * The maximum sell size.
     */
    private final DoubleProperty maxSellSize = new SimpleDoubleProperty();

    /**
     * List of buy orders.
     */
    private final Order[] buyEntries = new Order[0];

    /**
     * List of sell orders.
     */
    private final Order[] sellEntries = new Order[0];

    /**
     * Returns the order book for the specified company.
     *
     * @param securityIdentifier the security identifier
     * @return the order book
     */
    @Nullable
    public static OrderBook getOrderBook(String securityIdentifier) {
        return Http.getSingleObjectFromApi(OrderBook.class, "/api/orderbook/" + securityIdentifier);
    }

    /**
     * @return the maximum buy size
     */
    public Double getMaxBuySize() {
        return maxBuySize.getValue();
    }

    /**
     * @return the maximum sell size
     */
    public Double getMaxSellSize() {
        return maxSellSize.getValue();
    }

    /**
     * @return the list of buy orders
     */
    public List<Order> getBuyEntries() {
        return Arrays.asList(buyEntries);
    }

    /**
     * @return the list of sell orders
     */
    public List<Order> getSellEntries() {
        return Arrays.asList(sellEntries);
    }

    @Override
    public String toString() {
        return "OrderBook{"
            + "maxBuySize=" + maxBuySize
            + ", maxSellSize=" + maxSellSize
            + ", buyEntries=" + Arrays.toString(buyEntries)
            + ", sellEntries=" + Arrays.toString(sellEntries)
            + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        OrderBook orderBook = (OrderBook) o;

        if (maxBuySize.getValue() != null ? !maxBuySize.getValue().equals(orderBook.maxBuySize
            .getValue()) : orderBook.maxBuySize.getValue() != null) {
            return false;
        }
        return maxSellSize.getValue() != null ? maxSellSize.getValue().equals(orderBook.maxSellSize
            .getValue()) : orderBook.maxSellSize.getValue() == null;

    }

    @Override
    public int hashCode() {
        int result = maxBuySize.getValue() != null ? maxBuySize.getValue().hashCode() : 0;
        result = 31 * result + (maxSellSize.getValue() != null ? maxSellSize.getValue().hashCode() : 0);
        return result;
    }
}
