package com.alphatrader.rest;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.ZonedDateTime;

/**
 * Last price wrapping class used for gson deserialization.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de
 * @version 1.0.0
 */
public class LastPrice {
    /**
     * The date of the last transaction resulting in this price.
     */
    private final ZonedDateTime date = null;

    /**
     * The last trade price of this position.
     */
    private final StringProperty value = new SimpleStringProperty();

    /**
     * @return the time this trade took place
     */
    public ZonedDateTime getDate() {
        return this.date;
    }

    /**
     * @return the value of this trade
     */
    public Double getValue() {
        return Double.valueOf(this.value.getValue().replaceAll("%", "").trim());
    }

    @Override
    public String toString() {
        return "LastPrice{"
            + "date=" + date
            + ", value=" + value
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

        LastPrice lastPrice = (LastPrice) o;

        if (Double.compare(lastPrice.getValue(), getValue()) != 0) {
            return false;
        }
        return date != null ? date.equals(lastPrice.date) : lastPrice.date == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = date != null ? date.hashCode() : 0;
        temp = Double.doubleToLongBits(this.getValue());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
