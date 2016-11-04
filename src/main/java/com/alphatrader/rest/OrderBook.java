package com.alphatrader.rest;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.ObservableList;
import org.jetbrains.annotations.Nullable;

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
    private final ObservableList<Order> buyEntries = new SimpleListProperty<>();

    /**
     * List of sell orders.
     */
    private final ObservableList<Order> sellEntries = new SimpleListProperty<>();

    /**
     * Returns the order book for the specified company.
     *
     * @param securityIdentifier the security identifier
     * @return the order book
     */
    @PublicAPI
    @Nullable
    public static OrderBook getOrderBook(String securityIdentifier) {
        return Http.getSingleObjectFromApi(OrderBook.class, "/api/orderbook/" + securityIdentifier);
    }

    /**
     * @return the maximum buy size
     */
    @PublicAPI
    public Double getMaxBuySize() {
        return maxBuySize.getValue();
    }

    /**
     * @return the maximum sell size
     */
    @PublicAPI
    public Double getMaxSellSize() {
        return maxSellSize.getValue();
    }

    /**
     * @return the list of buy orders
     */
    @PublicAPI
    public ObservableList<Order> getBuyEntries() {
        return buyEntries;
    }

    /**
     * @return the list of sell orders
     */
    @PublicAPI
    public ObservableList<Order> getSellEntries() {
        return sellEntries;
    }

    @Override
    public String toString() {
        return "OrderBook{"
            + "maxBuySize=" + maxBuySize
            + ", maxSellSize=" + maxSellSize
            + ", buyEntries=" + buyEntries
            + ", sellEntries=" + sellEntries
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

        return maxBuySize.getValue() != null ? maxBuySize.getValue().equals(orderBook.maxBuySize
            .getValue()) : orderBook.maxBuySize.getValue() == null && (maxSellSize.getValue() != null
            ? maxSellSize.getValue().equals(orderBook.maxSellSize.getValue())
            : orderBook.maxSellSize.getValue() == null);

    }

    @Override
    public int hashCode() {
        int result = maxBuySize.getValue() != null ? maxBuySize.getValue().hashCode() : 0;
        result = 31 * result + (maxSellSize.getValue() != null ? maxSellSize.getValue().hashCode() : 0);
        return result;
    }
}
