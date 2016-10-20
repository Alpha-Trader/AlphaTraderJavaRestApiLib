package com.alphatrader.rest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

/**
 * Represents a company order book in the game.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0.0
 */
@SuppressWarnings("ConstantConditions")
public class OrderBook {
    /**
     * The logger for this class.
     */
    private static final Log log = LogFactory.getLog(Order.class);

    /**
     * The maximum buy size.
     */
    private final Double maxBuySize = null;

    /**
     * The maximum sell size.
     */
    private final Double maxSellSize = null;

    /**
     * List of buy orders.
     */
    private final Order[] buyEntries = null;

    /**
     * List of sell orders.
     */
    private final Order[] sellEntries = null;

    /**
     * Returns the order book for the specified company.
     *
     * @param securityIdentifier the security identifier
     * @return the order book
     */
    @Nullable
    public final OrderBook getOrderBook(String securityIdentifier) {
        return Http.getSingleObjectFromApi(OrderBook.class, "/api/orderbook/" + securityIdentifier);
    }

    /**
     * @return the maximum buy size
     */
    public Double getMaxBuySize() {
        return maxBuySize;
    }

    /**
     * @return the maximum sell size
     */
    public Double getMaxSellSize() {
        return maxSellSize;
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
}
