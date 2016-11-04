package com.alphatrader.rest;

import javafx.beans.property.*;
import org.jetbrains.annotations.NotNull;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * Represents the hourly market statistics in the game.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0.0
 */
public class HourlyMarketStatistics {
    /**
     * The unique id of the statistics object.
     */
    private final StringProperty id = new SimpleStringProperty();

    /**
     * The average bond duration in days.
     */
    private final DoubleProperty averageBondDurationInDays = new SimpleDoubleProperty();

    /**
     * The average book value.
     */
    private final DoubleProperty averageBookValue = new SimpleDoubleProperty();

    /**
     * The average daily wage.
     */
    private final DoubleProperty averageDailyWage = new SimpleDoubleProperty();

    /**
     * The average designated sponsor rating.
     */
    private final ObjectProperty<SponsorRating.SponsorRatingLetter> averageDesignatedSponsorRating
        = new SimpleObjectProperty<>();

    /**
     * The average yield to maturity.
     */
    private final DoubleProperty averageYieldToMaturity = new SimpleDoubleProperty();

    /**
     * The cumulative face volume of all bonds.
     */
    private final DoubleProperty bondFaceVolume = new SimpleDoubleProperty();

    /**
     * The cumulative central bank reserves.
     */
    private final DoubleProperty centralBankReserves = new SimpleDoubleProperty();

    /**
     * The cumulative committed cash.
     */
    private final DoubleProperty committedCash = new SimpleDoubleProperty();

    /**
     * The cumulative corporate cash.
     */
    private final DoubleProperty corporateCash = new SimpleDoubleProperty();

    /**
     * The date the statistics was created.
     */
    private final ObjectProperty<ZonedDateTime> date = new SimpleObjectProperty<>();

    /**
     * The current main interest rate.
     */
    private final DoubleProperty mainInterestRate = new SimpleDoubleProperty();

    /**
     * The current market cap.
     */
    private final DoubleProperty marketCap = new SimpleDoubleProperty();

    /**
     * The number of active listings.
     */
    private final LongProperty numberOfActiveOtherListings = new SimpleLongProperty();

    /**
     * The number of banks.
     */
    private final LongProperty numberOfBanks = new SimpleLongProperty();

    /**
     * The number of bond orders.
     */
    private final LongProperty numberOfBondOrders = new SimpleLongProperty();

    /**
     * The number of cashout polls.
     */
    private final LongProperty numberOfCashoutPolls = new SimpleLongProperty();

    /**
     * The number of committed shares.
     */
    private final LongProperty numberOfCommittedShares = new SimpleLongProperty();

    /**
     * The number of companies.
     */
    private final LongProperty numberOfCompanies = new SimpleLongProperty();

    /**
     * The number of designated sponsors.
     */
    private final LongProperty numberOfDesignatedSponsors = new SimpleLongProperty();

    /**
     * The number of liquidation polls.
     */
    private final LongProperty numberOfLiquidationPolls = new SimpleLongProperty();

    /**
     * The number of orders.
     */
    private final LongProperty numberOfOrders = new SimpleLongProperty();

    /**
     * The number of orders in the last 24 hours.
     */
    private final LongProperty numberOfOrders24h = new SimpleLongProperty();

    /**
     * The number of over the counter orders.
     */
    private final LongProperty numberOfOtcOrders = new SimpleLongProperty();

    /**
     * The number of other listings.
     */
    private final LongProperty numberOfOtherListings = new SimpleLongProperty();

    /**
     * The number of other orders.
     */
    private final LongProperty numberOfOtherOrders = new SimpleLongProperty();

    /**
     * The number of users with partner status.
     */
    private final LongProperty numberOfPartnerUsers = new SimpleLongProperty();

    /**
     * The number of users with premium status.
     */
    private final LongProperty numberOfPremiumUsers = new SimpleLongProperty();

    /**
     * The number of repo orders.
     */
    private final LongProperty numberOfRepoOrders = new SimpleLongProperty();

    /**
     * The number of stock orders.
     */
    private final LongProperty numberOfStockOrders = new SimpleLongProperty();

    /**
     * The number of system bond orders.
     */
    private final LongProperty numberOfSystemBondOrders = new SimpleLongProperty();

    /**
     * The number of system repo orders.
     */
    private final LongProperty numberOfSystemRepoOrders = new SimpleLongProperty();

    /**
     * The number of users.
     */
    private final LongProperty numberOfUsers = new SimpleLongProperty();

    /**
     * The order volume.
     */
    private final DoubleProperty orderVolume = new SimpleDoubleProperty();

    /**
     * The order volume in the last 24 hours.
     */
    private final DoubleProperty orderVolume24h = new SimpleDoubleProperty();

    /**
     * The cumulative amount of private cash.
     */
    private final DoubleProperty privateCash = new SimpleDoubleProperty();

    /**
     * The cumulative amount of system bond face volumes.
     */
    private final DoubleProperty systemBondFaceVolume = new SimpleDoubleProperty();

    /**
     * Fetches the hourly market statistics from the server.
     *
     * @return a list of the hourly market statistics
     */
    @PublicAPI
    @NotNull
    public static List<HourlyMarketStatistics> getAll() {
        return Http.getMultipleObjectFromApi(HourlyMarketStatistics.class, "/api/marketstatistics/");
    }

    /**
     * @return the unique id
     */
    @PublicAPI
    public String getId() {
        return id.getValue();
    }

    /**
     * @return the average bond duration in days
     */
    @PublicAPI
    public Double getAverageBondDurationInDays() {
        return averageBondDurationInDays.getValue();
    }

    /**
     * @return the average book value
     */
    @PublicAPI
    public Double getAverageBookValue() {
        return averageBookValue.getValue();
    }

    /**
     * @return the average daily wage
     */
    @PublicAPI
    public Double getAverageDailyWage() {
        return averageDailyWage.getValue();
    }

    /**
     * @return the average designated sponsor rating
     */
    @PublicAPI
    public SponsorRating.SponsorRatingLetter getAverageDesignatedSponsorRating() {
        return averageDesignatedSponsorRating.getValue();
    }

    /**
     * @return the average yield to maturity
     */
    @PublicAPI
    public Double getAverageYieldToMaturity() {
        return averageYieldToMaturity.getValue();
    }

    /**
     * @return the cumulative bond face volume
     */
    @PublicAPI
    public Double getBondFaceVolume() {
        return bondFaceVolume.getValue();
    }

    /**
     * @return the amount of central bank reserves
     */
    @PublicAPI
    public Double getCentralBankReserves() {
        return centralBankReserves.getValue();
    }

    /**
     * @return the amount of committed cash
     */
    @PublicAPI
    public Double getCommittedCash() {
        return committedCash.getValue();
    }

    /**
     * @return the amount of corporate cash
     */
    @PublicAPI
    public Double getCorporateCash() {
        return corporateCash.getValue();
    }

    /**
     * @return the date
     */
    @PublicAPI
    public ZonedDateTime getDate() {
        return date.getValue();
    }

    /**
     * @return the main interest rate
     */
    @PublicAPI
    public Double getMainInterestRate() {
        return mainInterestRate.getValue();
    }

    /**
     * @return the market cap
     */
    @PublicAPI
    public Double getMarketCap() {
        return marketCap.getValue();
    }

    /**
     * @return the number of active listings
     */
    @PublicAPI
    public Long getNumberOfActiveOtherListings() {
        return numberOfActiveOtherListings.getValue();
    }

    /**
     * @return the number of banks
     */
    @PublicAPI
    public Long getNumberOfBanks() {
        return numberOfBanks.getValue();
    }

    /**
     * @return the number of bond orders
     */
    @PublicAPI
    public Long getNumberOfBondOrders() {
        return numberOfBondOrders.getValue();
    }

    /**
     * @return the number of cashout polls
     */
    @PublicAPI
    public Long getNumberOfCashoutPolls() {
        return numberOfCashoutPolls.getValue();
    }

    /**
     * @return the number of committed shares
     */
    @PublicAPI
    public Long getNumberOfCommittedShares() {
        return numberOfCommittedShares.getValue();
    }

    /**
     * @return the number of companies
     */
    @PublicAPI
    public Long getNumberOfCompanies() {
        return numberOfCompanies.getValue();
    }

    /**
     * @return the number of designated sponsors
     */
    @PublicAPI
    public Long getNumberOfDesignatedSponsors() {
        return numberOfDesignatedSponsors.getValue();
    }

    /**
     * @return the number of liquidation polls
     */
    @PublicAPI
    public Long getNumberOfLiquidationPolls() {
        return numberOfLiquidationPolls.getValue();
    }

    /**
     * @return the number of orders
     */
    @PublicAPI
    public Long getNumberOfOrders() {
        return numberOfOrders.getValue();
    }

    /**
     * @return the number of orders in the last 24 hours
     */
    @PublicAPI
    public Long getNumberOfOrders24h() {
        return numberOfOrders24h.getValue();
    }

    /**
     * @return the number of over the counter orders
     */
    @PublicAPI
    public Long getNumberOfOtcOrders() {
        return numberOfOtcOrders.getValue();
    }

    /**
     * @return the number of other listings
     */
    @PublicAPI
    public Long getNumberOfOtherListings() {
        return numberOfOtherListings.getValue();
    }

    /**
     * @return the number of other orders
     */
    @PublicAPI
    public Long getNumberOfOtherOrders() {
        return numberOfOtherOrders.getValue();
    }

    /**
     * @return the number of users with partner status
     */
    @PublicAPI
    public Long getNumberOfPartnerUsers() {
        return numberOfPartnerUsers.getValue();
    }

    /**
     * @return the number of users with premium status
     */
    @PublicAPI
    public Long getNumberOfPremiumUsers() {
        return numberOfPremiumUsers.getValue();
    }

    /**
     * @return the number of repo orders
     */
    @PublicAPI
    public Long getNumberOfRepoOrders() {
        return numberOfRepoOrders.getValue();
    }

    /**
     * @return the number of stock orders
     */
    @PublicAPI
    public Long getNumberOfStockOrders() {
        return numberOfStockOrders.getValue();
    }

    /**
     * @return the number of system bond orders
     */
    @PublicAPI
    public Long getNumberOfSystemBondOrders() {
        return numberOfSystemBondOrders.getValue();
    }

    /**
     * @return the number of system repo orders
     */
    @PublicAPI
    public Long getNumberOfSystemRepoOrders() {
        return numberOfSystemRepoOrders.getValue();
    }

    /**
     * @return the number of users
     */
    @PublicAPI
    public Long getNumberOfUsers() {
        return numberOfUsers.getValue();
    }

    /**
     * @return the cumulative order volume
     */
    @PublicAPI
    public Double getOrderVolume() {
        return orderVolume.getValue();
    }

    /**
     * @return the cumulative order volume in the last 24 hours
     */
    @PublicAPI
    public Double getOrderVolume24h() {
        return orderVolume24h.getValue();
    }

    /**
     * @return the cumulative private cash
     */
    @PublicAPI
    public Double getPrivateCash() {
        return privateCash.getValue();
    }

    /**
     * @return the cumulative system bond face volume
     */
    @PublicAPI
    public Double getSystemBondFaceVolume() {
        return systemBondFaceVolume.getValue();
    }

    @Override
    public String toString() {
        return "HourlyMarketStatistics{"
            + "id='" + id.getValue() + '\''
            + ", averageBondDurationInDays=" + averageBondDurationInDays.getValue()
            + ", averageBookValue=" + averageBookValue.getValue()
            + ", averageDailyWage=" + averageDailyWage.getValue()
            + ", averageDesignatedSponsorRating=" + averageDesignatedSponsorRating.getValue()
            + ", averageYieldToMaturity=" + averageYieldToMaturity.getValue()
            + ", bondFaceVolume=" + bondFaceVolume.getValue()
            + ", centralBankReserves=" + centralBankReserves.getValue()
            + ", committedCash=" + committedCash.getValue()
            + ", corporateCash=" + corporateCash.getValue()
            + ", date=" + date.getValue()
            + ", mainInterestRate=" + mainInterestRate.getValue()
            + ", marketCap=" + marketCap.getValue()
            + ", numberOfActiveOtherListings=" + numberOfActiveOtherListings.getValue()
            + ", numberOfBanks=" + numberOfBanks.getValue()
            + ", numberOfBondOrders=" + numberOfBondOrders.getValue()
            + ", numberOfCashoutPolls=" + numberOfCashoutPolls.getValue()
            + ", numberOfCommittedShares=" + numberOfCommittedShares.getValue()
            + ", numberOfCompanies=" + numberOfCompanies.getValue()
            + ", numberOfDesignatedSponsors=" + numberOfDesignatedSponsors.getValue()
            + ", numberOfLiquidationPolls=" + numberOfLiquidationPolls.getValue()
            + ", numberOfOrders=" + numberOfOrders.getValue()
            + ", numberOfOrders24h=" + numberOfOrders24h.getValue()
            + ", numberOfOtcOrders=" + numberOfOtcOrders.getValue()
            + ", numberOfOtherListings=" + numberOfOtherListings.getValue()
            + ", numberOfOtherOrders=" + numberOfOtherOrders.getValue()
            + ", numberOfPartnerUsers=" + numberOfPartnerUsers.getValue()
            + ", numberOfPremiumUsers=" + numberOfPremiumUsers.getValue()
            + ", numberOfRepoOrders=" + numberOfRepoOrders.getValue()
            + ", numberOfStockOrders=" + numberOfStockOrders.getValue()
            + ", numberOfSystemBondOrders=" + numberOfSystemBondOrders.getValue()
            + ", numberOfSystemRepoOrders=" + numberOfSystemRepoOrders.getValue()
            + ", numberOfUsers=" + numberOfUsers.getValue()
            + ", orderVolume=" + orderVolume.getValue()
            + ", orderVolume24h=" + orderVolume24h.getValue()
            + ", privateCash=" + privateCash.getValue()
            + ", systemBondFaceVolume=" + systemBondFaceVolume.getValue()
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

        return id.getValue() != null ? id.getValue().equals(that.id.getValue())
            : that.id.getValue() == null;
    }

    @Override
    public int hashCode() {
        return id.getValue() != null ? id.getValue().hashCode() : 0;
    }
}
