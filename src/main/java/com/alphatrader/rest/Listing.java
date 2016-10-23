package com.alphatrader.rest;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * Represents a listing in the game.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0.0
 */
@SuppressWarnings("ConstantConditions")
public class Listing {
    /**
     * The start date of the listing.
     */
    private final ZonedDateTime startDate = null;

    /**
     * The end date of the listing.
     */
    private final ZonedDateTime endDate = null;

    /**
     * The listing's security identifier.
     */
    private final String securityIdentifier = null;

    /**
     * The name of the company, bond or repo this listing concerns.
     */
    private final String name = null;

    /**
     * The type of this listing.
     */
    private final Type type = null;

    /**
     * Fetches all listings currently on the market from the server.
     *
     * @return all listings on the market
     */
    @NotNull
    public static List<Listing> getAllListings() {
        return getMultipleListingsFromApi("listings/");
    }

    /**
     * Fetches the listing with the given security identifier.
     *
     * @param securityIdentifier the identifier to look for
     * @return the listing of null if not found
     */
    @Nullable
    public static Listing getById(String securityIdentifier) {
        return Http.getSingleObjectFromApi(Listing.class, "/api/listings/" + securityIdentifier);
    }

    /**
     * Returns the number of outstanding shares for this listing.
     *
     * @param securityIdentifier the security identifier of the listing
     * @return the number of outstanding shares
     */
    @Nullable
    public static Long getOutstandingShares(String securityIdentifier) {
        return Http.getSingleObjectFromApi(Long.class, "/api/listings/outstandingshares/"
            + securityIdentifier);
    }

    @Nullable
    public static Long getOutstandingShares(Listing listing) {
        return getOutstandingShares(listing.getSecurityIdentifier());
    }

    /**
     * Fetches all listings matching the search string.
     *
     * @param securityIdentifier part of the security identifier to look for
     * @return all listings on the market
     */
    @NotNull
    public static List<Listing> searchBySecurityIdentifier(String securityIdentifier) {
        return getMultipleListingsFromApi("search/listings/" + securityIdentifier);
    }

    /**
     * Fetches all shareholders of the listing with the given security identifier.
     *
     * @param securityIdentifier the identifier to look for
     * @return the list of shareholder companies for this listing
     */
    @NotNull
    public static List<Company> getShareholders(String securityIdentifier) {
        return Http.getMultipleObjectFromApi(Company.class, "/api/shareholders/" + securityIdentifier);
    }

    /**
     * Wrapper function for fetching multiple Company objects from the API.
     *
     * @param suffix the api endpoint
     * @return the requested companies
     */
    @NotNull
    private static List<Listing> getMultipleListingsFromApi(String suffix) {
        return Http.getMultipleObjectFromApi(Listing.class, "/api/" + suffix);
    }

    /**
     * Fetches all shareholders of the listing.
     *
     * @param listing the listing
     * @return the list of shareholder companies for this listing
     */
    @NotNull
    public static List<Company> getShareholders(Listing listing) {
        return getShareholders(listing.getSecurityIdentifier());
    }

    /**
     * @return the start date
     */
    public ZonedDateTime getStartDate() {
        return startDate;
    }

    /**
     * @return the end date
     */
    public ZonedDateTime getEndDate() {
        return endDate;
    }

    /**
     * @return the security identifier
     */
    public String getSecurityIdentifier() {
        return securityIdentifier;
    }

    /**
     * @return the company, bond or repo name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the listing type
     */
    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Listing{"
            + "startDate=" + startDate
            + ", endDate=" + endDate
            + ", securityIdentifier='" + securityIdentifier + '\''
            + ", name='" + name + '\''
            + ", type=" + type
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

        Listing listing = (Listing) o;

        return securityIdentifier != null ? securityIdentifier.equals(listing.securityIdentifier)
            : listing.securityIdentifier == null;

    }

    @Override
    public int hashCode() {
        return securityIdentifier != null ? securityIdentifier.hashCode() : 0;
    }

    /**
     * All listing types.
     */
    enum Type {
        BOND,
        STOCK,
        REPO
    }
}
