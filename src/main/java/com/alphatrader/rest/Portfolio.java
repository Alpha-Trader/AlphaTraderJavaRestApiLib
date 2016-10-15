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
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a company's portfolio in the game.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0
 */
public class Portfolio {
    /**
     * The logger for this class.
     */
    private static final Log log = LogFactory.getLog(Portfolio.class);

    /**
     * Gson instance for deserialization.
     */
    private static final Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class,
        new LocalDateTimeDeserializer()).create();
    /**
     * The amount of cash in this portfolio.
     */
    private double cash;
    /**
     * The amount of committed cash in this portfolio.
     */
    private double committedCash;
    /**
     * A list of all positions in this portfolio.
     */
    private List<Position> positions;

    /**
     * Creates a new portfolio with the given parameters.
     *
     * @param cash          the amount of cash
     * @param committedCash the amount of committed cash
     * @param positions     a list of all positions.
     */
    public Portfolio(double cash, double committedCash, List<Position> positions) {
        this.cash = cash;
        this.committedCash = committedCash;
        this.positions = positions;
    }

    /**
     * Fetches a company's portfolio from the server.
     *
     * @param company the company of which you want the portfolio from.
     * @return the portfolio of the given company
     */
    public static Portfolio getCompanyPortfolio(Company company) {
        Portfolio myReturn = null;
        try {
            HttpResponse<JsonNode> response = Http.getInstance().get("/api/portfolios/");
            myReturn = gson.fromJson(response.getBody().getObject().toString(), Portfolio.class);
        }
        catch (UnirestException ue) {
            log.error("Error fetching portfolio for company " + company.getName());
            ue.printStackTrace();
        }

        return myReturn;
    }

    /**
     * Creates a new portfolio object by parsing the provided json object.
     *
     * @param json the json to parse
     * @return the parsed portfolio
     */
    public static Portfolio createFromJson(JSONObject json) {
        Type positionListType = new TypeToken<ArrayList<Position>>() { }.getType();

        String positionsJson = json.getJSONArray("positions").toString();
        List<Position> positions = gson.fromJson(positionsJson, positionListType);

        return new Portfolio(json.getDouble("cash"), json.getDouble("committedCash"), positions);
    }

    /**
     * @return a list of all positions in this portfolio
     */
    public List<Position> getPositions() {
        return positions;
    }
}
