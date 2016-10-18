package com.alphatrader.rest;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.Type;
import java.net.URL;
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
    private final String id = null;

    /**
     * The company name.
     */
    private final String name = null;

    /**
     * The securities account id.
     */
    private final String securitiesAccountId = null;

    /**
     * The current amount of uncommitted cash.
     */
    private final BankAccount bankAccount = null;

    /**
     * The company's portfolio.
     */
    private Portfolio portfolio;

    /**
     * The current CEO.
     */
    private final User ceo = null;

    /**
     * The company listing on the market.
     */
    private final Listing listing = null;

    /**
     * The URL of the company logo.
     */
    private final URL logoUrl = null;

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
     * @return the securities accounts unique identifier.
     */
    public String getSecuritiesAccountId() {
        return securitiesAccountId;
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

    @Override
    public String toString() {
        return "Company{"
            + "id='" + id + '\''
            + ", name='" + name + '\''
            + ", securitiesAccountId='" + securitiesAccountId + '\''
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

        Company company = (Company) o;

        if (id != null ? !id.equals(company.id) : company.id != null) {
            return false;
        }
        if (name != null ? !name.equals(company.name) : company.name != null) {
            return false;
        }
        return securitiesAccountId != null ? securitiesAccountId.equals(company.securitiesAccountId)
            : company.securitiesAccountId == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (securitiesAccountId != null ? securitiesAccountId.hashCode() : 0);
        return result;
    }

    public Listing getListing() {
        return listing;
    }
}
