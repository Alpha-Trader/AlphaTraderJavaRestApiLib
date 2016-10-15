/**
 *
 */
package com.alphatrader.rest;

import com.alphatrader.rest.util.Http;
import com.alphatrader.rest.util.LocalDateTimeDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a stock listing on the market.
 *
 * @author Frangelo Acampora (frangelo.acampora@gmail.com)
 * @version 1.0
 */
public class Stock {
    /**
     * Gson instance for deserialization.
     */
    private static final Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer()).create();

    /**
     * List type for gson deserialization.
     */
    private static final Type listType = new TypeToken<ArrayList<Stock>>(){}.getType();

    /**
     * Fetches all listings currently on the market from the server.
     *
     * @return all listings on the market
     */
    public static List<Stock> getAllStocks() {
        List<Stock> myReturn = new ArrayList<>();

        try {
            HttpResponse<JsonNode> response = Http.getInstance().get("/api/listings/");
            String listings = response.getBody().getArray().toString();
            myReturn = gson.fromJson(listings, listType);
        } catch (UnirestException e) {
            System.err.println("Error fetching bonds : " + e.getMessage());
        }
        return myReturn;
    }

    /**
     * Creates a Stock from the api json answers.
     * corrected
     * @param json the json object you want to parse
     * @return the parsed stock
     */
    public static Stock createFromJson(String json) {
        return gson.fromJson(json, Stock.class);
    }

    /**
     * The stock's name
     */
    private String name;

    /**
     * The stock's security identifier
     */
    private String securityIdentifier;

    /**
     * The stock's issue date
     */
    private LocalDateTime startDate;

    /**
     * Creates a new Stock with the given Parameters
     *
     * @param name
     * @param securityIdentifier
     * @param startDate
     */
    public Stock(String name, String securityIdentifier, LocalDateTime startDate) {
        this.name = name;
        this.securityIdentifier = securityIdentifier;
        this.startDate = startDate;
    }

    /**
     * @return the stock listing's name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the stock listing's security identifier
     */
    public String getSecurityIdentifier() {
        return securityIdentifier;
    }

    /**
     * @return the date the listing was issued
     */
    public LocalDateTime getStartDate() {
        return startDate;
    }

    /**
     * @return a string representation of the stock listing
     */
    public String toString() {
        return "Stock{" + "name='" + name + '\'' + ", securityIdentifier=" + securityIdentifier + ", startDate="
            + startDate + '}';
    }
}
