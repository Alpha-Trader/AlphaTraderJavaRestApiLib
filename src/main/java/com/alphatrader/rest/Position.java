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
    private final ObjectProperty<LastPrice> lastPrice = new SimpleObjectProperty<>();

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
    @PublicAPI
    public String getSecurityIdentifier() {
        return securityIdentifier.getValue();
    }

    /**
     * @return the last trading price
     */
    @PublicAPI
    public LastPrice getLastPrice() {
        return lastPrice.getValue();
    }

    /**
     * @return the number of shares
     */
    @PublicAPI
    public Long getNumberOfShares() {
        return numberOfShares.getValue();
    }

    /**
     * @return the overall volume
     */
    @PublicAPI
    public Double getVolume() {
        return volume.getValue();
    }

    /**
     * @return the security identifier property.
     */
    @PublicAPI
    public ReadOnlyStringProperty securityIdentifierProperty() {
        return securityIdentifier;
    }

    /**
     * @return the last price property.
     */
    @PublicAPI
    public ReadOnlyObjectProperty<LastPrice> lastPriceProperty() {
        return lastPrice;
    }

    /**
     * @return the number of shares property.
     */
    @PublicAPI
    public ReadOnlyLongProperty numberOfSharesProperty() {
        return numberOfShares;
    }

    /**
     * @return the volume property.
     */
    @PublicAPI
    public ReadOnlyDoubleProperty volumeProperty() {
        return volume;
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

    @SuppressWarnings("SimplifiableIfStatement")
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Position position = (Position) o;

        if (securityIdentifier.getValue() != null ? !securityIdentifier.getValue().equals(
            position.securityIdentifier.getValue()) : position.securityIdentifier.getValue() != null) {
            return false;
        }
        if (lastPrice.getValue() != null ? !lastPrice.getValue().equals(position.lastPrice.getValue())
            : position.lastPrice.getValue() != null) {
            return false;
        }
        if (numberOfShares.getValue() != null ? !numberOfShares.getValue().equals(
            position.numberOfShares.getValue()) : position.numberOfShares.getValue() != null) {
            return false;
        }
        return volume.getValue() != null ? volume.getValue().equals(position.volume.getValue())
            : position.volume.getValue() == null;

    }

    @Override
    public int hashCode() {
        int result = securityIdentifier.getValue() != null ? securityIdentifier.getValue().hashCode()
            : 0;
        result = 31 * result + (lastPrice.getValue() != null ? lastPrice.getValue().hashCode() : 0);
        result = 31 * result + (numberOfShares.getValue() != null ? numberOfShares.getValue().hashCode()
            : 0);
        result = 31 * result + (volume.getValue() != null ? volume.getValue().hashCode() : 0);
        return result;
    }
}
