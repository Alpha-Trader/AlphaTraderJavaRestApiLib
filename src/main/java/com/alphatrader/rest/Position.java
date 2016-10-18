package com.alphatrader.rest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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
     * The security identifier of this position.
     */
    private final String securityIdentifier = null;

    /**
     * The price this position was traded for in the last trade.
     */
    private final LastPrice lastPrice = null;

    /**
     * The number of shares held in the portfolio.
     */
    private final Integer numberOfShares = null;

    /**
     * The overall volume of this position.
     */
    private final Double volume = null;

    /**
     * @return the security identifier
     */
    public String getSecurityIdentifier() {
        return securityIdentifier;
    }

    /**
     * @return the last trading price
     */
    public LastPrice getLastPrice() {
        return lastPrice;
    }

    /**
     * @return the number of shares
     */
    public Integer getNumberOfShares() {
        return numberOfShares;
    }

    /**
     * @return the overall volume
     */
    public Double getVolume() {
        return volume;
    }
}
