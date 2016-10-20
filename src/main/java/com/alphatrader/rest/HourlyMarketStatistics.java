package com.alphatrader.rest;

import org.jetbrains.annotations.NotNull;

import java.time.ZonedDateTime;
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
    private final SponsorRating.SponsorRatingLetter averageDesignatedSponsorRating = null;

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
    private final ZonedDateTime date = null;

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
    private final Long numberOfActiveOtherListings = null;

    /**
     * The number of banks.
     */
    private final Long numberOfBanks = null;

    /**
     * The number of bond orders.
     */
    private final Long numberOfBondOrders = null;

    /**
     * The number of cashout polls.
     */
    private final Long numberOfCashoutPolls = null;

    /**
     * The number of committed shares.
     */
    private final Long numberOfCommittedShares = null;

    /**
     * The number of companies.
     */
    private final Long numberOfCompanies = null;

    /**
     * The number of designated sponsors.
     */
    private final Long numberOfDesignatedSponsors = null;

    /**
     * The number of liquidation polls.
     */
    private final Long numberOfLiquidationPolls = null;

    /**
     * The number of orders.
     */
    private final Long numberOfOrders = null;

    /**
     * The number of orders in the last 24 hours.
     */
    private final Long numberOfOrders24h = null;

    /**
     * The number of over the counter orders.
     */
    private final Long numberOfOtcOrders = null;

    /**
     * The number of other listings.
     */
    private final Long numberOfOtherListings = null;

    /**
     * The number of other orders.
     */
    private final Long numberOfOtherOrders = null;

    /**
     * The number of users with partner status.
     */
    private final Long numberOfPartnerUsers = null;

    /**
     * The number of users with premium status.
     */
    private final Long numberOfPremiumUsers = null;

    /**
     * The number of repo orders.
     */
    private final Long numberOfRepoOrders = null;

    /**
     * The number of stock orders.
     */
    private final Long numberOfStockOrders = null;

    /**
     * The number of system bond orders.
     */
    private final Long numberOfSystemBondOrders = null;

    /**
     * The number of system repo orders.
     */
    private final Long numberOfSystemRepoOrders = null;

    /**
     * The number of users.
     */
    private final Long numberOfUsers = null;

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
    public SponsorRating.SponsorRatingLetter getAverageDesignatedSponsorRating() {
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
    public ZonedDateTime getDate() {
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
    public Long getNumberOfActiveOtherListings() {
        return numberOfActiveOtherListings;
    }

    /**
     * @return the number of banks
     */
    public Long getNumberOfBanks() {
        return numberOfBanks;
    }

    /**
     * @return the number of bond orders
     */
    public Long getNumberOfBondOrders() {
        return numberOfBondOrders;
    }

    /**
     * @return the number of cashout polls
     */
    public Long getNumberOfCashoutPolls() {
        return numberOfCashoutPolls;
    }

    /**
     * @return the number of committed shares
     */
    public Long getNumberOfCommittedShares() {
        return numberOfCommittedShares;
    }

    /**
     * @return the number of companies
     */
    public Long getNumberOfCompanies() {
        return numberOfCompanies;
    }

    /**
     * @return the number of designated sponsors
     */
    public Long getNumberOfDesignatedSponsors() {
        return numberOfDesignatedSponsors;
    }

    /**
     * @return the number of liquidation polls
     */
    public Long getNumberOfLiquidationPolls() {
        return numberOfLiquidationPolls;
    }

    /**
     * @return the number of orders
     */
    public Long getNumberOfOrders() {
        return numberOfOrders;
    }

    /**
     * @return the number of orders in the last 24 hours
     */
    public Long getNumberOfOrders24h() {
        return numberOfOrders24h;
    }

    /**
     * @return the number of over the counter orders
     */
    public Long getNumberOfOtcOrders() {
        return numberOfOtcOrders;
    }

    /**
     * @return the number of other listings
     */
    public Long getNumberOfOtherListings() {
        return numberOfOtherListings;
    }

    /**
     * @return the number of other orders
     */
    public Long getNumberOfOtherOrders() {
        return numberOfOtherOrders;
    }

    /**
     * @return the number of users with partner status
     */
    public Long getNumberOfPartnerUsers() {
        return numberOfPartnerUsers;
    }

    /**
     * @return the number of users with premium status
     */
    public Long getNumberOfPremiumUsers() {
        return numberOfPremiumUsers;
    }

    /**
     * @return the number of repo orders
     */
    public Long getNumberOfRepoOrders() {
        return numberOfRepoOrders;
    }

    /**
     * @return the number of stock orders
     */
    public Long getNumberOfStockOrders() {
        return numberOfStockOrders;
    }

    /**
     * @return the number of system bond orders
     */
    public Long getNumberOfSystemBondOrders() {
        return numberOfSystemBondOrders;
    }

    /**
     * @return the number of system repo orders
     */
    public Long getNumberOfSystemRepoOrders() {
        return numberOfSystemRepoOrders;
    }

    /**
     * @return the number of users
     */
    public Long getNumberOfUsers() {
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
            + ", numberOfActiveOtherListings=" + numberOfActiveOtherListings
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
