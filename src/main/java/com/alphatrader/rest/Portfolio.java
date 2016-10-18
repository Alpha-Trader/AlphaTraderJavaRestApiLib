package com.alphatrader.rest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jetbrains.annotations.Nullable;

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
    @Nullable
    public static Portfolio getCompanyPortfolio(Company company) {
        return getCompanyPortfolio(company.getSecuritiesAccountId());
    }

    /**
     * Fetches a company's portfolio from the server.
     *
     * @param securitiesAccountId the securities account id of the company of which you want the
     *                            portfolio from.
     * @return the portfolio of the given company
     */
    @Nullable
    public static Portfolio getCompanyPortfolio(String securitiesAccountId) {
        return Http.getSingleObjectFromApi(Portfolio.class, "/api/portfolios/" + securitiesAccountId);
    }

    /**
     * Fetches a company's fixed income portfolio from the server.
     *
     * @param company the company of which you want the portfolio from.
     * @return the fixed income portfolio of the given company
     */
    @Nullable
    public static Portfolio getFixedIncomePortfolio(Company company) {
        return getFixedIncomePortfolio(company.getSecuritiesAccountId());
    }

    /**
     * Fetches a company's fixed income portfolio from the server.
     *
     * @param securitiesAccountId the securities account id of the company of which you want the
     *                            portfolio from.
     * @return the fixed income portfolio of the given company
     */
    @Nullable
    public static Portfolio getFixedIncomePortfolio(String securitiesAccountId) {
        return Http.getSingleObjectFromApi(Portfolio.class, "/api/portfolios/fixedincome/"
            + securitiesAccountId);
    }

    /**
     * @return a list of all positions in this portfolio
     */
    public List<Position> getPositions() {
        return positions;
    }
}
