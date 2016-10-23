package com.alphatrader.rest;

import javafx.beans.property.*;

/**
 * Represents a single portfolio position in the game.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0
 */
public class Position {
    /**
     * The security identifier of this position.
     */
    private final StringProperty securityIdentifier = new SimpleStringProperty();

    /**
     * The price this position was traded for in the last trade.
     */
    private final LastPrice lastPrice = null;

    /**
     * The number of shares held in the portfolio.
     */
    private final LongProperty numberOfShares = new SimpleLongProperty();

    /**
     * The overall volume of this position.
     */
    private final DoubleProperty volume = new SimpleDoubleProperty();

    /**
     * @return the security identifier
     */
    public String getSecurityIdentifier() {
        return securityIdentifier.getValue();
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
        return numberOfShares.getValue();
    }

    /**
     * @return the overall volume
     */
    public Double getVolume() {
        return volume.getValue();
    }

    @Override
    public String toString() {
        return "Position{"
            + "securityIdentifier='" + securityIdentifier.getValue() + '\''
            + ", lastPrice=" + lastPrice
            + ", numberOfShares=" + numberOfShares.getValue()
            + ", volume=" + volume.getValue()
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

        if (securityIdentifier.getValue() != null ? !securityIdentifier.getValue().equals(position
            .securityIdentifier.getValue()) : position.securityIdentifier.getValue() != null) {
            return false;
        }
        if (lastPrice != null ? !lastPrice.equals(position.lastPrice) : position.lastPrice != null) {
            return false;
        }
        if (numberOfShares.getValue() != null ? !numberOfShares.getValue().equals(position.numberOfShares
            .getValue()) : position.numberOfShares.getValue() != null) {
            return false;
        }
        return volume.getValue() != null ? volume.getValue().equals(position.volume.getValue())
            : position.volume.getValue() == null;

    }

    @Override
    public int hashCode() {
        int result = securityIdentifier.getValue() != null ? securityIdentifier.getValue().hashCode()
            : 0;
        result = 31 * result + (lastPrice != null ? lastPrice.hashCode() : 0);
        result = 31 * result + (numberOfShares.getValue() != null ? numberOfShares.getValue().hashCode()
            : 0);
        result = 31 * result + (volume.getValue() != null ? volume.getValue().hashCode() : 0);
        return result;
    }
}
