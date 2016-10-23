package com.alphatrader.rest;

import com.alphatrader.rest.util.ZonedDateTimeDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Before;
import org.junit.Test;

import java.time.ZonedDateTime;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

/**
 * Test case for the {@link Position} class.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0
 */
public class PositionTest {
    private static final Gson gson = new GsonBuilder().registerTypeAdapter(ZonedDateTime.class,
        new ZonedDateTimeDeserializer()).create();

    private static final String JSON = "{\n" +
        "      \"currentAskPrice\": 3500,\n" +
        "      \"currentAskSize\": 850,\n" +
        "      \"currentBidPrice\": null,\n" +
        "      \"currentBidSize\": null,\n" +
        "      \"lastPrice\": {\n" +
        "        \"date\": 1475882777051,\n" +
        "        \"value\": 2.88\n" +
        "      },\n" +
        "      \"numberOfShares\": 1712,\n" +
        "      \"volume\": 4930.56,\n" +
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
        "    }";

    private Position toTest;

    @Before
    public void setUp() throws Exception {
        toTest = gson.fromJson(JSON, Position.class);
    }

    @Test
    public void testGetSecurityIdentifier() throws Exception {
        assertEquals("ST57FA01", toTest.getSecurityIdentifier());
    }

    @Test
    public void testGetLastPrice() throws Exception {
        LastPrice reference = gson.fromJson("{\n" +
        "        \"date\": 1475882777051,\n" +
        "        \"value\": 2.88\n" +
        "      }", LastPrice.class);

        assertEquals(reference, toTest.getLastPrice());
    }

    @Test
    public void testGetNumberOfShares() throws Exception {
        assertEquals(Long.valueOf(1712), toTest.getNumberOfShares());
    }

    @Test
    public void testGetVolume() throws Exception {
        assertEquals(4930.56, toTest.getVolume(), 0.0001);
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

        Position other1 = gson.fromJson("{\n" +
            "      \"currentAskPrice\": 3500,\n" +
            "      \"currentAskSize\": 850,\n" +
            "      \"currentBidPrice\": null,\n" +
            "      \"currentBidSize\": null,\n" +
            "      \"lastPrice\": {\n" +
            "        \"date\": 1475882777051,\n" +
            "        \"value\": 2.0\n" +
            "      },\n" +
            "      \"numberOfShares\": 1712,\n" +
            "      \"volume\": 4930.56,\n" +
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
            "    }", Position.class);
        Position other2 = gson.fromJson("{\n" +
            "      \"currentAskPrice\": 3500,\n" +
            "      \"currentAskSize\": 850,\n" +
            "      \"currentBidPrice\": null,\n" +
            "      \"currentBidSize\": null,\n" +
            "      \"lastPrice\": {\n" +
            "        \"date\": 1475882777051,\n" +
            "        \"value\": 2.88\n" +
            "      },\n" +
            "      \"numberOfShares\": 1,\n" +
            "      \"volume\": 4930.56,\n" +
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
            "    }", Position.class);
        Position other3 = gson.fromJson("{\n" +
            "      \"currentAskPrice\": 3500,\n" +
            "      \"currentAskSize\": 850,\n" +
            "      \"currentBidPrice\": null,\n" +
            "      \"currentBidSize\": null,\n" +
            "      \"lastPrice\": {\n" +
            "        \"date\": 1475882777051,\n" +
            "        \"value\": 2.88\n" +
            "      },\n" +
            "      \"numberOfShares\": 1712,\n" +
            "      \"volume\": 4930.56,\n" +
            "      \"committedShares\": 0,\n" +
            "      \"securityIdentifier\": \"STSTSTST\",\n" +
            "      \"listing\": {\n" +
            "        \"startDate\": 1469951384818,\n" +
            "        \"endDate\": null,\n" +
            "        \"securityIdentifier\": \"ST57FA01\",\n" +
            "        \"name\": \"Argo\",\n" +
            "        \"type\": \"STOCK\"\n" +
            "      },\n" +
            "      \"type\": \"STOCK\"\n" +
            "    }", Position.class);

        assertFalse(toTest.equals(other1));
        assertFalse(toTest.equals(other2));
        assertFalse(toTest.equals(other3));
    }

    @Test
    public void testHashCode() throws Exception {
        Position reference = gson.fromJson(JSON, Position.class);
        assertEquals(reference.hashCode(), toTest.hashCode());
    }
}