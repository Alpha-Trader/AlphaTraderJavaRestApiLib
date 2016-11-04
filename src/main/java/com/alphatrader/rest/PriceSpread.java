package com.alphatrader.rest;

import javafx.beans.property.*;
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
    private final LongProperty askSize = new SimpleLongProperty();

    /**
     * The current asking price.
     */
    private final DoubleProperty askPrice = new SimpleDoubleProperty();

    /**
     * The current bid size.
     */
    private final LongProperty bidSize = new SimpleLongProperty();

    /**
     * The current bid price.
     */
    private final DoubleProperty bidPrice = new SimpleDoubleProperty();

    /**
     * The absolute spread.
     */
    private final DoubleProperty spreadAbs = new SimpleDoubleProperty();

    /**
     * The spread percentage.
     */
    private final DoubleProperty spreadPercent = new SimpleDoubleProperty();

    /**
     * The last price the listing was traded.
     */
    private final ObjectProperty<LastPrice> lastPrice = new SimpleObjectProperty<>();

    /**
     * The maximum bid price allowed by the market.
     */
    private final DoubleProperty maxBidPrice = new SimpleDoubleProperty();

    /**
     * The minimal asking price allowed by the market.
     */
    private final DoubleProperty minAskPrice = new SimpleDoubleProperty();

    /**
     * The date the spread was created.
     */
    private final ObjectProperty<ZonedDateTime> date = new SimpleObjectProperty<>();

    /**
     * Fetches all price spreads currently on the market from the server.
     *
     * @return all price spreads on the market
     */
    @PublicAPI
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
    @PublicAPI
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
    @PublicAPI
    @Nullable
    public static PriceSpread getPriceSpread(String securityIdentifier) {
        return Http.getSingleObjectFromApi(PriceSpread.class, "/api/pricespreads/" + securityIdentifier);
    }

    /**
     * @return the current ask size
     */
    @PublicAPI
    public Long getAskSize() {
        return askSize.getValue();
    }

    /**
     * @return the current asking price
     */
    @PublicAPI
    public Double getAskPrice() {
        return askPrice.getValue();
    }

    /**
     * @return the current bid size
     */
    @PublicAPI
    public Long getBidSize() {
        return bidSize.getValue();
    }

    /**
     * @return the current bid price
     */
    @PublicAPI
    public Double getBidPrice() {
        return bidPrice.getValue();
    }

    /**
     * @return the current absolute spread
     */
    @PublicAPI
    public Double getSpreadAbs() {
        return spreadAbs.getValue();
    }

    /**
     * @return the current spread percentage
     */
    @PublicAPI
    public Double getSpreadPercent() {
        return spreadPercent.getValue();
    }

    /**
     * @return the last price
     */
    @PublicAPI
    public LastPrice getLastPrice() {
        return lastPrice.getValue();
    }

    /**
     * @return the maximum bid price
     */
    @PublicAPI
    public Double getMaxBidPrice() {
        return maxBidPrice.getValue();
    }

    /**
     * @return the minimum asking price
     */
    @PublicAPI
    public Double getMinAskPrice() {
        return minAskPrice.getValue();
    }

    /**
     * @return the date of the last trade
     */
    @PublicAPI
    public ZonedDateTime getDate() {
        return date.getValue();
    }

    /**
     * @return the ask size property.
     */
    @PublicAPI
    public ReadOnlyLongProperty askSizeProperty() {
        return askSize;
    }

    /**
     * @return the ask price property.
     */
    @PublicAPI
    public ReadOnlyDoubleProperty askPriceProperty() {
        return askPrice;
    }

    /**
     * @return the bid size property.
     */
    @PublicAPI
    public ReadOnlyLongProperty bidSizeProperty() {
        return bidSize;
    }

    /**
     * @return the bid price property.
     */
    @PublicAPI
    public ReadOnlyDoubleProperty bidPriceProperty() {
        return bidPrice;
    }

    /**
     * @return the absolute spread property.
     */
    @PublicAPI
    public ReadOnlyDoubleProperty spreadAbsProperty() {
        return spreadAbs;
    }

    /**
     * @return the spread percentage property.
     */
    @PublicAPI
    public ReadOnlyDoubleProperty spreadPercentProperty() {
        return spreadPercent;
    }

    /**
     * @return the last price property.
     */
    @PublicAPI
    public ReadOnlyObjectProperty<LastPrice> lastPriceProperty() {
        return lastPrice;
    }

    /**
     * @return the max bid price property.
     */
    @PublicAPI
    public ReadOnlyDoubleProperty maxBidPriceProperty() {
        return maxBidPrice;
    }

    /**
     * @return the minimal asking price property.
     */
    @PublicAPI
    public ReadOnlyDoubleProperty minAskPriceProperty() {
        return minAskPrice;
    }

    /**
     * @return the date property.
     */
    @PublicAPI
    public ReadOnlyObjectProperty<ZonedDateTime> dateProperty() {
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

    @SuppressWarnings("SimplifiableIfStatement")
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PriceSpread that = (PriceSpread) o;

        if (askSize.getValue() != null ? !askSize.getValue().equals(that.askSize.getValue())
            : that.askSize.getValue() != null) {
            return false;
        }
        if (askPrice.getValue() != null ? !askPrice.getValue().equals(that.askPrice.getValue())
            : that.askPrice.getValue() != null) {
            return false;
        }
        if (bidSize.getValue() != null ? !bidSize.getValue().equals(that.bidSize.getValue())
            : that.bidSize.getValue() != null) {
            return false;
        }
        if (bidPrice.getValue() != null ? !bidPrice.getValue().equals(that.bidPrice.getValue())
            : that.bidPrice.getValue() != null) {
            return false;
        }
        if (spreadAbs.getValue() != null ? !spreadAbs.getValue().equals(that.spreadAbs.getValue())
            : that.spreadAbs.getValue() != null) {
            return false;
        }
        if (spreadPercent.getValue() != null ? !spreadPercent.getValue().equals(
            that.spreadPercent.getValue()) : that.spreadPercent.getValue() != null) {
            return false;
        }
        if (lastPrice.getValue() != null ? !lastPrice.getValue().equals(that.lastPrice.getValue())
            : that.lastPrice.getValue() != null) {
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
        return date.getValue() != null ? date.getValue().equals(that.date.getValue())
            : that.date.getValue() == null;

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
        result = 31 * result + (lastPrice.getValue() != null ? lastPrice.getValue().hashCode() : 0);
        result = 31 * result + (maxBidPrice.getValue() != null ? maxBidPrice.getValue().hashCode() : 0);
        result = 31 * result + (minAskPrice.getValue() != null ? minAskPrice.getValue().hashCode() : 0);
        result = 31 * result + (date.getValue() != null ? date.getValue().hashCode() : 0);
        return result;
    }
}
