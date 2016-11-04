package com.alphatrader.rest;

import org.jetbrains.annotations.NotNull;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * Utility class for fetching lists of security price histories.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0.0
 */
public final class SecurityPrice {
    /**
     * Private constructor to avoid utility class instantiation.
     */
    private SecurityPrice() {
    }

    /**
     * Fetches all prices for the given company.
     *
     * @param securityIdentifier the securityIdentifier
     * @return the list of prices
     */
    @PublicAPI
    @NotNull
    public static List<LastPrice> getSecurityPrices(String securityIdentifier) {
        return getMultipleLastPricesFromApi("?securityIdentifier=" + securityIdentifier);
    }

    /**
     * Fetches all prices for the given company.
     *
     * @param securityIdentifier the securityIdentifier
     * @param startDate          the lower date boundary
     * @return the list of prices
     */
    @PublicAPI
    @NotNull
    public static List<LastPrice> getSecurityPrices(String securityIdentifier, ZonedDateTime startDate) {
        return getMultipleLastPricesFromApi("?securityIdentifier=" + securityIdentifier + "&startDate="
            + startDate.toInstant().toEpochMilli());
    }

    /**
     * Fetches all prices for the given company.
     *
     * @param securityIdentifier the securityIdentifier
     * @param startDate          the lower date boundary
     * @param endDate            the upper date boundary
     * @return the list of prices
     */
    @PublicAPI
    @NotNull
    public static List<LastPrice> getSecurityPrices(String securityIdentifier, ZonedDateTime startDate,
                                                    ZonedDateTime endDate) {
        return getMultipleLastPricesFromApi("?securityIdentifier=" + securityIdentifier + "&startDate="
            + startDate.toInstant().toEpochMilli() + "&endDate=" + endDate.toInstant().toEpochMilli());
    }

    /**
     * Wrapper class for api access.
     *
     * @param suffix the api suffix
     * @return the requested list of prices.
     */
    @NotNull
    private static List<LastPrice> getMultipleLastPricesFromApi(String suffix) {
        return Http.getMultipleObjectFromApi(LastPrice.class, "/api/securityPrices/" + suffix);
    }
}
