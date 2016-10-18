/**
 *
 */
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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents an order in the game.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0.0
 */
public class Order {
    /**
     * The logger for this class.
     */
    private static final Log log = LogFactory.getLog(Order.class);

    /**
     * Gson instance for deserialization.
     */
    private static final Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class,
        new LocalDateTimeDeserializer()).create();

    /**
     * List type for gson deserialization.
     */
    private static final java.lang.reflect.Type listType = new TypeToken<ArrayList<Order>>() {
    }.getType();

    /**
     * The date and time the order was created
     */
    private final LocalDateTime creationDate = null;

    /**
     * Name of Security
     */
    private final Listing listing = null;

    /**
     * Type of Security
     */
    private final Type type = null;

    /**
     * Unique securityIdentifier of security
     */
    private final String securityIdentifier = null;

    /**
     * Number of shares entailed in Order
     */
    private final Integer numberOfShares = null;

    /**
     * The name of the counter party.
     */
    private final String counterPartyName = null;

    /**
     * The security account id of the counter party.
     */
    private final String counterParty = null;

    /**
     * The type of action this order represents.
     */
    private final Action action = null;

    /**
     * The committed cash of this order.
     */
    private final Double committedCash = null;

    /**
     * The price.
     */
    private final Double price = null;

    /**
     * The name of the owner.
     */
    private final String ownerName = null;

    /**
     * The security account identifier of the owner.
     */
    private final String owner = null;

    /**
     * The unique id of the order.
     */
    private final String id = null;

    /**
     * Fetches all otc orders of the given company.
     *
     * @param securitiesAccountId the securities account id
     * @return all otc orders
     */
    @NotNull
    public static List<Order> getOtcOrders(String securitiesAccountId) {
        return getFromApi("securityorders/counterparty/" + securitiesAccountId);
    }

    /**
     * Fetches all otc orders of the given company.
     *
     * @param company the company
     * @return all otc orders
     */
    @NotNull
    public static List<Order> getOtcOrders(Company company) {
        return getFromApi("securityorders/counterparty/" + company.getSecuritiesAccountId());
    }

    /**
     * Fetches all orders concerning the given security identifier.
     *
     * @param securityIdentifier the security identifier
     * @return all orders concerning the given identifier
     */
    @NotNull
    public static List<Order> getOrderForCompany(String securityIdentifier) {
        return getFromApi("orderlist/" + securityIdentifier);
    }

    /**
     * Fetches all orders concerning the given security identifier.
     *
     * @param company the company being traded
     * @return all orders concerning the given identifier
     */
    @NotNull
    public static List<Order> getOrderForCompany(Company company) {
        return getFromApi("orderlist/" + company.getListing().getSecurityIdentifier());
    }

    /**
     * Fetches all orders by the provided company.
     *
     * @param securitiesAccountId the securities account id
     * @return the list of orders
     */
    @NotNull
    public static List<Order> getOrders(String securitiesAccountId) {
        return getFromApi("securityorders/securitiesaccount/" + securitiesAccountId);
    }

    /**
     * Fetches all orders by the provided company.
     *
     * @param company the company looking to buy / sell
     * @return the list of orders
     */
    @NotNull
    public static List<Order> getOrders(Company company) {
        return getFromApi("securityorders/securitiesaccount/" + company.getSecuritiesAccountId());
    }

    /**
     * Generalized api request function.
     *
     * @param suffix the api suffix for the specific request
     * @return the requested list of orders
     */
    @NotNull
    private static List<Order> getFromApi(String suffix) {
        List<Order> myReturn = new ArrayList<>();

        try {
            HttpResponse<JsonNode> response = Http.getInstance().get("/api/" + suffix);
            String orders = response.getBody().getArray().toString();
            myReturn = gson.fromJson(orders, listType);
        }
        catch (UnirestException ue) {
            log.error("Error fetching orders: " + ue.getMessage());
            StringWriter stringWriter = new StringWriter();
            ue.printStackTrace(new PrintWriter(stringWriter));
            log.debug(stringWriter.toString());
        }

        return myReturn;
    }

    /**
     * Fetches the order with the given id from the API.
     *
     * @param id the id of the order
     * @return the requested order
     */
    @Nullable
    public static Order getById(String id) {
        Order myReturn = null;

        try {
            HttpResponse<JsonNode> response = Http.getInstance().get("/api/securityorders/" + id);
            String orders = response.getBody().getObject().toString();
            myReturn = gson.fromJson(orders, Order.class);
        }
        catch (UnirestException ue) {
            log.error("Error fetching orders: " + ue.getMessage());
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
        return getListing().getName();
    }

    /**
     * return the date of creation
     */
    public LocalDateTime getCreationDate() {

        return creationDate;
    }

    public Listing getListing() {
        return listing;
    }

    /**
     * @return the type of security
     */
    public Type getType() {
        return type;
    }

    /**
     * @return the security identifier
     */
    public String getSecurityIdentifier() {
        return securityIdentifier;
    }

    /**
     * @return the number of shares
     */
    public int getNumberOfShares() {
        return numberOfShares;
    }

    /**
     * The order type.
     */
    public enum Type {
        LIMIT,
        MARKET
    }

    /**
     * The requested action.
     */
    public enum Action {
        BUY,
        SELL
    }
}


    
    
    



