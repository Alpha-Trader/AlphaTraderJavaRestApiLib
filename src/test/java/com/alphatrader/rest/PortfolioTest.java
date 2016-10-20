package com.alphatrader.rest;

import com.alphatrader.rest.util.ZonedDateTimeDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.junit.Before;
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

    @Before
    public void setUp() throws Exception {
        toTest = gson.fromJson(JSON, Portfolio.class);
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

        Portfolio other = gson.fromJson("{\n" +
            "  \"id\": \"12345\"\n" +
            "}", Portfolio.class);

        assertFalse(toTest.equals(other));
    }

    @Test
    public void testHashCode() throws Exception {
        Portfolio reference = gson.fromJson(JSON, Portfolio.class);
        assertEquals(reference.hashCode(), toTest.hashCode());
    }
}