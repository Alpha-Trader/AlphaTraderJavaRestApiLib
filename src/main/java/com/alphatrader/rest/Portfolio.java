package com.alphatrader.rest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
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
    private final Position[] positions = null;

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
        return Arrays.asList(positions);
    }

    /**
     * @return the amount of cash
     */
    public Double getCash() {
        return cash;
    }

    /**
     * @return the amount of committed cash
     */
    public Double getCommittedCash() {
        return committedCash;
    }

    @Override
    public String toString() {
        return "Portfolio{"
            + "cash=" + cash
            + ", committedCash=" + committedCash
            + ", positions=" + Arrays.toString(positions)
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

        Portfolio portfolio = (Portfolio) o;

        if (cash != null ? !cash.equals(portfolio.cash) : portfolio.cash != null) {
            return false;
        }
        if (committedCash != null ? !committedCash.equals(portfolio.committedCash)
            : portfolio.committedCash != null) {
            return false;
        }
        return Arrays.equals(positions, portfolio.positions);
    }

    @Override
    public int hashCode() {
        int result = cash != null ? cash.hashCode() : 0;
        result = 31 * result + (committedCash != null ? committedCash.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(positions);
        return result;
    }
}
