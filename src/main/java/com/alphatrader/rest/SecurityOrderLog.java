package com.alphatrader.rest;

import com.alphatrader.rest.util.Http;
import com.alphatrader.rest.util.LocalDateTimeDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;
import javafx.util.Pair;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jetbrains.annotations.Contract;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a security order log in the game.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0.0
 */
public class SecurityOrderLog {
    /**
     * The logger for this class.
     */
    private static final Log log = LogFactory.getLog(SecurityOrderLog.class);

    /**
     * Gson instance for deserialization.
     */
    private static final Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class,
        new LocalDateTimeDeserializer()).create();

    /**
     * List type for gson deserialization.
     */
    private static final java.lang.reflect.Type listType = new TypeToken<ArrayList<SecurityOrderLog>>() {
    }.getType();

    /**
     * The buyer securities account id.
     */
    private final String buyerSecuritiesAccount = null;

    /**
     * The seller securities account id.
     */
    private final String sellerSecuritiesAccount = null;

    /**
     * The number of shares.
     */
    private final Integer numberOfShares = null;

    /**
     * The volume.
     */
    private final Double volume = null;

    /**
     * The security identifier.
     */
    private final String securityIdentifier = null;

    /**
     * The price.
     */
    private final Double price = null;

    /**
     * The date.
     */
    private final LocalDateTime date = null;

    /**
     * The unique id.
     */
    private final String id = null;

    /**
     * @return a list of all security order logs
     */
    public static List<SecurityOrderLog> getAllLogs() {
        return searchLogs(null, null, null);
    }

    /**
     * Searches for all security order logs matching the given search parameters.
     *
     * @param startDate the lower date boundary for the search
     * @param endDate   the upper date boundary for the search
     * @param params    the list of search parameters
     * @return the search result list
     */
    @SafeVarargs
    public static List<SecurityOrderLog> searchLogs(LocalDateTime startDate, LocalDateTime endDate,
                                                    Pair<SearchType, String>... params) {
        String suffix = "";

        if (startDate != null || endDate != null || params.length > 0) {
            List<Pair<SearchType, String>> parameters = Arrays.asList(params);

            if (startDate != null) {
                parameters.add(new Pair<>(
                    SearchType.START_DATE,
                    Long.toString(startDate.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli())
                ));
            }
            if (endDate != null) {
                parameters.add(new Pair<SearchType, String>(
                    SearchType.END_DATE,
                    Long.toString(endDate.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli())
                ));
            }
            suffix += "?" + String.join("&", parameters.stream().map(
                e -> e.getKey().toString() + "=" + e.getValue()
            ).collect(Collectors.toList()));
        }

        return getFromApi(suffix);
    }

    /**
     * Wrapper function for API access.
     *
     * @param suffix the url suffix
     * @return the list of security orders requested
     */
    private static List<SecurityOrderLog> getFromApi(String suffix) {
        List<SecurityOrderLog> myReturn = new ArrayList<>();

        try {
            HttpResponse<JsonNode> response = Http.getInstance().get("/api/securityorderlogs" + suffix);
            String orders = response.getBody().getArray().toString();
            myReturn = gson.fromJson(orders, listType);
        } catch (UnirestException ue) {
            log.error("Error fetching security order logs: " + ue.getMessage());
            StringWriter stringWriter = new StringWriter();
            ue.printStackTrace(new PrintWriter(stringWriter));
            log.debug(stringWriter.toString());
        }

        return myReturn;
    }

    public String getBuyerSecuritiesAccount() {
        return buyerSecuritiesAccount;
    }

    public String getSellerSecuritiesAccount() {
        return sellerSecuritiesAccount;
    }

    public Integer getNumberOfShares() {
        return numberOfShares;
    }

    public Double getVolume() {
        return volume;
    }

    public String getSecurityIdentifier() {
        return securityIdentifier;
    }

    public Double getPrice() {
        return price;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "SecurityOrderLog{"
            + "buyerSecuritiesAccount='" + buyerSecuritiesAccount + '\''
            + ", sellerSecuritiesAccount='" + sellerSecuritiesAccount + '\''
            + ", numberOfShares=" + numberOfShares
            + ", volume=" + volume
            + ", securityIdentifier='" + securityIdentifier + '\''
            + ", price=" + price
            + ", date=" + date
            + ", id='" + id + '\''
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

        SecurityOrderLog that = (SecurityOrderLog) o;

        return id != null ? id.equals(that.id) : that.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    public enum SearchType {
        START_DATE("startDate"),
        END_DATE("endDate"),
        SECURITY_IDENTIFIER("securityIdentifier"),
        BUYER_SECURITIES_ACCOUNT_ID("buyerSecuritiesAccountId"),
        SELLER_SECURITIES_ACCOUNT_ID("sellerSecuritiesAccountId");

        /**
         * the search query string.
         */
        private final String text;

        /**
         * @param text the search query string.
         */
        SearchType(final String text) {
            this.text = text;
        }

        @Contract(pure = true)
        @Override
        public String toString() {
            return text;
        }
    }
}
