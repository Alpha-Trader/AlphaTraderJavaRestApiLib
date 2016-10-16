package com.alphatrader.rest;

import com.alphatrader.rest.util.LocalDateTimeDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.time.LocalDateTime;

/**
 * Represents a single portfolio position in the game.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0
 */
public class Position {
    /**
     * The logger for this class.
     */
    private static final Log log = LogFactory.getLog(Position.class);

    /**
     * Gson instance for deserialization.
     */
    private static final Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class,
        new LocalDateTimeDeserializer()).create();

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
     * @return the security identifier
     */
    public String getSecurityIdentifier() {
        return securityIdentifier;
    }

    /**
     * @return the last trading price
     */
    public double getLastPrice() {
        return lastPrice.getValue();
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
