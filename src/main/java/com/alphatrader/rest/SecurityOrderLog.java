package com.alphatrader.rest;

import javafx.beans.property.*;
import javafx.util.Pair;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.LinkedList;
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
     * The buyer securities account id.
     */
    private final StringProperty buyerSecuritiesAccount = new SimpleStringProperty();

    /**
     * The seller securities account id.
     */
    private final StringProperty sellerSecuritiesAccount = new SimpleStringProperty();

    /**
     * The number of shares.
     */
    private final DoubleProperty numberOfShares = new SimpleDoubleProperty();

    /**
     * The volume.
     */
    private final DoubleProperty volume = new SimpleDoubleProperty();

    /**
     * The security identifier.
     */
    private final StringProperty securityIdentifier = new SimpleStringProperty();

    /**
     * The price.
     */
    private final DoubleProperty price = new SimpleDoubleProperty();

    /**
     * The date.
     */
    private final ObjectProperty<ZonedDateTime> date = new SimpleObjectProperty<>();

    /**
     * The unique id.
     */
    private final StringProperty id = new SimpleStringProperty();

    /**
     * @return a list of all security order logs
     */
    @PublicAPI
    @NotNull
    public static List<SecurityOrderLog> getAllLogs() {
        return searchLogs(null, null, (Pair<SearchType, String>[]) null);
    }

    /**
     * Searches for all security order logs matching the given search parameters.
     *
     * @param startDate the lower date boundary for the search
     * @param endDate   the upper date boundary for the search
     * @param params    the list of search parameters
     * @return the search result list
     */
    @PublicAPI
    @NotNull
    @SafeVarargs
    public static List<SecurityOrderLog> searchLogs(ZonedDateTime startDate, ZonedDateTime endDate,
                                                    Pair<SearchType, String>... params) {
        String suffix = "";

        if (startDate != null || endDate != null || (params != null && params.length > 0)) {
            List<Pair<SearchType, String>> parameters = new LinkedList<>();

            if (params != null) {
                parameters.addAll(Arrays.asList(params));
            }

            if (startDate != null) {
                parameters.add(new Pair<>(
                    SearchType.START_DATE,
                    Long.toString(startDate.toInstant().toEpochMilli())
                ));
            }
            if (endDate != null) {
                parameters.add(new Pair<>(
                    SearchType.END_DATE,
                    Long.toString(endDate.toInstant().toEpochMilli())
                ));
            }
            suffix += "?" + String.join("&", parameters.stream().map(
                e -> e.getKey().toString() + "=" + e.getValue()
            ).collect(Collectors.toList()));
        }

        return getMultipleLogsFromApi(suffix);
    }

    /**
     * Wrapper function for API access.
     *
     * @param suffix the url suffix
     * @return the list of security orders requested
     */
    @PublicAPI
    @NotNull
    private static List<SecurityOrderLog> getMultipleLogsFromApi(String suffix) {
        return Http.getMultipleObjectFromApi(SecurityOrderLog.class, "/api/securityorderlogs" + suffix);
    }

    /**
     * @return the buyer's securities account id
     */
    @PublicAPI
    public String getBuyerSecuritiesAccount() {
        return buyerSecuritiesAccount.getValue();
    }

    /**
     * @return the seller's securities account id
     */
    @PublicAPI
    public String getSellerSecuritiesAccount() {
        return sellerSecuritiesAccount.getValue();
    }

    /**
     * @return the number of shares
     */
    @PublicAPI
    public Double getNumberOfShares() {
        return numberOfShares.getValue();
    }

    /**
     * @return the overall volume of the order
     */
    @PublicAPI
    public Double getVolume() {
        return volume.getValue();
    }

    /**
     * @return the security identifier of the traded asset
     */
    @PublicAPI
    public String getSecurityIdentifier() {
        return securityIdentifier.getValue();
    }

    /**
     * @return the price the asset was traded for
     */
    @PublicAPI
    public Double getPrice() {
        return price.getValue();
    }

    /**
     * @return the date the order was placed
     */
    @PublicAPI
    public ZonedDateTime getDate() {
        return date.getValue();
    }

    /**
     * @return the unique id
     */
    @PublicAPI
    public String getId() {
        return id.getValue();
    }

    @Override
    public String toString() {
        return "SecurityOrderLog{"
            + "buyerSecuritiesAccount='" + buyerSecuritiesAccount.getValue() + '\''
            + ", sellerSecuritiesAccount='" + sellerSecuritiesAccount.getValue() + '\''
            + ", numberOfShares=" + numberOfShares.getValue()
            + ", volume=" + volume.getValue()
            + ", securityIdentifier='" + securityIdentifier.getValue() + '\''
            + ", price=" + price.getValue()
            + ", date=" + date
            + ", id='" + id.getValue() + '\''
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

        return id.getValue() != null ? id.getValue().equals(that.id.getValue())
            : that.id.getValue() == null;
    }

    @Override
    public int hashCode() {
        return id.getValue() != null ? id.getValue().hashCode() : 0;
    }

    /**
     * The property you want to look for.
     */
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
