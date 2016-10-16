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

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a listing in the game.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0.0
 */
class Listing {
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
