package com.alphatrader.rest;

import com.alphatrader.rest.util.LocalDateTimeDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Test case for the {@link Position} class.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0
 */
public class PositionTest {
    private static final Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class,
        new LocalDateTimeDeserializer()).create();

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
        assertEquals(2.88, toTest.getLastPrice(), 0.0001);
    }

    @Test
    public void testGetNumberOfShares() throws Exception {
        assertEquals(1712, toTest.getNumberOfShares());
    }

    @Test
    public void testGetVolume() throws Exception {
        assertEquals(4930.56, toTest.getVolume(), 0.0001);
    }
}