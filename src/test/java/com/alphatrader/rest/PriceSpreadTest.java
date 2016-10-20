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
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0.0
 */
public class PriceSpreadTest {
    private static final Gson gson = new GsonBuilder().registerTypeAdapter(ZonedDateTime.class,
        new ZonedDateTimeDeserializer()).create();

    private static final String JSON = "{\n" +
        "  \"listing\": {\n" +
        "    \"startDate\": 1469951698361,\n" +
        "    \"endDate\": null,\n" +
        "    \"securityIdentifier\": \"STK0F513\",\n" +
        "    \"name\": \"Katholische Kirche AG\",\n" +
        "    \"type\": \"STOCK\"\n" +
        "  },\n" +
        "  \"askSize\": null,\n" +
        "  \"bidPrice\": 25,\n" +
        "  \"askPrice\": null,\n" +
        "  \"spreadAbs\": null,\n" +
        "  \"spreadPercent\": null,\n" +
        "  \"lastPrice\": {\n" +
        "    \"date\": 1476375715646,\n" +
        "    \"value\": 656.93\n" +
        "  },\n" +
        "  \"bidSize\": 49701,\n" +
        "  \"date\": 1476375717202\n" +
        "}";

    private PriceSpread toTest;

    @Before
    public void setUp() throws Exception {
        toTest = gson.fromJson(JSON, PriceSpread.class);
    }

    @Test
    public void getAllPriceSpreads() throws Exception {

    }

    @Test
    public void getPriceSpread() throws Exception {

    }

    @Test
    public void getPriceSpread1() throws Exception {

    }

    @Test
    public void getAskSize() throws Exception {
        assertEquals(null, toTest.getAskSize());
    }

    @Test
    public void getAskPrice() throws Exception {
        assertEquals(null, toTest.getAskPrice());
    }

    @Test
    public void getBidSize() throws Exception {
        assertEquals(49701, toTest.getBidSize(), 0.0001);
    }

    @Test
    public void getBidPrice() throws Exception {
        assertEquals(25, toTest.getBidPrice(), 0.0001);
    }

    @Test
    public void getSpreadAbs() throws Exception {
        assertEquals(null, toTest.getSpreadAbs());
    }

    @Test
    public void getSpreadPercent() throws Exception {
        assertEquals(null, toTest.getSpreadPercent());
    }

    @Test
    public void getLastPrice() throws Exception {
        LastPrice reference = gson.fromJson("{\n" +
        "  \"date\": 1476375715646,\n" +
        "  \"value\": 656.93\n" +
        "}", LastPrice.class);
        assertEquals(reference, toTest.getLastPrice());
    }

    @Test
    public void getMaxBidPrice() throws Exception {
        assertEquals(null, toTest.getMaxBidPrice());
    }

    @Test
    public void getMinAskPrice() throws Exception {
        assertEquals(null, toTest.getMinAskPrice());
    }

    @Test
    public void getDate() throws Exception {
        ZonedDateTime reference = ZonedDateTime.ofInstant(Instant.ofEpochMilli(1476375717202L),
            ZoneId.systemDefault());
        assertEquals(reference, toTest.getDate());
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
    }

    @Test
    public void testHashCode() throws Exception {
        PriceSpread reference = gson.fromJson(JSON, PriceSpread.class);
        assertEquals(reference.hashCode(), toTest.hashCode());
    }
}