package com.alphatrader.rest;

import com.alphatrader.rest.util.Http;
import com.alphatrader.rest.util.LocalDateTimeDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the price spread between the current bid and asking price.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0.0
 */
class PriceSpread {
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
     * List type for gson deserialization.
     */
    private static final Type listType = new TypeToken<ArrayList<PriceSpread>>() { }.getType();

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

    /**
     * Fetches all price spreads currently on the market from the server.
     *
     * @return all price spreads on the market
     */
    @NotNull
    public static List<PriceSpread> getAllPriceSpreads() {
        List<PriceSpread> myReturn = new ArrayList<>();

        try {
            HttpResponse<JsonNode> response = Http.getInstance().get("/api/pricespreads/");

            if (response != null && response.getStatus() == 200) {
                myReturn = gson.fromJson(response.getBody()
                    .getArray()
                    .toString(), listType);
            }
        }
        catch (UnirestException ue) {
            log.error("Error fetching price spreads: " + ue.getMessage());
            StringWriter stringWriter = new StringWriter();
            ue.printStackTrace(new PrintWriter(stringWriter));
            log.debug(stringWriter.toString());
        }

        return myReturn;
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
        PriceSpread myReturn = null;

        try {
            HttpResponse<JsonNode> response = Http.getInstance().get("/api/pricespreads/");

            if (response != null && response.getStatus() == 200) {
                myReturn = gson.fromJson(response.getBody()
                    .getObject()
                    .toString(), PriceSpread.class);
            }
        }
        catch (UnirestException ue) {
            log.error("Error fetching price spread: " + ue.getMessage());
            StringWriter stringWriter = new StringWriter();
            ue.printStackTrace(new PrintWriter(stringWriter));
            log.debug(stringWriter.toString());
        }

        return myReturn;
    }

    /**
     * @return the current ask size
     */
    public Integer getAskSize() {
        return askSize;
    }

    /**
     * @return the current asking price
     */
    public Double getAskPrice() {
        return askPrice;
    }

    /**
     * @return the current bid size
     */
    public Integer getBidSize() {
        return bidSize;
    }

    /**
     * @return the current bid price
     */
    public Double getBidPrice() {
        return bidPrice;
    }

    /**
     * @return the current absolute spread
     */
    public Double getSpreadAbs() {
        return spreadAbs;
    }

    /**
     * @return the current spread percentage
     */
    public Double getSpreadPercent() {
        return spreadPercent;
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
        return maxBidPrice;
    }

    /**
     * @return the minimum asking price
     */
    public Double getMinAskPrice() {
        return minAskPrice;
    }

    /**
     * @return the date of the last trade
     */
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
        if (spreadPercent != null ? !spreadPercent.equals(that.spreadPercent)
            : that.spreadPercent != null) {
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
