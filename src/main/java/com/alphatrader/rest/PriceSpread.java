package com.alphatrader.rest;

import java.time.LocalDateTime;

/**
 * Represents the price spread between the current bid and asking price.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0.0
 */
class PriceSpread {
    private final Integer askSize = null;

    private final Double askPrice = null;

    private final Integer bidSize = null;

    private final Double bidPrice = null;

    private final Double spreadAbs = null;

    private final Double spreadPercent = null;

    private final LastPrice lastPrice = null;

    private final Double maxBidPrice = null;

    private final Double minAskPrice = null;

    private final LocalDateTime date = null;

    public Integer getAskSize() {
        return askSize;
    }

    public Double getAskPrice() {
        return askPrice;
    }

    public Integer getBidSize() {
        return bidSize;
    }

    public Double getBidPrice() {
        return bidPrice;
    }

    public Double getSpreadAbs() {
        return spreadAbs;
    }

    public Double getSpreadPercent() {
        return spreadPercent;
    }

    public LastPrice getLastPrice() {
        return lastPrice;
    }

    public Double getMaxBidPrice() {
        return maxBidPrice;
    }

    public Double getMinAskPrice() {
        return minAskPrice;
    }

    public LocalDateTime getDate() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PriceSpread that = (PriceSpread) o;

        if (askSize != null ? !askSize.equals(that.askSize) : that.askSize != null) {
            return false;
        }
        if (askPrice != null ? !askPrice.equals(that.askPrice) : that.askPrice != null) {
            return false;
        }
        if (bidSize != null ? !bidSize.equals(that.bidSize) : that.bidSize != null) {
            return false;
        }
        if (bidPrice != null ? !bidPrice.equals(that.bidPrice) : that.bidPrice != null) {
            return false;
        }
        if (spreadAbs != null ? !spreadAbs.equals(that.spreadAbs) : that.spreadAbs != null) {
            return false;
        }
        if (spreadPercent != null ? !spreadPercent.equals(that.spreadPercent) : that.spreadPercent != null) {
            return false;
        }
        if (lastPrice != null ? !lastPrice.equals(that.lastPrice) : that.lastPrice != null) {
            return false;
        }
        if (maxBidPrice != null ? !maxBidPrice.equals(that.maxBidPrice) : that.maxBidPrice != null) {
            return false;
        }
        if (minAskPrice != null ? !minAskPrice.equals(that.minAskPrice) : that.minAskPrice != null) {
            return false;
        }
        return date != null ? date.equals(that.date) : that.date == null;

    }

    @Override
    public int hashCode() {
        int result = askSize != null ? askSize.hashCode() : 0;
        result = 31 * result + (askPrice != null ? askPrice.hashCode() : 0);
        result = 31 * result + (bidSize != null ? bidSize.hashCode() : 0);
        result = 31 * result + (bidPrice != null ? bidPrice.hashCode() : 0);
        result = 31 * result + (spreadAbs != null ? spreadAbs.hashCode() : 0);
        result = 31 * result + (spreadPercent != null ? spreadPercent.hashCode() : 0);
        result = 31 * result + (lastPrice != null ? lastPrice.hashCode() : 0);
        result = 31 * result + (maxBidPrice != null ? maxBidPrice.hashCode() : 0);
        result = 31 * result + (minAskPrice != null ? minAskPrice.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }
}
