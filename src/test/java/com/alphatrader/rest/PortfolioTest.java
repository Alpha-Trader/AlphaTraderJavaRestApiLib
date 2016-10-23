package com.alphatrader.rest;

import com.alphatrader.rest.util.ZonedDateTimeDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Test case for the {@link Portfolio} class.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0
 */
public class PortfolioTest {
    private static HttpResponder httpResponder = HttpResponder.getInstance();
    private static final Gson gson = new GsonBuilder().registerTypeAdapter(ZonedDateTime.class,
        new ZonedDateTimeDeserializer()).create();

    private static final String JSON = "{\n" +
        "  \"committedCash\": 0,\n" +
        "  \"cash\": 400,\n" +
        "  \"positions\": [\n" +
        "    {\n" +
        "      \"currentAskPrice\": 3500,\n" +
        "      \"currentAskSize\": 850,\n" +
        "      \"currentBidPrice\": null,\n" +
        "      \"currentBidSize\": null,\n" +
        "      \"lastPrice\": {\n" +
        "        \"date\": 1475882777051,\n" +
        "        \"value\": 10\n" +
        "      },\n" +
        "      \"numberOfShares\": 100,\n" +
        "      \"volume\": 1000,\n" +
        "      \"committedShares\": 0,\n" +
        "      \"securityIdentifier\": \"ST57FA01\",\n" +
        "      \"listing\": {\n" +
        "        \"startDate\": 1469951384818,\n" +
        "        \"endDate\": null,\n" +
        "        \"securityIdentifier\": \"ST57FA01\",\n" +
        "        \"name\": \"Argo\",\n" +
        "        \"type\": \"STOCK\"\n" +
        "      },\n" +
        "      \"type\": \"STOCK\"\n" +
        "    }\n" +
        "  ]\n" +
        "}";

    private Portfolio toTest;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        Http.setInstance(httpResponder.getMock());
    }

    @Before
    public void setUp() throws Exception {
        toTest = gson.fromJson(JSON, Portfolio.class);
    }

    @Test
    public void getCompanyPortfolio() throws Exception {
        Company company = Company.getBySecurityIdentifier("STK0F513");
        Portfolio reference = gson.fromJson(httpResponder.getJsonForRequest(
            "/api/portfolios/57875cf3-de0a-48e4-a3bc-314d4550df12"), Portfolio.class);
        Portfolio testObject = Portfolio.getCompanyPortfolio(company);
        assertNotNull(testObject);
        assertEquals(reference, testObject);
    }

    @Test
    public void getFixedIncomePortfolio() throws Exception {
        Company company = Company.getBySecurityIdentifier("STK0F513");
        Portfolio reference = gson.fromJson(httpResponder.getJsonForRequest(
            "/api/portfolios/fixedincome/57875cf3-de0a-48e4-a3bc-314d4550df12"), Portfolio.class);
        Portfolio testObject = Portfolio.getFixedIncomePortfolio(company);
        assertNotNull(testObject);
        assertEquals(reference, testObject);
    }

    @Test
    public void getCash() throws Exception {
        assertEquals(400, toTest.getCash(), 0.0001);
    }

    @Test
    public void getCommittedCash() throws Exception {
        assertEquals(0, toTest.getCommittedCash(), 0.0001);
    }

    @Test
    public void getPositions() throws Exception {
        List<Position> reference = gson.fromJson("[\n" +
        "    {\n" +
        "      \"currentAskPrice\": 3500,\n" +
        "      \"currentAskSize\": 850,\n" +
        "      \"currentBidPrice\": null,\n" +
        "      \"currentBidSize\": null,\n" +
        "      \"lastPrice\": {\n" +
        "        \"date\": 1475882777051,\n" +
        "        \"value\": 10\n" +
        "      },\n" +
        "      \"numberOfShares\": 100,\n" +
        "      \"volume\": 1000,\n" +
        "      \"committedShares\": 0,\n" +
        "      \"securityIdentifier\": \"ST57FA01\",\n" +
        "      \"listing\": {\n" +
        "        \"startDate\": 1469951384818,\n" +
        "        \"endDate\": null,\n" +
        "        \"securityIdentifier\": \"ST57FA01\",\n" +
        "        \"name\": \"Argo\",\n" +
        "        \"type\": \"STOCK\"\n" +
        "      },\n" +
        "      \"type\": \"STOCK\"\n" +
        "    }\n" +
        "  ]", new TypeToken<ArrayList<Position>>() { }.getType());
        assertEquals(new HashSet<>(reference), new HashSet<>(toTest.getPositions()));
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

        Portfolio other1 = gson.fromJson("{\n" +
        "  \"committedCash\": 10,\n" +
        "  \"cash\": 400,\n" +
        "  \"positions\": [\n" +
        "    {\n" +
        "      \"currentAskPrice\": 3500,\n" +
        "      \"currentAskSize\": 850,\n" +
        "      \"currentBidPrice\": null,\n" +
        "      \"currentBidSize\": null,\n" +
        "      \"lastPrice\": {\n" +
        "        \"date\": 1475882777051,\n" +
        "        \"value\": 10\n" +
        "      },\n" +
        "      \"numberOfShares\": 100,\n" +
        "      \"volume\": 1000,\n" +
        "      \"committedShares\": 0,\n" +
        "      \"securityIdentifier\": \"ST57FA01\",\n" +
        "      \"listing\": {\n" +
        "        \"startDate\": 1469951384818,\n" +
        "        \"endDate\": null,\n" +
        "        \"securityIdentifier\": \"ST57FA01\",\n" +
        "        \"name\": \"Argo\",\n" +
        "        \"type\": \"STOCK\"\n" +
        "      },\n" +
        "      \"type\": \"STOCK\"\n" +
        "    }\n" +
        "  ]\n" +
        "}", Portfolio.class);

        Portfolio other2 = gson.fromJson("{\n" +
            "  \"committedCash\": 0,\n" +
            "  \"cash\": 300,\n" +
            "  \"positions\": [\n" +
            "    {\n" +
            "      \"currentAskPrice\": 3500,\n" +
            "      \"currentAskSize\": 850,\n" +
            "      \"currentBidPrice\": null,\n" +
            "      \"currentBidSize\": null,\n" +
            "      \"lastPrice\": {\n" +
            "        \"date\": 1475882777051,\n" +
            "        \"value\": 10\n" +
            "      },\n" +
            "      \"numberOfShares\": 100,\n" +
            "      \"volume\": 1000,\n" +
            "      \"committedShares\": 0,\n" +
            "      \"securityIdentifier\": \"ST57FA01\",\n" +
            "      \"listing\": {\n" +
            "        \"startDate\": 1469951384818,\n" +
            "        \"endDate\": null,\n" +
            "        \"securityIdentifier\": \"ST57FA01\",\n" +
            "        \"name\": \"Argo\",\n" +
            "        \"type\": \"STOCK\"\n" +
            "      },\n" +
            "      \"type\": \"STOCK\"\n" +
            "    }\n" +
            "  ]\n" +
            "}", Portfolio.class);

        assertFalse(toTest.equals(other1));
        assertFalse(toTest.equals(other2));
    }

    @Test
    public void testHashCode() throws Exception {
        Portfolio reference = gson.fromJson(JSON, Portfolio.class);
        assertEquals(reference.hashCode(), toTest.hashCode());
    }
}