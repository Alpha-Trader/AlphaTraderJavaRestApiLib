package com.alphatrader.rest;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleLongProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * Represents the price spread between the current bid and asking price.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0.0
 */
public class PriceSpread {
    /**
     * The current ask size.
     */
    private final LongProperty askSize;

    /**
     * The current asking price.
     */
    private final DoubleProperty askPrice;

    /**
     * The current bid size.
     */
    private final LongProperty bidSize;

    /**
     * The current bid price.
     */
    private final DoubleProperty bidPrice;

    /**
     * The absolute spread.
     */
    private final DoubleProperty spreadAbs;

    /**
     * The spread percentage.
     */
    private final DoubleProperty spreadPercent;

    /**
     * The last price the listing was traded.
     */
    private final LastPrice lastPrice;

    /**
     * The maximum bid price allowed by the market.
     */
    private final DoubleProperty maxBidPrice;

    /**
     * The minimal asking price allowed by the market.
     */
    private final DoubleProperty minAskPrice;

    /**
     * The date the spread was created.
     */
    private final ZonedDateTime date;

    /**
     * Creates a default price spread object.
     */
    public PriceSpread() {
        askSize = new SimpleLongProperty();
        askPrice = new SimpleDoubleProperty();
        bidSize = new SimpleLongProperty();
        bidPrice = new SimpleDoubleProperty();
        spreadAbs = new SimpleDoubleProperty();
        spreadPercent = new SimpleDoubleProperty();
        lastPrice = new LastPrice();
        maxBidPrice = new SimpleDoubleProperty();
        minAskPrice = new SimpleDoubleProperty();
        date = ZonedDateTime.now();
    }

    /**
     * Fetches all price spreads currently on the market from the server.
     *
     * @return all price spreads on the market
     */
    @NotNull
    public static List<PriceSpread> getAllPriceSpreads() {
        return Http.getMultipleObjectFromApi(PriceSpread.class, "/api/pricespreads/");
    }

    /**
     * Fetches the price spread of the listing.
     *
     * @param listing the listing
     * @return the price spread of the listing
     */
    @Nullable
    public static PriceSpread getPriceSpread(Listing listing) {
        return getPriceSpread(listing.getSecurityIdentifier());
    }

    /**
     * Fetches the price spread of the listing with the given security identifier.
     *
     * @param securityIdentifier the security identifier or the listing
     * @return the price spread of the listing
     */
    @Nullable
    public static PriceSpread getPriceSpread(String securityIdentifier) {
        return Http.getSingleObjectFromApi(PriceSpread.class, "/api/pricespreads/" + securityIdentifier);
    }

    /**
     * @return the current ask size
     */
    public Long getAskSize() {
        return askSize.getValue();
    }

    /**
     * @return the current asking price
     */
    public Double getAskPrice() {
        return askPrice.getValue();
    }

    /**
     * @return the current bid size
     */
    public Long getBidSize() {
        return bidSize.getValue();
    }

    /**
     * @return the current bid price
     */
    public Double getBidPrice() {
        return bidPrice.getValue();
    }

    /**
     * @return the current absolute spread
     */
    public Double getSpreadAbs() {
        return spreadAbs.getValue();
    }

    /**
     * @return the current spread percentage
     */
    public Double getSpreadPercent() {
        return spreadPercent.getValue();
    }

    /**
     * @return the last price
     */
    public LastPrice getLastPrice() {
        return lastPrice;
    }

    /**
     * @return the maximum bid price
     */
    public Double getMaxBidPrice() {
        return maxBidPrice.getValue();
    }

    /**
     * @return the minimum asking price
     */
    public Double getMinAskPrice() {
        return minAskPrice.getValue();
    }

    /**
     * @return the date of the last trade
     */
    public ZonedDateTime getDate() {
        return date;
    }


    @Override
    public String toString() {
        return "PriceSpread{"
            + "askSize=" + askSize
            + ", askPrice=" + askPrice
            + ", bidSize=" + bidSize
            + ", bidPrice=" + bidPrice
            + ", spreadAbs=" + spreadAbs
            + ", spreadPercent=" + spreadPercent
            + ", lastPrice=" + lastPrice
            + ", maxBidPrice=" + maxBidPrice
            + ", minAskPrice=" + minAskPrice
            + ", date=" + date
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

        PriceSpread that = (PriceSpread) o;

        if (askSize.getValue() != null ? !askSize.getValue().equals(that.askSize.getValue()) : that
            .askSize.getValue() != null) {
            return false;
        }
        if (askPrice.getValue() != null ? !askPrice.getValue().equals(that.askPrice.getValue())
            : that.askPrice.getValue() != null) {
            return false;
        }
        if (bidSize.getValue() != null ? !bidSize.getValue().equals(that.bidSize.getValue()) : that
            .bidSize.getValue() != null) {
            return false;
        }
        if (bidPrice.getValue() != null ? !bidPrice.getValue().equals(that.bidPrice.getValue()) : that
            .bidPrice.getValue() != null) {
            return false;
        }
        if (spreadAbs.getValue() != null ? !spreadAbs.getValue().equals(that.spreadAbs.getValue()) : that
            .spreadAbs.getValue() != null) {
            return false;
        }
        if (spreadPercent.getValue() != null ? !spreadPercent.getValue().equals(that.spreadPercent
            .getValue()) : that.spreadPercent.getValue()
            != null) {
            return false;
        }
        if (lastPrice != null ? !lastPrice.equals(that.lastPrice) : that.lastPrice != null) {
            return false;
        }
        if (maxBidPrice.getValue() != null ? !maxBidPrice.getValue().equals(that.maxBidPrice.getValue())
            : that.maxBidPrice.getValue() != null) {
            return false;
        }
        if (minAskPrice.getValue() != null ? !minAskPrice.getValue().equals(that.minAskPrice.getValue())
            : that.minAskPrice.getValue() != null) {
            return false;
        }
        return date != null ? date.equals(that.date) : that.date == null;

    }

    @Override
    public int hashCode() {
        int result = askSize.getValue() != null ? askSize.getValue().hashCode() : 0;
        result = 31 * result + (askPrice.getValue() != null ? askPrice.getValue().hashCode() : 0);
        result = 31 * result + (bidSize.getValue() != null ? bidSize.getValue().hashCode() : 0);
        result = 31 * result + (bidPrice.getValue() != null ? bidPrice.getValue().hashCode() : 0);
        result = 31 * result + (spreadAbs.getValue() != null ? spreadAbs.getValue().hashCode() : 0);
        result = 31 * result + (spreadPercent.getValue() != null ? spreadPercent.getValue().hashCode()
            : 0);
        result = 31 * result + (lastPrice != null ? lastPrice.hashCode() : 0);
        result = 31 * result + (maxBidPrice.getValue() != null ? maxBidPrice.getValue().hashCode() : 0);
        result = 31 * result + (minAskPrice.getValue() != null ? minAskPrice.getValue().hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }
}
