package com.alphatrader.rest;

import com.alphatrader.rest.util.LocalDateTimeDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jetbrains.annotations.Nullable;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
     * Gson instance for deserialization.
     */
    private static final Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class,
        new LocalDateTimeDeserializer()).create();

    /**
     * List type for gson deserialization.
     */
    private static final Type listType = new TypeToken<ArrayList<Order>>() {
    }.getType();

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
    private final List<Order> buyOrders = null;

    /**
     * List of sell orders.
     */
    private final List<Order> sellOrders = null;

    /**
     * Returns the order book for the specified company.
     *
     * @param securityIdentifier the security identifier
     * @return the order book
     */
    @Nullable
    public final OrderBook getOrderBook(String securityIdentifier) {
        OrderBook myReturn = null;

        try {
            HttpResponse<JsonNode> response = Http.getInstance().get("/api/orderbook/"
                + securityIdentifier);
            String object = response.getBody().getObject().toString();
            myReturn = gson.fromJson(object, OrderBook.class);
        }
        catch (UnirestException ue) {
            log.error("Error fetching orderbook: " + ue.getMessage());
            StringWriter stringWriter = new StringWriter();
            ue.printStackTrace(new PrintWriter(stringWriter));
            log.debug(stringWriter.toString());
        }

        return myReturn;
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
    public List<Order> getBuyOrders() {
        return buyOrders;
    }

    /**
     * @return the list of sell orders
     */
    public List<Order> getSellOrders() {
        return sellOrders;
    }

    @Override
    public String toString() {
        return "OrderBook{"
            + "maxBuySize=" + maxBuySize
            + ", maxSellSize=" + maxSellSize
            + ", buyOrders=" + buyOrders
            + ", sellOrders=" + sellOrders
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

        if (maxBuySize != null ? !maxBuySize.equals(orderBook.maxBuySize)
            : orderBook.maxBuySize != null) {
            return false;
        }
        if (maxSellSize != null ? !maxSellSize.equals(orderBook.maxSellSize)
            : orderBook.maxSellSize != null) {
            return false;
        }
        if (buyOrders != null ? !buyOrders.equals(orderBook.buyOrders)
            : orderBook.buyOrders != null) {
            return false;
        }
        return sellOrders != null ? sellOrders.equals(orderBook.sellOrders)
            : orderBook.sellOrders == null;

    }

    @Override
    public int hashCode() {
        int result = maxBuySize != null ? maxBuySize.hashCode() : 0;
        result = 31 * result + (maxSellSize != null ? maxSellSize.hashCode() : 0);
        result = 31 * result + (buyOrders != null ? buyOrders.hashCode() : 0);
        result = 31 * result + (sellOrders != null ? sellOrders.hashCode() : 0);
        return result;
    }
}
