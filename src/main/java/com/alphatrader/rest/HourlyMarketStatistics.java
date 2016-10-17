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
import org.jetbrains.annotations.NotNull;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the hourly market statistics in the game.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0.0
 */
public class HourlyMarketStatistics {
    /**
     * The logger for this class.
     */
    private static final Log log = LogFactory.getLog(LastPrice.class);

    /**
     * Gson instance for deserialization.
     */
    private static final Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class,
        new LocalDateTimeDeserializer()).create();

    /**
     * List type for gson deserialization.
     */
    private static final java.lang.reflect.Type listType =
        new TypeToken<ArrayList<HourlyMarketStatistics>>() { }.getType();

    private final String id = null;
    private final Double averageBondDurationInDays = null;
    private final Double averageBookValue = null;
    private final Double averageDailyWage = null;
    private final Double averageDesignatedSponsorRating = null;
    private final Double averageYieldToMaturity = null;
    private final Double bondFaceVolume = null;
    private final Double centralBankReserves = null;
    private final Double committedCash = null;
    private final Double corporateCash = null;
    private final LocalDateTime date = null;
    private final Double mainInterestRate = null;
    private final Double marketCap = null;
    private final Integer numberOfActiveListings = null;
    private final Integer numberOfBanks = null;
    private final Integer numberOfBondOrders = null;
    private final Integer numberOfCashoutPolls = null;
    private final Integer numberOfCommittedShares = null;
    private final Integer numberOfCompanies = null;
    private final Integer numberOfDesignatedSponsors = null;
    private final Integer numberOfLiquidationPolls = null;
    private final Integer numberOfOrders = null;
    private final Integer numberOfOrders24h = null;
    private final Integer numberOfOtcOrders = null;
    private final Integer numberOfOtherListings = null;
    private final Integer numberOfOtherOrders = null;
    private final Integer numberOfPartnerUsers = null;
    private final Integer numberOfPremiumUsers = null;
    private final Integer numberOfRepoOrders = null;
    private final Integer numberOfStockOrders = null;
    private final Integer numberOfSystemBondOrders = null;
    private final Integer numberOfSystemRepoOrders = null;
    private final Integer numberOfUsers = null;
    private final Double orderVolume = null;
    private final Double orderVolume24h = null;
    private final Double privateCash = null;
    private final Double systemBondFaceVolume = null;

    /**
     * Fetches the hourly market statistics from the server.
     *
     * @return a list of the hourly market statistics
     */
    @NotNull
    public static List<HourlyMarketStatistics> getAll() {
        List<HourlyMarketStatistics> myReturn = new ArrayList<>();

        try {
            HttpResponse<JsonNode> response = Http.getInstance().get("/api/marketstatistics/");

            if (response != null && response.getStatus() == 200) {
                myReturn = gson.fromJson(response.getBody()
                    .getArray()
                    .toString(), listType);
            }
        }
        catch (UnirestException ue) {
            log.error("Error fetching hourly market statistics: " + ue.getMessage());
            StringWriter stringWriter = new StringWriter();
            ue.printStackTrace(new PrintWriter(stringWriter));
            log.debug(stringWriter.toString());
        }

        return myReturn;
    }

    public String getId() {
        return id;
    }

    public Double getAverageBondDurationInDays() {
        return averageBondDurationInDays;
    }

    public Double getAverageBookValue() {
        return averageBookValue;
    }

    public Double getAverageDailyWage() {
        return averageDailyWage;
    }

    public Double getAverageDesignatedSponsorRating() {
        return averageDesignatedSponsorRating;
    }

    public Double getAverageYieldToMaturity() {
        return averageYieldToMaturity;
    }

    public Double getBondFaceVolume() {
        return bondFaceVolume;
    }

    public Double getCentralBankReserves() {
        return centralBankReserves;
    }

    public Double getCommittedCash() {
        return committedCash;
    }

    public Double getCorporateCash() {
        return corporateCash;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public Double getMainInterestRate() {
        return mainInterestRate;
    }

    public Double getMarketCap() {
        return marketCap;
    }

    public Integer getNumberOfActiveListings() {
        return numberOfActiveListings;
    }

    public Integer getNumberOfBanks() {
        return numberOfBanks;
    }

    public Integer getNumberOfBondOrders() {
        return numberOfBondOrders;
    }

    public Integer getNumberOfCashoutPolls() {
        return numberOfCashoutPolls;
    }

    public Integer getNumberOfCommittedShares() {
        return numberOfCommittedShares;
    }

    public Integer getNumberOfCompanies() {
        return numberOfCompanies;
    }

    public Integer getNumberOfDesignatedSponsors() {
        return numberOfDesignatedSponsors;
    }

    public Integer getNumberOfLiquidationPolls() {
        return numberOfLiquidationPolls;
    }

    public Integer getNumberOfOrders() {
        return numberOfOrders;
    }

    public Integer getNumberOfOrders24h() {
        return numberOfOrders24h;
    }

    public Integer getNumberOfOtcOrders() {
        return numberOfOtcOrders;
    }

    public Integer getNumberOfOtherListings() {
        return numberOfOtherListings;
    }

    public Integer getNumberOfOtherOrders() {
        return numberOfOtherOrders;
    }

    public Integer getNumberOfPartnerUsers() {
        return numberOfPartnerUsers;
    }

    public Integer getNumberOfPremiumUsers() {
        return numberOfPremiumUsers;
    }

    public Integer getNumberOfRepoOrders() {
        return numberOfRepoOrders;
    }

    public Integer getNumberOfStockOrders() {
        return numberOfStockOrders;
    }

    public Integer getNumberOfSystemBondOrders() {
        return numberOfSystemBondOrders;
    }

    public Integer getNumberOfSystemRepoOrders() {
        return numberOfSystemRepoOrders;
    }

    public Integer getNumberOfUsers() {
        return numberOfUsers;
    }

    public Double getOrderVolume() {
        return orderVolume;
    }

    public Double getOrderVolume24h() {
        return orderVolume24h;
    }

    public Double getPrivateCash() {
        return privateCash;
    }

    public Double getSystemBondFaceVolume() {
        return systemBondFaceVolume;
    }

    @Override
    public String toString() {
        return "HourlyMarketStatistics{" +
            "id='" + id + '\'' +
            ", averageBondDurationInDays=" + averageBondDurationInDays +
            ", averageBookValue=" + averageBookValue +
            ", averageDailyWage=" + averageDailyWage +
            ", averageDesignatedSponsorRating=" + averageDesignatedSponsorRating +
            ", averageYieldToMaturity=" + averageYieldToMaturity +
            ", bondFaceVolume=" + bondFaceVolume +
            ", centralBankReserves=" + centralBankReserves +
            ", committedCash=" + committedCash +
            ", corporateCash=" + corporateCash +
            ", date=" + date +
            ", mainInterestRate=" + mainInterestRate +
            ", marketCap=" + marketCap +
            ", numberOfActiveListings=" + numberOfActiveListings +
            ", numberOfBanks=" + numberOfBanks +
            ", numberOfBondOrders=" + numberOfBondOrders +
            ", numberOfCashoutPolls=" + numberOfCashoutPolls +
            ", numberOfCommittedShares=" + numberOfCommittedShares +
            ", numberOfCompanies=" + numberOfCompanies +
            ", numberOfDesignatedSponsors=" + numberOfDesignatedSponsors +
            ", numberOfLiquidationPolls=" + numberOfLiquidationPolls +
            ", numberOfOrders=" + numberOfOrders +
            ", numberOfOrders24h=" + numberOfOrders24h +
            ", numberOfOtcOrders=" + numberOfOtcOrders +
            ", numberOfOtherListings=" + numberOfOtherListings +
            ", numberOfOtherOrders=" + numberOfOtherOrders +
            ", numberOfPartnerUsers=" + numberOfPartnerUsers +
            ", numberOfPremiumUsers=" + numberOfPremiumUsers +
            ", numberOfRepoOrders=" + numberOfRepoOrders +
            ", numberOfStockOrders=" + numberOfStockOrders +
            ", numberOfSystemBondOrders=" + numberOfSystemBondOrders +
            ", numberOfSystemRepoOrders=" + numberOfSystemRepoOrders +
            ", numberOfUsers=" + numberOfUsers +
            ", orderVolume=" + orderVolume +
            ", orderVolume24h=" + orderVolume24h +
            ", privateCash=" + privateCash +
            ", systemBondFaceVolume=" + systemBondFaceVolume +
            '}';
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
