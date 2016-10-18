package com.alphatrader.rest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Represents the hourly market statistics in the game.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0.0
 */
@SuppressWarnings("ConstantConditions")
public class HourlyMarketStatistics {
    /**
     * The logger for this class.
     */
    private static final Log log = LogFactory.getLog(LastPrice.class);

    /**
     * The unique id of the statistics object.
     */
    private final String id = null;

    /**
     * The average bond duration in days.
     */
    private final Double averageBondDurationInDays = null;

    /**
     * The average book value.
     */
    private final Double averageBookValue = null;

    /**
     * The average daily wage.
     */
    private final Double averageDailyWage = null;

    /**
     * The average designated sponsor rating.
     */
    private final Double averageDesignatedSponsorRating = null;

    /**
     * The average yield to maturity.
     */
    private final Double averageYieldToMaturity = null;

    /**
     * The cumulative face volume of all bonds.
     */
    private final Double bondFaceVolume = null;

    /**
     * The cumulative central bank reserves.
     */
    private final Double centralBankReserves = null;

    /**
     * The cumulative committed cash.
     */
    private final Double committedCash = null;

    /**
     * The cumulative corporate cash.
     */
    private final Double corporateCash = null;

    /**
     * The date the statistics was created.
     */
    private final LocalDateTime date = null;

    /**
     * The current main interest rate.
     */
    private final Double mainInterestRate = null;

    /**
     * The current market cap.
     */
    private final Double marketCap = null;

    /**
     * The number of active listings.
     */
    private final Integer numberOfActiveListings = null;

    /**
     * The number of banks.
     */
    private final Integer numberOfBanks = null;

    /**
     * The number of bond orders.
     */
    private final Integer numberOfBondOrders = null;

    /**
     * The number of cashout polls.
     */
    private final Integer numberOfCashoutPolls = null;

    /**
     * The number of committed shares.
     */
    private final Integer numberOfCommittedShares = null;

    /**
     * The number of companies.
     */
    private final Integer numberOfCompanies = null;

    /**
     * The number of designated sponsors.
     */
    private final Integer numberOfDesignatedSponsors = null;

    /**
     * The number of liquidation polls.
     */
    private final Integer numberOfLiquidationPolls = null;

    /**
     * The number of orders.
     */
    private final Integer numberOfOrders = null;

    /**
     * The number of orders in the last 24 hours.
     */
    private final Integer numberOfOrders24h = null;

    /**
     * The number of over the counter orders.
     */
    private final Integer numberOfOtcOrders = null;

    /**
     * The number of other listings.
     */
    private final Integer numberOfOtherListings = null;

    /**
     * The number of other orders.
     */
    private final Integer numberOfOtherOrders = null;

    /**
     * The number of users with partner status.
     */
    private final Integer numberOfPartnerUsers = null;

    /**
     * The number of users with premium status.
     */
    private final Integer numberOfPremiumUsers = null;

    /**
     * The number of repo orders.
     */
    private final Integer numberOfRepoOrders = null;

    /**
     * The number of stock orders.
     */
    private final Integer numberOfStockOrders = null;

    /**
     * The number of system bond orders.
     */
    private final Integer numberOfSystemBondOrders = null;

    /**
     * The number of system repo orders.
     */
    private final Integer numberOfSystemRepoOrders = null;

    /**
     * The number of users.
     */
    private final Integer numberOfUsers = null;

    /**
     * The order volume.
     */
    private final Double orderVolume = null;

    /**
     * The order volume in the last 24 hours.
     */
    private final Double orderVolume24h = null;

    /**
     * The cumulative amount of private cash.
     */
    private final Double privateCash = null;

    /**
     * The cumulative amount of system bond face volumes.
     */
    private final Double systemBondFaceVolume = null;

    /**
     * Fetches the hourly market statistics from the server.
     *
     * @return a list of the hourly market statistics
     */
    @NotNull
    public static List<HourlyMarketStatistics> getAll() {
        return Http.getMultipleObjectFromApi(HourlyMarketStatistics.class, "/api/marketstatistics/");
    }

    /**
     * @return the unique id
     */
    public String getId() {
        return id;
    }

    /**
     * @return the average bond duration in days
     */
    public Double getAverageBondDurationInDays() {
        return averageBondDurationInDays;
    }

    /**
     * @return the average book value
     */
    public Double getAverageBookValue() {
        return averageBookValue;
    }

    /**
     * @return the average daily wage
     */
    public Double getAverageDailyWage() {
        return averageDailyWage;
    }

    /**
     * @return the average designated sponsor rating
     */
    public Double getAverageDesignatedSponsorRating() {
        return averageDesignatedSponsorRating;
    }

    /**
     * @return the average yield to maturity
     */
    public Double getAverageYieldToMaturity() {
        return averageYieldToMaturity;
    }

    /**
     * @return the cumulative bond face volume
     */
    public Double getBondFaceVolume() {
        return bondFaceVolume;
    }

    /**
     * @return the amount of central bank reserves
     */
    public Double getCentralBankReserves() {
        return centralBankReserves;
    }

    /**
     * @return the amount of committed cash
     */
    public Double getCommittedCash() {
        return committedCash;
    }

    /**
     * @return the amount of corporate cash
     */
    public Double getCorporateCash() {
        return corporateCash;
    }

    /**
     * @return the date
     */
    public LocalDateTime getDate() {
        return date;
    }

    /**
     * @return the main interest rate
     */
    public Double getMainInterestRate() {
        return mainInterestRate;
    }

    /**
     * @return the market cap
     */
    public Double getMarketCap() {
        return marketCap;
    }

    /**
     * @return the number of active listings
     */
    public Integer getNumberOfActiveListings() {
        return numberOfActiveListings;
    }

    /**
     * @return the number of banks
     */
    public Integer getNumberOfBanks() {
        return numberOfBanks;
    }

    /**
     * @return the number of bond orders
     */
    public Integer getNumberOfBondOrders() {
        return numberOfBondOrders;
    }

    /**
     * @return the number of cashout polls
     */
    public Integer getNumberOfCashoutPolls() {
        return numberOfCashoutPolls;
    }

    /**
     * @return the number of committed shares
     */
    public Integer getNumberOfCommittedShares() {
        return numberOfCommittedShares;
    }

    /**
     * @return the number of companies
     */
    public Integer getNumberOfCompanies() {
        return numberOfCompanies;
    }

    /**
     * @return the number of designated sponsors
     */
    public Integer getNumberOfDesignatedSponsors() {
        return numberOfDesignatedSponsors;
    }

    /**
     * @return the number of liquidation polls
     */
    public Integer getNumberOfLiquidationPolls() {
        return numberOfLiquidationPolls;
    }

    /**
     * @return the number of orders
     */
    public Integer getNumberOfOrders() {
        return numberOfOrders;
    }

    /**
     * @return the number of orders in the last 24 hours
     */
    public Integer getNumberOfOrders24h() {
        return numberOfOrders24h;
    }

    /**
     * @return the number of over the counter orders
     */
    public Integer getNumberOfOtcOrders() {
        return numberOfOtcOrders;
    }

    /**
     * @return the number of other listings
     */
    public Integer getNumberOfOtherListings() {
        return numberOfOtherListings;
    }

    /**
     * @return the number of other orders
     */
    public Integer getNumberOfOtherOrders() {
        return numberOfOtherOrders;
    }

    /**
     * @return the number of users with partner status
     */
    public Integer getNumberOfPartnerUsers() {
        return numberOfPartnerUsers;
    }

    /**
     * @return the number of users with premium status
     */
    public Integer getNumberOfPremiumUsers() {
        return numberOfPremiumUsers;
    }

    /**
     * @return the number of repo orders
     */
    public Integer getNumberOfRepoOrders() {
        return numberOfRepoOrders;
    }

    /**
     * @return the number of stock orders
     */
    public Integer getNumberOfStockOrders() {
        return numberOfStockOrders;
    }

    /**
     * @return the number of system bond orders
     */
    public Integer getNumberOfSystemBondOrders() {
        return numberOfSystemBondOrders;
    }

    /**
     * @return the number of system repo orders
     */
    public Integer getNumberOfSystemRepoOrders() {
        return numberOfSystemRepoOrders;
    }

    /**
     * @return the number of users
     */
    public Integer getNumberOfUsers() {
        return numberOfUsers;
    }

    /**
     * @return the cumulative order volume
     */
    public Double getOrderVolume() {
        return orderVolume;
    }

    /**
     * @return the cumulative order volume in the last 24 hours
     */
    public Double getOrderVolume24h() {
        return orderVolume24h;
    }

    /**
     * @return the cumulative private cash
     */
    public Double getPrivateCash() {
        return privateCash;
    }

    /**
     * @return the cumulative system bond face volume
     */
    public Double getSystemBondFaceVolume() {
        return systemBondFaceVolume;
    }

    @Override
    public String toString() {
        return "HourlyMarketStatistics{"
            + "id='" + id + '\''
            + ", averageBondDurationInDays=" + averageBondDurationInDays
            + ", averageBookValue=" + averageBookValue
            + ", averageDailyWage=" + averageDailyWage
            + ", averageDesignatedSponsorRating=" + averageDesignatedSponsorRating
            + ", averageYieldToMaturity=" + averageYieldToMaturity
            + ", bondFaceVolume=" + bondFaceVolume
            + ", centralBankReserves=" + centralBankReserves
            + ", committedCash=" + committedCash
            + ", corporateCash=" + corporateCash
            + ", date=" + date
            + ", mainInterestRate=" + mainInterestRate
            + ", marketCap=" + marketCap
            + ", numberOfActiveListings=" + numberOfActiveListings
            + ", numberOfBanks=" + numberOfBanks
            + ", numberOfBondOrders=" + numberOfBondOrders
            + ", numberOfCashoutPolls=" + numberOfCashoutPolls
            + ", numberOfCommittedShares=" + numberOfCommittedShares
            + ", numberOfCompanies=" + numberOfCompanies
            + ", numberOfDesignatedSponsors=" + numberOfDesignatedSponsors
            + ", numberOfLiquidationPolls=" + numberOfLiquidationPolls
            + ", numberOfOrders=" + numberOfOrders
            + ", numberOfOrders24h=" + numberOfOrders24h
            + ", numberOfOtcOrders=" + numberOfOtcOrders
            + ", numberOfOtherListings=" + numberOfOtherListings
            + ", numberOfOtherOrders=" + numberOfOtherOrders
            + ", numberOfPartnerUsers=" + numberOfPartnerUsers
            + ", numberOfPremiumUsers=" + numberOfPremiumUsers
            + ", numberOfRepoOrders=" + numberOfRepoOrders
            + ", numberOfStockOrders=" + numberOfStockOrders
            + ", numberOfSystemBondOrders=" + numberOfSystemBondOrders
            + ", numberOfSystemRepoOrders=" + numberOfSystemRepoOrders
            + ", numberOfUsers=" + numberOfUsers
            + ", orderVolume=" + orderVolume
            + ", orderVolume24h=" + orderVolume24h
            + ", privateCash=" + privateCash
            + ", systemBondFaceVolume=" + systemBondFaceVolume
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

        HourlyMarketStatistics that = (HourlyMarketStatistics) o;

        return id != null ? id.equals(that.id) : that.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
