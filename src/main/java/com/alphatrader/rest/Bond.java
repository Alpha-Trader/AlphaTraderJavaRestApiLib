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
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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
@SuppressWarnings("ConstantConditions")
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
    @NotNull
    public static List<Bond> getAllBonds() {
        return getMultipleBondsFromApi("bonds/");
    }

    /**
     * Fetches all system bonds on the market from the server.
     *
     * @return all system bonds on the market
     */
    @NotNull
    public static List<Bond> getAllSystemBonds() {
        return getMultipleBondsFromApi("systembonds/");
    }

    /**
     * Fetches the bond with the given security identifier from the server.
     *
     * @param secId the security identifier of the bond you want
     * @return the bond with the given security identifier
     */
    @Nullable
    public static Bond getBondBySecurityIdentifier(String secId) {
        return getSingleBondFromApi("bonds/securityidentifier/" + secId);
    }

    /**
     * Fetches the system bond with the given security identifier from the server.
     *
     * @param secId the security identifier of the system bond you want
     * @return the system bond with the given security identifier
     */
    @Nullable
    public static Bond getSystemBondBySecurityIdentifier(String secId) {
        return getSingleBondFromApi("systembonds/securityidentifier/" + secId);
    }

    /**
     * Fetches the bond with the given id from the server.
     *
     * @param bondId the id of the bond you want
     * @return the bond with the given id
     */
    @Nullable
    public static Bond getBondById(String bondId) {
        return getSingleBondFromApi("bonds/" + bondId);
    }

    /**
     * Fetches the system bond with the given id from the server.
     *
     * @param bondId the id of the system bond you want
     * @return the system bond with the given id
     */
    @Nullable
    public static Bond getSystemBondById(String bondId) {
        return getSingleBondFromApi("systembonds/" + bondId);
    }

    /**
     * Wrapper function fetching single bonds from the API.
     *
     * @param suffix the api endpoint suffix
     * @return the requested bond or null of none found.
     */
    @Nullable
    private static Bond getSingleBondFromApi(String suffix) {
        Bond myReturn = null;

        try {
            HttpResponse<JsonNode> response = Http.getInstance().get("/api/" + suffix);

            if (response != null && response.getStatus() == 200) {
                myReturn = gson.fromJson(response.getBody()
                    .getObject()
                    .toString(), Bond.class);
            }
        }
        catch (UnirestException ue) {
            log.error("Error fetching bond: " + ue.getMessage());
            StringWriter stringWriter = new StringWriter();
            ue.printStackTrace(new PrintWriter(stringWriter));
            log.debug(stringWriter.toString());
        }

        return myReturn;
    }

    /**
     * Wrapper function for fetching multiple Bonds from the API.
     *
     * @param suffix the api endpoint suffix
     * @return all requested bonds
     */
    @NotNull
    public static List<Bond> getMultipleBondsFromApi(String suffix) {
        List<Bond> myReturn = new ArrayList<>();

        try {
            HttpResponse<JsonNode> response = Http.getInstance().get("/api/" + suffix);

            if (response != null && response.getStatus() == 200) {
                myReturn.addAll(gson.fromJson(response.getBody()
                    .getArray()
                    .toString(), listType));
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
