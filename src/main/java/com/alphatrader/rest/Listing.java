package com.alphatrader.rest;

import javafx.beans.property.*;
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
public class Listing {
    /**
     * The start date of the listing.
     */
    private final ObjectProperty<ZonedDateTime> startDate = new SimpleObjectProperty<>();

    /**
     * The end date of the listing.
     */
    private final ObjectProperty<ZonedDateTime> endDate = new SimpleObjectProperty<>();

    /**
     * The listing's security identifier.
     */
    private final StringProperty securityIdentifier = new SimpleStringProperty();

    /**
     * The name of the company, bond or repo this listing concerns.
     */
    private final StringProperty name = new SimpleStringProperty();

    /**
     * The type of this listing.
     */
    private final ObjectProperty<Type> type = new SimpleObjectProperty<>();

    /**
     * Fetches all listings currently on the market from the server.
     *
     * @return all listings on the market
     */
    @PublicAPI
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
    @PublicAPI
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
    @PublicAPI
    @Nullable
    public static Long getOutstandingShares(String securityIdentifier) {
        return Http.getSingleObjectFromApi(Long.class, "/api/listings/outstandingshares/"
            + securityIdentifier);
    }

    /**
     * Returns the number of outstanding shares for this listing.
     *
     * @param listing the listing
     * @return the number of outstanding shares
     */
    @PublicAPI
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
    @PublicAPI
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
    @PublicAPI
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
    @PublicAPI
    @NotNull
    public static List<Company> getShareholders(Listing listing) {
        return getShareholders(listing.getSecurityIdentifier());
    }

    /**
     * @return the start date
     */
    @PublicAPI
    public ZonedDateTime getStartDate() {
        return startDate.getValue();
    }

    /**
     * @return the end date
     */
    @PublicAPI
    public ZonedDateTime getEndDate() {
        return endDate.getValue();
    }

    /**
     * @return the security identifier
     */
    @PublicAPI
    public String getSecurityIdentifier() {
        return securityIdentifier.getValue();
    }

    /**
     * @return the company, bond or repo name
     */
    @PublicAPI
    public String getName() {
        return name.getValue();
    }

    /**
     * @return the listing type
     */
    @PublicAPI
    public Type getType() {
        return type.getValue();
    }

    /**
     * @return the start date property.
     */
    @PublicAPI
    public ReadOnlyObjectProperty<ZonedDateTime> startDateProperty() {
        return startDate;
    }

    /**
     * @return the end date property.
     */
    @PublicAPI
    public ReadOnlyObjectProperty<ZonedDateTime> endDateProperty() {
        return endDate;
    }

    /**
     * @return the security identifier property.
     */
    @PublicAPI
    public ReadOnlyStringProperty securityIdentifierProperty() {
        return securityIdentifier;
    }

    /**
     * @return the name property.
     */
    @PublicAPI
    public ReadOnlyStringProperty nameProperty() {
        return name;
    }

    /**
     * @return the type property.
     */
    @PublicAPI
    public ReadOnlyObjectProperty<Type> typeProperty() {
        return type;
    }

    @Override
    public String toString() {
        return "Listing{"
            + "startDate=" + startDate
            + ", endDate=" + endDate
            + ", securityIdentifier='" + securityIdentifier.getValue() + '\''
            + ", name='" + name.getValue() + '\''
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

        return securityIdentifier.getValue() != null ? securityIdentifier.getValue()
            .equals(listing.securityIdentifier.getValue())
            : listing.securityIdentifier.getValue() == null;

    }

    @Override
    public int hashCode() {
        return securityIdentifier.getValue() != null ? securityIdentifier.getValue().hashCode() : 0;
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
