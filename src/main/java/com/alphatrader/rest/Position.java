package com.alphatrader.rest;

/**
 * Represents a single portfolio position in the game.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0
 */
@SuppressWarnings("ConstantConditions")
public class Position {
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
    private final Long numberOfShares = null;

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
    public Long getNumberOfShares() {
        return numberOfShares;
    }

    /**
     * @return the overall volume
     */
    public Double getVolume() {
        return volume;
    }

    @Override
    public String toString() {
        return "Position{"
            + "securityIdentifier='" + securityIdentifier + '\''
            + ", lastPrice=" + lastPrice
            + ", numberOfShares=" + numberOfShares
            + ", volume=" + volume
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

        Position position = (Position) o;

        if (securityIdentifier != null ? !securityIdentifier.equals(position.securityIdentifier)
            : position.securityIdentifier != null) {
            return false;
        }
        if (lastPrice != null ? !lastPrice.equals(position.lastPrice) : position.lastPrice != null) {
            return false;
        }
        if (numberOfShares != null ? !numberOfShares.equals(position.numberOfShares)
            : position.numberOfShares != null) {
            return false;
        }
        return volume != null ? volume.equals(position.volume) : position.volume == null;

    }

    @Override
    public int hashCode() {
        int result = securityIdentifier != null ? securityIdentifier.hashCode() : 0;
        result = 31 * result + (lastPrice != null ? lastPrice.hashCode() : 0);
        result = 31 * result + (numberOfShares != null ? numberOfShares.hashCode() : 0);
        result = 31 * result + (volume != null ? volume.hashCode() : 0);
        return result;
    }
}
