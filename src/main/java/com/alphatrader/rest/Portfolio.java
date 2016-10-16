package com.alphatrader.rest;

import com.alphatrader.rest.util.Http;
import com.alphatrader.rest.util.LocalDateTimeDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;
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
    private final Double cash = null;
    /**
     * The amount of committed cash in this portfolio.
     */
    private final Double committedCash = null;
    /**
     * A list of all positions in this portfolio.
     */
    private final List<Position> positions = null;

    /**
     * Fetches a company's portfolio from the server.
     *
     * @param company the company of which you want the portfolio from.
     * @return the portfolio of the given company
     */
    public static Portfolio getCompanyPortfolio(Company company) {
        Portfolio myReturn = null;
        try {
            HttpResponse<JsonNode> response = Http.getInstance().get("/api/portfolios/"
                + company.getSecuritiesAccountId());
            myReturn = gson.fromJson(response.getBody().getObject().toString(), Portfolio.class);
        }
        catch (UnirestException ue) {
            log.error("Error fetching portfolio: " + ue.getMessage());
            StringWriter stringWriter = new StringWriter();
            ue.printStackTrace(new PrintWriter(stringWriter));
            log.debug(stringWriter.toString());
        }

        return myReturn;
    }

    /**
     * Fetches a company's fixed income portfolio from the server.
     *
     * @param company the company of which you want the portfolio from.
     * @return the fixed income portfolio of the given company
     */
    public static Portfolio getFixedIncomePortfolio(Company company) {
        Portfolio myReturn = null;
        try {
            HttpResponse<JsonNode> response = Http.getInstance().get("/api/portfolios/fixedincome/"
                + company.getSecuritiesAccountId());
            myReturn = gson.fromJson(response.getBody().getObject().toString(), Portfolio.class);
        }
        catch (UnirestException ue) {
            log.error("Error fetching portfolio: " + ue.getMessage());
            StringWriter stringWriter = new StringWriter();
            ue.printStackTrace(new PrintWriter(stringWriter));
            log.debug(stringWriter.toString());
        }

        return myReturn;
    }

    /**
     * @return a list of all positions in this portfolio
     */
    public List<Position> getPositions() {
        return positions;
    }
}
