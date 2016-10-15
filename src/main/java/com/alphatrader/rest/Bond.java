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

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a bond in the game. Contains factory methods to handle json input as well.
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
    private static final Type listType = new TypeToken<ArrayList<Bond>>() {
    }.getType();
    /**
     * The bond's name.
     */
    private String name;
    /**
     * The bond's volume.
     */
    private int volume;
    /**
     * The overall interest rate of the bond.
     */
    private double interestRate;
    /**
     * The face value the bond was issued for.
     */
    private double faceValue;
    /**
     * The bond's date of maturity.
     */
    private LocalDateTime maturityDate;

    /**
     * Creates a new bond with the given parameters.
     *
     * @param name         the name
     * @param volume       the volume
     * @param interestRate the interest rate
     * @param faceValue    the face value it was issued for
     * @param maturityDate the maturity date
     */
    public Bond(String name, int volume, double interestRate, double faceValue,
                LocalDateTime maturityDate) {
        this.name = name;
        this.volume = volume;
        this.interestRate = interestRate;
        this.faceValue = faceValue;
        this.maturityDate = maturityDate;
    }

    /**
     * Fetches all bonds currently on the market from the server.
     *
     * @return all bonds on the market
     */
    public static List<Bond> getAllBonds() {
        List<Bond> myReturn = new ArrayList<>();

        try {
            HttpResponse<JsonNode> response = Http.getInstance().get("/api/bonds/");
            String bonds = response.getBody().getArray().toString();
            myReturn = gson.fromJson(bonds, listType);
        }
        catch (UnirestException e) {
            log.error("Error fetching bonds : " + e.getMessage());
        }

        return myReturn;
    }

    /**
     * Creates a bond from the api json answers.
     *
     * @param json the json object you want to parse.
     * @return the parsed bond
     */
    public static Bond createFromJson(String json) {
        return gson.fromJson(json, Bond.class);
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

        if (volume != bond.volume) {
            return false;
        }
        if (Double.compare(bond.interestRate, interestRate) != 0) {
            return false;
        }
        if (Double.compare(bond.faceValue, faceValue) != 0) {
            return false;
        }
        if (name != null ? !name.equals(bond.name) : bond.name != null) {
            return false;
        }
        return maturityDate != null ? maturityDate.equals(bond.maturityDate) : bond.maturityDate == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = name != null ? name.hashCode() : 0;
        result = 31 * result + volume;
        temp = Double.doubleToLongBits(interestRate);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(faceValue);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (maturityDate != null ? maturityDate.hashCode() : 0);
        return result;
    }
}
