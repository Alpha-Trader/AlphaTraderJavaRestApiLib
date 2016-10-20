package com.alphatrader.rest;

import com.alphatrader.rest.util.ZonedDateTimeDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Before;
import org.junit.Test;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.junit.Assert.*;

/**
 * Test case for the {@link HourlyMarketStatistics} class.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0.0
 */
public class HourlyMarketStatisticsTest {
    private static final Gson gson = new GsonBuilder().registerTypeAdapter(ZonedDateTime.class,
        new ZonedDateTimeDeserializer()).create();

    private static final String JSON = "{\n" +
        "  \"id\": \"b6800547-d4bb-44b8-a109-33574f91b10e\",\n" +
        "  \"averageBondDurationInDays\": 1,\n" +
        "  \"averageBookValue\": 2,\n" +
        "  \"averageDailyWage\": 3,\n" +
        "  \"averageDesignatedSponsorRating\": \"D\",\n" +
        "  \"averageYieldToMaturity\": 4,\n" +
        "  \"bondFaceVolume\": 5,\n" +
        "  \"centralBankReserves\": 6,\n" +
        "  \"committedCash\": 7,\n" +
        "  \"corporateCash\": 8,\n" +
        "  \"date\": 1469950287872,\n" +
        "  \"mainInterestRate\": 9,\n" +
        "  \"marketCap\": 10,\n" +
        "  \"numberOfActiveOtherListings\": 11,\n" +
        "  \"numberOfBanks\": 12,\n" +
        "  \"numberOfBondOrders\": 13,\n" +
        "  \"numberOfCashoutPolls\": 14,\n" +
        "  \"numberOfCommittedShares\": 15,\n" +
        "  \"numberOfCompanies\": 16,\n" +
        "  \"numberOfDesignatedSponsors\": 17,\n" +
        "  \"numberOfLiquidationPolls\": 18,\n" +
        "  \"numberOfOrders\": 19,\n" +
        "  \"numberOfOrders24h\": 20,\n" +
        "  \"numberOfOtcOrders\": 21,\n" +
        "  \"numberOfOtherListings\": 22,\n" +
        "  \"numberOfOtherOrders\": 23,\n" +
        "  \"numberOfPartnerUsers\": 24,\n" +
        "  \"numberOfPremiumUsers\": 25,\n" +
        "  \"numberOfRepoOrders\": 26,\n" +
        "  \"numberOfStockOrders\": 27,\n" +
        "  \"numberOfSystemBondOrders\": 28,\n" +
        "  \"numberOfSystemRepoOrders\": 29,\n" +
        "  \"numberOfUsers\": 30,\n" +
        "  \"orderVolume\": 31,\n" +
        "  \"orderVolume24h\": 32,\n" +
        "  \"privateCash\": 33,\n" +
        "  \"systemBondFaceVolume\": 34\n" +
        "}";

    private HourlyMarketStatistics toTest;

    @Before
    public void setUp() throws Exception {
        toTest = gson.fromJson(JSON, HourlyMarketStatistics.class);
    }

    @Test
    public void getAll() throws Exception {

    }

    @Test
    public void getId() throws Exception {
        assertEquals("b6800547-d4bb-44b8-a109-33574f91b10e", toTest.getId());
    }

    @Test
    public void getAverageBondDurationInDays() throws Exception {
        assertEquals(1.0, toTest.getAverageBondDurationInDays(), 0.0001);
    }

    @Test
    public void getAverageBookValue() throws Exception {
        assertEquals(2.0, toTest.getAverageBookValue(), 0.0001);
    }

    @Test
    public void getAverageDailyWage() throws Exception {
        assertEquals(3.0, toTest.getAverageDailyWage(), 0.0001);
    }

    @Test
    public void getAverageDesignatedSponsorRating() throws Exception {
        assertEquals(SponsorRating.SponsorRatingLetter.D, toTest.getAverageDesignatedSponsorRating());
    }

    @Test
    public void getAverageYieldToMaturity() throws Exception {
        assertEquals(4.0, toTest.getAverageYieldToMaturity(), 0.0001);
    }

    @Test
    public void getBondFaceVolume() throws Exception {
        assertEquals(5.0, toTest.getBondFaceVolume(), 0.0001);
    }

    @Test
    public void getCentralBankReserves() throws Exception {
        assertEquals(6.0, toTest.getCentralBankReserves(), 0.0001);
    }

    @Test
    public void getCommittedCash() throws Exception {
        assertEquals(7.0, toTest.getCommittedCash(), 0.0001);
    }

    @Test
    public void getCorporateCash() throws Exception {
        assertEquals(8.0, toTest.getCorporateCash(), 0.0001);
    }

    @Test
    public void getDate() throws Exception {
        ZonedDateTime date = ZonedDateTime.ofInstant(Instant.ofEpochMilli(1469950287872L),
            ZoneId.systemDefault());
        assertEquals(date, toTest.getDate());
    }

    @Test
    public void getMainInterestRate() throws Exception {
        assertEquals(9.0, toTest.getMainInterestRate(), 0.0001);
    }

    @Test
    public void getMarketCap() throws Exception {
        assertEquals(10.0, toTest.getMarketCap(), 0.0001);
    }

    @Test
    public void getNumberOfActiveListings() throws Exception {
        assertEquals(Long.valueOf(11), toTest.getNumberOfActiveOtherListings());
    }

    @Test
    public void getNumberOfBanks() throws Exception {
        assertEquals(Long.valueOf(12), toTest.getNumberOfBanks());
    }

    @Test
    public void getNumberOfBondOrders() throws Exception {
        assertEquals(Long.valueOf(13), toTest.getNumberOfBondOrders());
    }

    @Test
    public void getNumberOfCashoutPolls() throws Exception {
        assertEquals(Long.valueOf(14), toTest.getNumberOfCashoutPolls());
    }

    @Test
    public void getNumberOfCommittedShares() throws Exception {
        assertEquals(Long.valueOf(15), toTest.getNumberOfCommittedShares());
    }

    @Test
    public void getNumberOfCompanies() throws Exception {
        assertEquals(Long.valueOf(16), toTest.getNumberOfCompanies());
    }

    @Test
    public void getNumberOfDesignatedSponsors() throws Exception {
        assertEquals(Long.valueOf(17), toTest.getNumberOfDesignatedSponsors());
    }

    @Test
    public void getNumberOfLiquidationPolls() throws Exception {
        assertEquals(Long.valueOf(18), toTest.getNumberOfLiquidationPolls());
    }

    @Test
    public void getNumberOfOrders() throws Exception {
        assertEquals(Long.valueOf(19), toTest.getNumberOfOrders());
    }

    @Test
    public void getNumberOfOrders24h() throws Exception {
        assertEquals(Long.valueOf(20), toTest.getNumberOfOrders24h());
    }

    @Test
    public void getNumberOfOtcOrders() throws Exception {
        assertEquals(Long.valueOf(21), toTest.getNumberOfOtcOrders());
    }

    @Test
    public void getNumberOfOtherListings() throws Exception {
        assertEquals(Long.valueOf(22), toTest.getNumberOfOtherListings());
    }

    @Test
    public void getNumberOfOtherOrders() throws Exception {
        assertEquals(Long.valueOf(23), toTest.getNumberOfOtherOrders());
    }

    @Test
    public void getNumberOfPartnerUsers() throws Exception {
        assertEquals(Long.valueOf(24), toTest.getNumberOfPartnerUsers());
    }

    @Test
    public void getNumberOfPremiumUsers() throws Exception {
        assertEquals(Long.valueOf(25), toTest.getNumberOfPremiumUsers());
    }

    @Test
    public void getNumberOfRepoOrders() throws Exception {
        assertEquals(Long.valueOf(26), toTest.getNumberOfRepoOrders());
    }

    @Test
    public void getNumberOfStockOrders() throws Exception {
        assertEquals(Long.valueOf(27), toTest.getNumberOfStockOrders());
    }

    @Test
    public void getNumberOfSystemBondOrders() throws Exception {
        assertEquals(Long.valueOf(28), toTest.getNumberOfSystemBondOrders());
    }

    @Test
    public void getNumberOfSystemRepoOrders() throws Exception {
        assertEquals(Long.valueOf(29), toTest.getNumberOfSystemRepoOrders());
    }

    @Test
    public void getNumberOfUsers() throws Exception {
        assertEquals(Long.valueOf(30), toTest.getNumberOfUsers());
    }

    @Test
    public void getOrderVolume() throws Exception {
        assertEquals(31.0, toTest.getOrderVolume(), 0.0001);
    }

    @Test
    public void getOrderVolume24h() throws Exception {
        assertEquals(32.0, toTest.getOrderVolume24h(), 0.0001);
    }

    @Test
    public void getPrivateCash() throws Exception {
        assertEquals(33.0, toTest.getPrivateCash(), 0.0001);
    }

    @Test
    public void getSystemBondFaceVolume() throws Exception {
        assertEquals(34.0, toTest.getSystemBondFaceVolume(), 0.0001);
    }

    @Test
    public void testToString() throws Exception {
        assertTrue(toTest.toString().startsWith(toTest.getClass().getSimpleName()));
    }

    @Test
    public void testEquals() throws Exception {
        assertTrue(toTest.equals(toTest));
        assertFalse(toTest.equals(null));
        assertFalse(toTest.equals("Test"));

        HourlyMarketStatistics other = gson.fromJson("{\n" +
            "  \"id\": \"12345\"\n" +
            "}", HourlyMarketStatistics.class);

        assertFalse(toTest.equals(other));
    }

    @Test
    public void testHashCode() throws Exception {
        HourlyMarketStatistics reference = gson.fromJson(JSON, HourlyMarketStatistics.class);
        assertEquals(reference.hashCode(), toTest.hashCode());
    }
}