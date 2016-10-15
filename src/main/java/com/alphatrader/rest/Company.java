package com.alphatrader.rest;

import com.alphatrader.rest.util.Http;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a company in the game. Contains factory methods for parsing api json answers as well.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0
 */
public class Company {
    /**
     * The logger for this company.
     */
    private static final Log log = LogFactory.getLog(Company.class);

    /**
     * Gson instance for deserialization.
     */
    private static final Gson gson = new Gson();

    /**
     * List type for gson deserialization.
     */
    private static final Type listType = new TypeToken<ArrayList<Company>>() {
    }.getType();
    /**
     * The unique company identifier.
     */
    private final String id;
    /**
     * The company name.
     */
    private final String name;
    /**
     * The security identifier of this company's stocks on the market.
     */
    private final String securityIdentifier;
    /**
     * The securities account id.
     */
    private final String securitiesAccountId;
    /**
     * The current amount of uncommitted cash.
     */
    private double cash;
    /**
     * The company's portfolio.
     */
    private Portfolio portfolio;
    /**
     * The number of company shares in circulation.
     */
    private int outstandingShares;

    /**
     * Creates a new Company object with the given parameters
     *
     * @param id                  the unique company id
     * @param name                the company name
     * @param securityIdentifier  the security identifier
     * @param securitiesAccountId the securities account id
     * @param cash                the amount of uncommitted cash
     */
    public Company(String id,
                   String name,
                   String securityIdentifier,
                   String securitiesAccountId,
                   double cash,
                   int outstandingShares) {
        this.id = id;
        this.name = name;
        this.securityIdentifier = securityIdentifier;
        this.securitiesAccountId = securitiesAccountId;
        this.cash = cash;
        this.outstandingShares = outstandingShares;
    }

    /**
     * Fetches all companies currently employing the given user as a CEO.
     *
     * @param user the user who governs the company
     * @return a list of all companies governed by the user
     */
    public static List<Company> getAllUserCompanies(User user) {
        List<Company> myReturn = new ArrayList<>();

        try {
            HttpResponse<JsonNode> response = Http.getInstance().get("/api/companies/");
            String companyNodes = response.getBody().getArray().toString();
            myReturn = gson.fromJson(companyNodes, listType);
        }
        catch (UnirestException e) {
            log.error("Error fetching companies: " + e.getMessage());
        }

        return myReturn;
    }

    /**
     * fetches all companies in the game
     */

    public static List<Company> getAllCompanies() {
        List<Company> myReturn = new ArrayList<>();
        try {
            HttpResponse<JsonNode> response = Http.getInstance().get("/api/companies/all/");
            String companyNodes = response.getBody().getArray().toString();
            myReturn = gson.fromJson(companyNodes, listType);
        }
        catch (UnirestException e) {
            log.error("Error fetching companies: " + e.getMessage());
        }

        return myReturn;
    }

    /**
     * Creates a Company object from the API's json response.
     *
     * @param json the json object you want to parse
     * @return the parsed company
     */
    public static Company createFromJson(String json) {
        return gson.fromJson(json, Company.class);
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the securityIdentifier
     */
    public String getSecurityIdentifier() {
        return securityIdentifier;
    }

    /**
     * @return the securities accounts unique identifier.
     */
    public String getSecuritiesAccountId() {
        return securitiesAccountId;
    }

    /**
     * @return the company's cash
     */
    public double getCash() {
        return cash;
    }

    /**
     * @return the company's unique id.
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the portfolio of this company. This call will be evaluated lazily to avoid congesting the
     * server.
     *
     * @return the company's portfolio.
     */
    public Portfolio getPortfolio() {
        if (this.portfolio == null) {
            this.portfolio = Portfolio.getCompanyPortfolio(this);
        }
        return this.portfolio;
    }

    /**
     * Sets the company portfolio to the provided one.
     *
     * @param portfolio the new portfolio
     */
    public void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Company [name=" + name + ", securityIdentifier=" + securityIdentifier + "]";
    }

    public int getOutstandingShares() {
        return outstandingShares;
    }

    /**
     * Sets the outstanding shares to the new value.
     *
     * @param outstandingShares the number of outstanding shares
     */
    public void setOutstandingShares(int outstandingShares) {
        this.outstandingShares = outstandingShares;
    }
}
