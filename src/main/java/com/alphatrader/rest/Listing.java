package com.alphatrader.rest;

import com.alphatrader.rest.util.LocalDateTimeDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jetbrains.annotations.Nullable;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a listing in the game.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0.0
 */
public class Listing {
    /**
     * The logger for this class.
     */
    private static final Log log = LogFactory.getLog(Listing.class);

    /**
     * Gson instance for deserialization.
     */
    private static final Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class,
        new LocalDateTimeDeserializer()).create();

    /**
     * List type for gson deserialization.
     */
    private static final java.lang.reflect.Type listType = new TypeToken<ArrayList<Listing>>() { }.getType();

    /**
     * The start date of the listing.
     */
    private final LocalDateTime startDate = null;

    /**
     * The end date of the listing.
     */
    private final LocalDateTime endDate = null;

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
    @Nullable
    public static List<Listing> getAllListings() {
        List<Listing> myReturn = new ArrayList<>();

        try {
            HttpResponse<JsonNode> response = Http.getInstance().get("/api/listings/");
            String listings = response.getBody().getArray().toString();
            myReturn = gson.fromJson(listings, listType);
        }
        catch (UnirestException ue) {
            log.error("Error fetching listings: " + ue.getMessage());
            StringWriter stringWriter = new StringWriter();
            ue.printStackTrace(new PrintWriter(stringWriter));
            log.debug(stringWriter.toString());
        }
        return myReturn;
    }

    /**
     * Fetches the listing with the given security identifier.
     *
     * @param securityIdentifier the identifier to look for
     * @return the listing of null if not found
     */
    @Nullable
    public static Listing getById(String securityIdentifier) {
        Listing myReturn = null;

        try {
            HttpResponse<JsonNode> response = Http.getInstance().get("/api/listings/"
                + securityIdentifier);
            String listing = response.getBody().getObject().toString();
            myReturn = gson.fromJson(listing, Listing.class);
        }
        catch (UnirestException ue) {
            log.error("Error fetching listing: " + ue.getMessage());
            StringWriter stringWriter = new StringWriter();
            ue.printStackTrace(new PrintWriter(stringWriter));
            log.debug(stringWriter.toString());
        }
        return myReturn;
    }

    @Nullable
    public static Double getOutstandingShares(String securityIdentifier) {
        Double myReturn = null;
        try {
            HttpResponse<JsonNode> response = Http.getInstance().get("/api/listings/outstandingshares/"
                + securityIdentifier);
            myReturn = Double.valueOf(response.getBody().toString());
        }
        catch (UnirestException ue) {
            log.error("Error fetching outstanding shares: " + ue.getMessage());
            StringWriter stringWriter = new StringWriter();
            ue.printStackTrace(new PrintWriter(stringWriter));
            log.debug(stringWriter.toString());
        }
        return null;
    }

    @Nullable
    public static Double getOutstandingShares(Listing listing) {
        return getOutstandingShares(listing.getSecurityIdentifier());
    }

    /**
     * Fetches all listings matching the search string.
     *
     * @param securityIdentifier part of the security identifier to look for
     * @return all listings on the market
     */
    @Nullable
    public static List<Listing> searchBySecurityIdentifier(String securityIdentifier) {
        List<Listing> myReturn = new ArrayList<>();

        try {
            HttpResponse<JsonNode> response = Http.getInstance().get("/api/search/listings/"
                + securityIdentifier);
            String listings = response.getBody().getArray().toString();
            myReturn = gson.fromJson(listings, listType);
        }
        catch (UnirestException ue) {
            log.error("Error fetching listings: " + ue.getMessage());
            StringWriter stringWriter = new StringWriter();
            ue.printStackTrace(new PrintWriter(stringWriter));
            log.debug(stringWriter.toString());
        }
        return myReturn;
    }

    /**
     * Fetches all shareholders of the listing with the given security identifier.
     *
     * @param securityIdentifier the identifier to look for
     * @return the list of shareholder companies for this listing
     */
    @Nullable
    public static List<Company> getShareholders(String securityIdentifier) {
        List<Company> myReturn = new ArrayList<>();
        java.lang.reflect.Type companyListType = new TypeToken<ArrayList<Company>>() { }.getType();

        try {
            HttpResponse<JsonNode> response = Http.getInstance().get("/api/shareholders/"
                + securityIdentifier);
            String listings = response.getBody().getArray().toString();
            myReturn = gson.fromJson(listings, companyListType);
        }
        catch (UnirestException ue) {
            log.error("Error fetching listings: " + ue.getMessage());
            StringWriter stringWriter = new StringWriter();
            ue.printStackTrace(new PrintWriter(stringWriter));
            log.debug(stringWriter.toString());
        }
        return myReturn;
    }

    /**
     * Fetches all shareholders of the listing.
     *
     * @param listing the listing
     * @return the list of shareholder companies for this listing
     */
    public static List<Company> getShareholders(Listing listing) {
        return getShareholders(listing.getSecurityIdentifier());
    }

    /**
     * @return the start date
     */
    public LocalDateTime getStartDate() {
        return startDate;
    }

    /**
     * @return the end date
     */
    public LocalDateTime getEndDate() {
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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Listing listing = (Listing) o;

        if (startDate != null ? !startDate.equals(listing.startDate) : listing.startDate != null) {
            return false;
        }
        if (endDate != null ? !endDate.equals(listing.endDate) : listing.endDate != null) {
            return false;
        }
        if (securityIdentifier != null ? !securityIdentifier.equals(listing.securityIdentifier)
            : listing.securityIdentifier != null) {
            return false;
        }
        if (name != null ? !name.equals(listing.name) : listing.name != null) {
            return false;
        }
        return type == listing.type;

    }

    @Override
    public int hashCode() {
        int result = startDate != null ? startDate.hashCode() : 0;
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (securityIdentifier != null ? securityIdentifier.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
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
