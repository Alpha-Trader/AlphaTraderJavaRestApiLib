package com.alphatrader.rest;

import java.time.LocalDateTime;

/**
 * Last price wrapping class used for gson deserialization.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de
 * @version 1.0.0
 */
class LastPrice {
    /**
     * The date of the last transaction resulting in this price.
     */
    private final LocalDateTime date;

    /**
     * The last trade price of this position.
     */
    private final double value;

    LastPrice(LocalDateTime date, double value) {
        this.date = date;
        this.value = value;
    }

    LocalDateTime getDate() {
        return this.date;
    }

    double getValue() {
        return this.value;
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

        if (Double.compare(lastPrice.value, value) != 0) {
            return false;
        }
        return date != null ? date.equals(lastPrice.date) : lastPrice.date == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = date != null ? date.hashCode() : 0;
        temp = Double.doubleToLongBits(value);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
