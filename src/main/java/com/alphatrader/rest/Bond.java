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
 * Represents a bond in the game. Contains factory methods to handle API communication as well.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0
 */
public class Bond {

    /**
     * The logger for this class. Use this to write messages to the console.
     */
    private static final Log log = LogFactory.getLog(Bond.class);

    /**
     * Gson instance for deserialization.
     */
    private static final Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class,
        new LocalDateTimeDeserializer()).create();

    /**
     * List type for gson deserialization.
     */
    private static final Type listType = new TypeToken<ArrayList<Bond>>() { }.getType();

    /**
     * The bond's name.
     */
    private final String name = null;

    /**
     * The bond's volume.
     */
    private final Integer volume = null;

    /**
     * The overall interest rate of the bond.
     */
    private final Double interestRate = null;

    /**
     * The face value the bond was issued for.
     */
    private final Double faceValue = null;

    /**
     * The bond's date of maturity.
     */
    private final LocalDateTime maturityDate = null;

    /**
     * The date the bond was issued.
     */
    private final LocalDateTime issueDate = null;

    /**
     * The price spread
     */
    private final PriceSpread priceSpread = null;

    /**
     * The company that issued the bond.
     */
    private final Company issuer = null;

    /**
     * The listing as present on the market.
     */
    private final Listing listing = null;

    /**
     * The repurchase listing as present on the market.
     */
    private final Listing repurchaseListing = null;

    /**
     * The unique bond id.
     */
    private final String id = null;

    /**
     * Fetches all bonds currently on the market from the server.
     *
     * @return all bonds on the market
     */
    public static List<Bond> getAllBonds() {
        List<Bond> myReturn = new ArrayList<>();

        try {
            HttpResponse<JsonNode> response = Http.getInstance().get("/api/bonds/");

            if (response != null && response.getStatus() == 200) {
                myReturn = gson.fromJson(response.getBody()
                    .getArray()
                    .toString(), listType);
            }
        }
        catch (UnirestException ue) {
            log.error("Error fetching bonds: " + ue.getMessage());
            StringWriter stringWriter = new StringWriter();
            ue.printStackTrace(new PrintWriter(stringWriter));
            log.debug(stringWriter.toString());
        }

        return myReturn;
    }

    /**
     * Fetches the bond with the given security identifier from the server.
     *
     * @param secId the security identifier of the bond you want
     * @return the bond with the given security identifier
     */
    public static Bond getBondBySecurityIdentifier(String secId) {
        Bond myReturn = null;

        try {
            HttpResponse<JsonNode> response = Http.getInstance().get("/api/bonds/securityidentifier/"
                + secId);

            if (response != null && response.getStatus() == 200) {
                myReturn = gson.fromJson(response.getBody()
                    .getObject()
                    .toString(), Bond.class);
            }
        }
        catch (UnirestException ue) {
            log.error("Error fetching bond : " + ue.getMessage());
            StringWriter stringWriter = new StringWriter();
            ue.printStackTrace(new PrintWriter(stringWriter));
            log.debug(stringWriter.toString());
        }

        return myReturn;
    }

    /**
     * Fetches the bond with the given id from the server.
     *
     * @param bondId the id of the bond you want
     * @return the bond with the given id
     */
    public static Bond getBondById(String bondId) {
        Bond myReturn = null;

        try {
            HttpResponse<JsonNode> response = Http.getInstance().get("/api/bonds/"
                + bondId);

            if (response != null && response.getStatus() == 200) {
                myReturn = gson.fromJson(response.getBody()
                    .getObject()
                    .toString(), Bond.class);
            }
        }
        catch (UnirestException ue) {
            log.error("Error fetching bond : " + ue.getMessage());
            StringWriter stringWriter = new StringWriter();
            ue.printStackTrace(new PrintWriter(stringWriter));
            log.debug(stringWriter.toString());
        }

        return myReturn;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the volume
     */
    public int getVolume() {
        return volume;
    }

    /**
     * @return the interest rate
     */
    public double getInterestRate() {
        return interestRate;
    }

    /**
     * @return the face value
     */
    public double getFaceValue() {
        return faceValue;
    }

    /**
     * @return the maturity date
     */
    public LocalDateTime getMaturityDate() {
        return maturityDate;
    }


    /**
     * @return the issue date
     */
    public LocalDateTime getIssueDate() {
        return issueDate;
    }

    /**
     * @return the price spread
     */
    public PriceSpread getPriceSpread() {
        return priceSpread;
    }

    /**
     * @return the issuer
     */
    public Company getIssuer() {
        return issuer;
    }

    /**
     * @return the listing
     */
    public Listing getListing() {
        return listing;
    }

    /**
     * @return the repurchase listing
     */
    public Listing getRepurchaseListing() {
        return repurchaseListing;
    }

    /**
     * @return the unique id
     */
    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Bond{"
            + "name='" + name + '\''
            + ", volume=" + volume
            + ", interestRate=" + interestRate
            + ", faceValue=" + faceValue
            + ", maturityDate=" + maturityDate
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

        Bond bond = (Bond) o;

        if (name != null ? !name.equals(bond.name) : bond.name != null) {
            return false;
        }
        return id != null ? id.equals(bond.id) : bond.id == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        return result;
    }
}
