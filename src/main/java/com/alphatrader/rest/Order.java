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
 * @author frangelo
 */
public class Order {
    /**
     * Listing class necessary for gson deserialization.
     *
     * @author Christopher Guckes
     * @version 1.0
     */
    private static final class Listing {
        /**
         * The name of the company which shares you are buying.
         */
        String name;
    }

    /**
     * Gson instance for deserialization.
     */
    private static final Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer()).create();

    /**
     * List type for gson deserialization.
     */
    private static final Type listType = new TypeToken<ArrayList<Order>>(){}.getType();

    /**
     * Fetches all unfilled orders  of the user
     *
     * @return all unfilled orders
     */
    public static List<Order> getUnfilledOrders(Company company) {
        List<Order> myReturn = new ArrayList<>();

        try {
            HttpResponse<JsonNode> response = Http.getInstance().get("/api/securityorders/securitiesaccount/");
            String unfilledOrders = response.getBody().getArray().toString();
            myReturn = gson.fromJson(unfilledOrders, listType);
        } catch (UnirestException e) {
            System.err.println("Error fetching unfilled Orders: " + e.getMessage());
        }

        return myReturn;
    }

    /**
     * Creates a Order from the api json answers
     */
    public static Order createFromJson(String json) {
        return gson.fromJson(json, Order.class);
    }

    /**
     * The date and time the order was created
     */
    private LocalDateTime creationDate;

    /**
     * Name of Security
     */
    private Listing listing = new Listing();
    /**
     * Type of Security
     */
    private String type;
    /**
     * Unique securityIdentifier of security
     */
    private String securityIdentifier;
    /**
     * Number of shares entailed in Order
     */
    private int numberOfShares;
    /**
     * Volume of Order
     */
    private double volume;

    public Order(LocalDateTime creationDate, String name, String securityIdentifier, String type, int numberOfShares) {
        this.creationDate = creationDate;
        this.listing.name = name;
        this.type = type;
        this.numberOfShares = numberOfShares;
        this.securityIdentifier = securityIdentifier;
    }

    @Override
    public String toString() {
        return "Order{" +
            "name='" + listing.name + '\'' +
            ", creationDate=" + creationDate +
            ", type=" + type +
            ", volume=" + volume +
            ", numberOfShares=" + numberOfShares +
            ", securityIdentifier=" + securityIdentifier +
            '}';
    }


    /**
     * @return the name
     */
    public String getName() {
        return listing.name;
    }

    /**
     * return the date of creation
     */
    public LocalDateTime getCreationDate() {

        return creationDate;
    }

    /**
     * @return the type of security
     */
    public String getType() {
        return type;
    }

    public String getSecurityIdentifier() {
        return securityIdentifier;
    }

    public int getNumberOfShares() {
        return numberOfShares;
    }
}


    
    
    



