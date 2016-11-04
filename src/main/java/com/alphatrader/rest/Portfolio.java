package com.alphatrader.rest;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.ObservableList;
import org.jetbrains.annotations.Nullable;

/**
 * Represents a company's portfolio in the game.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0
 */
public class Portfolio {
    /**
     * The amount of cash in this portfolio.
     */
    private final DoubleProperty cash = new SimpleDoubleProperty();
    /**
     * The amount of committed cash in this portfolio.
     */
    private final DoubleProperty committedCash = new SimpleDoubleProperty();
    /**
     * A list of all positions in this portfolio.
     */
    private final ObservableList<Position> positions = new SimpleListProperty<>();

    /**
     * Fetches a company's portfolio from the server.
     *
     * @param company the company of which you want the portfolio from.
     * @return the portfolio of the given company
     */
    @PublicAPI
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
    @PublicAPI
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
    @PublicAPI
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
    @PublicAPI
    @Nullable
    public static Portfolio getFixedIncomePortfolio(String securitiesAccountId) {
        return Http.getSingleObjectFromApi(Portfolio.class, "/api/portfolios/fixedincome/"
            + securitiesAccountId);
    }

    /**
     * @return a list of all positions in this portfolio
     */
    @PublicAPI
    public ObservableList<Position> getPositions() {
        return positions;
    }

    /**
     * @return the amount of cash
     */
    @PublicAPI
    public Double getCash() {
        return cash.getValue();
    }

    /**
     * @return the amount of committed cash
     */
    @PublicAPI
    public Double getCommittedCash() {
        return committedCash.getValue();
    }

    /**
     * @return the cash property.
     */
    @PublicAPI
    public ReadOnlyDoubleProperty cashProperty() {
        return cash;
    }

    /**
     * @return the committed cash property.
     */
    @PublicAPI
    public ReadOnlyDoubleProperty committedCashProperty() {
        return committedCash;
    }

    @Override
    public String toString() {
        return "Portfolio{"
            + "cash=" + cash.getValue()
            + ", committedCash=" + committedCash.getValue()
            + ", positions=" + positions.toString()
            + '}';
    }

    @SuppressWarnings("SimplifiableIfStatement")
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Portfolio portfolio = (Portfolio) o;

        if (cash.getValue() != null ? !cash.getValue().equals(portfolio.cash.getValue())
            : portfolio.cash.getValue() != null) {
            return false;
        }
        if (committedCash.getValue() != null ? !committedCash.getValue().equals(
            portfolio.committedCash.getValue()) : portfolio.committedCash.getValue() != null) {
            return false;
        }
        return positions.equals(portfolio.positions);

    }

    @Override
    public int hashCode() {
        int result = cash.getValue() != null ? cash.getValue().hashCode() : 0;
        result = 31 * result + (committedCash.getValue() != null ? committedCash.getValue().hashCode()
            : 0);
        result = 31 * result + positions.hashCode();
        return result;
    }
}
