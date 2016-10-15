package com.alphatrader.rest;

import com.alphatrader.rest.util.LocalDateTimeDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.time.LocalDateTime;

/**
 * Represents a single portfolio position in the game.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0
 */
public class Position {
    /**
     * Last price wrapping class used for gson deserialization.
     *
     * @author Christopher Guckes (christopher.guckes@torq-dev.de
     * @version 1.0
     */
    private static class LastPrice {
        /**
         * The date of the last transaction resulting in this price.
         */
        LocalDateTime date;

        /**
         * The last trade price of this position.
         */
        double value;
    }

    /**
     * Gson instance for deserialization.
     */
    private static final Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer()).create();;

    /**
     * Creates a new portfolio position from an API JSON response.
     *
     * @param json the json to parse
     * @return the parsed position object
     */
    public static Position createFromJson(String json) {
        return gson.fromJson(json, Position.class);
    }

    /**
     * The security identifier of this position.
     */
    private String securityIdentifier;

    /**
     * The price this position was traded for in the last trade.
     */
    private LastPrice lastPrice;

    /**
     * The number of shares held in the portfolio.
     */
    private int numberOfShares;

    /**
     * The overall volume of this position.
     */
    private double volume;

    /**
     * Creates a new position object with the given parameters.
     *
     * @param securityIdentifier the security identifier
     * @param numberOfShares     the number of shares
     * @param volume             the overall volume
     */
    public Position(String securityIdentifier, int numberOfShares, double volume) {
        this.securityIdentifier = securityIdentifier;
        this.lastPrice.value = volume / (double) numberOfShares;
        this.numberOfShares = numberOfShares;
        this.volume = volume;
    }

    /**
     * @return the security identifier
     */
    public String getSecurityIdentifier() {
        return securityIdentifier;
    }

    /**
     * @return the last trading price
     */
    public double getLastPrice() {
        return lastPrice.value;
    }

    /**
     * @return the number of shares
     */
    public int getNumberOfShares() {
        return numberOfShares;
    }

    /**
     * @return the overall volume
     */
    public double getVolume() {
        return volume;
    }
}
