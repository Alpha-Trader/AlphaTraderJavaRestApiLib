package com.alphatrader.rest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * Represents an order in the game.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0.0
 */
@SuppressWarnings("ConstantConditions")
public class Order {
    /**
     * The logger for this class.
     */
    private static final Log log = LogFactory.getLog(Order.class);

    /**
     * The date and time the order was created
     */
    private final ZonedDateTime creationDate = null;

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
        return getMultipleOrdersFromApi("securityorders/counterparty/" + securitiesAccountId);
    }

    /**
     * Fetches all otc orders of the given company.
     *
     * @param company the company
     * @return all otc orders
     */
    @NotNull
    public static List<Order> getOtcOrders(Company company) {
        return getMultipleOrdersFromApi("securityorders/counterparty/"
            + company.getSecuritiesAccountId());
    }

    /**
     * Fetches all orders concerning the given security identifier.
     *
     * @param securityIdentifier the security identifier
     * @return all orders concerning the given identifier
     */
    @NotNull
    public static List<Order> getOrderForCompany(String securityIdentifier) {
        return getMultipleOrdersFromApi("orderlist/" + securityIdentifier);
    }

    /**
     * Fetches all orders concerning the given security identifier.
     *
     * @param company the company being traded
     * @return all orders concerning the given identifier
     */
    @NotNull
    public static List<Order> getOrderForCompany(Company company) {
        return getMultipleOrdersFromApi("orderlist/" + company.getListing().getSecurityIdentifier());
    }

    /**
     * Fetches all orders by the provided company.
     *
     * @param securitiesAccountId the securities account id
     * @return the list of orders
     */
    @NotNull
    public static List<Order> getOrders(String securitiesAccountId) {
        return getMultipleOrdersFromApi("securityorders/securitiesaccount/" + securitiesAccountId);
    }

    /**
     * Fetches all orders by the provided company.
     *
     * @param company the company looking to buy / sell
     * @return the list of orders
     */
    @NotNull
    public static List<Order> getOrders(Company company) {
        return getMultipleOrdersFromApi("securityorders/securitiesaccount/"
            + company.getSecuritiesAccountId());
    }

    /**
     * Generalized api request function.
     *
     * @param suffix the api suffix for the specific request
     * @return the requested list of orders
     */
    @NotNull
    private static List<Order> getMultipleOrdersFromApi(String suffix) {
        return Http.getMultipleObjectFromApi(Order.class, "/api/" + suffix);
    }

    /**
     * Fetches the order with the given id from the API.
     *
     * @param id the id of the order
     * @return the requested order
     */
    @Nullable
    public static Order getById(String id) {
        return Http.getSingleObjectFromApi(Order.class, "/api/securityorders/" + id);
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
    public ZonedDateTime getCreationDate() {

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


    
    
    



