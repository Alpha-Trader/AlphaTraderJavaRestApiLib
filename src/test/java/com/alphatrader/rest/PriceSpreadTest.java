package com.alphatrader.rest;

import com.alphatrader.rest.util.ZonedDateTimeDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0.0
 */
public class PriceSpreadTest {
    private static HttpResponder httpResponder = HttpResponder.getInstance();
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

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        Http.setInstance(httpResponder.getMock());
    }

    @Before
    public void setUp() throws Exception {
        toTest = gson.fromJson(JSON, PriceSpread.class);
    }

    @Test
    public void getAllPriceSpreads() throws Exception {
        List<PriceSpread> reference = gson.fromJson(httpResponder.getJsonForRequest(
            "/api/pricespreads/"), new TypeToken<ArrayList<PriceSpread>>() { }.getType());
        List<PriceSpread> testObject = PriceSpread.getAllPriceSpreads();
        assertNotEquals(0, testObject.size());
        assertEquals(new HashSet<>(reference), new HashSet<>(testObject));
    }

    @Test
    public void getPriceSpread() throws Exception {
        PriceSpread reference = gson.fromJson(httpResponder.getJsonForRequest(
            "/api/pricespreads/STK0F513"), PriceSpread.class);
        PriceSpread testObject = PriceSpread.getPriceSpread(Listing.getById("STK0F513"));
        assertNotNull(testObject);
        assertEquals(reference, testObject);
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

        PriceSpread other1 = gson.fromJson("{\n" +
            "  \"listing\": {\n" +
            "    \"startDate\": 1469951698361,\n" +
            "    \"endDate\": null,\n" +
            "    \"securityIdentifier\": \"STK0F513\",\n" +
            "    \"name\": \"Katholische Kirche AG\",\n" +
            "    \"type\": \"STOCK\"\n" +
            "  },\n" +
            "  \"askSize\": 1,\n" +
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
            "}", PriceSpread.class);
        assertFalse(toTest.equals(other1));
        PriceSpread other2 = gson.fromJson("{\n" +
            "  \"listing\": {\n" +
            "    \"startDate\": 1,\n" +
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
            "  \"date\": 1\n" +
            "}", PriceSpread.class);
        assertFalse(toTest.equals(other2));
        PriceSpread other3 = gson.fromJson("{\n" +
            "  \"listing\": {\n" +
            "    \"startDate\": 1469951698361,\n" +
            "    \"endDate\": null,\n" +
            "    \"securityIdentifier\": \"STK0F513\",\n" +
            "    \"name\": \"Katholische Kirche AG\",\n" +
            "    \"type\": \"STOCK\"\n" +
            "  },\n" +
            "  \"askSize\": null,\n" +
            "  \"bidPrice\": 25,\n" +
            "  \"askPrice\": 1,\n" +
            "  \"spreadAbs\": null,\n" +
            "  \"spreadPercent\": null,\n" +
            "  \"lastPrice\": {\n" +
            "    \"date\": 1476375715646,\n" +
            "    \"value\": 656.93\n" +
            "  },\n" +
            "  \"bidSize\": 49701,\n" +
            "  \"date\": 1476375717202\n" +
            "}", PriceSpread.class);
        assertFalse(toTest.equals(other3));
        PriceSpread other4 = gson.fromJson("{\n" +
            "  \"listing\": {\n" +
            "    \"startDate\": 1469951698361,\n" +
            "    \"endDate\": null,\n" +
            "    \"securityIdentifier\": \"STK0F513\",\n" +
            "    \"name\": \"Katholische Kirche AG\",\n" +
            "    \"type\": \"STOCK\"\n" +
            "  },\n" +
            "  \"askSize\": null,\n" +
            "  \"bidPrice\": 1,\n" +
            "  \"askPrice\": null,\n" +
            "  \"spreadAbs\": null,\n" +
            "  \"spreadPercent\": null,\n" +
            "  \"lastPrice\": {\n" +
            "    \"date\": 1476375715646,\n" +
            "    \"value\": 656.93\n" +
            "  },\n" +
            "  \"bidSize\": 49701,\n" +
            "  \"date\": 1476375717202\n" +
            "}", PriceSpread.class);
        assertFalse(toTest.equals(other4));
        PriceSpread other5 = gson.fromJson("{\n" +
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
            "  \"bidSize\": 1,\n" +
            "  \"date\": 1476375717202\n" +
            "}", PriceSpread.class);
        assertFalse(toTest.equals(other5));
        PriceSpread other6 = gson.fromJson("{\n" +
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
            "  \"spreadAbs\": 1,\n" +
            "  \"spreadPercent\": null,\n" +
            "  \"lastPrice\": {\n" +
            "    \"date\": 1476375715646,\n" +
            "    \"value\": 656.93\n" +
            "  },\n" +
            "  \"bidSize\": 49701,\n" +
            "  \"date\": 1476375717202\n" +
            "}", PriceSpread.class);
        assertFalse(toTest.equals(other6));
        PriceSpread other8 = gson.fromJson("{\n" +
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
            "  \"spreadPercent\": 1,\n" +
            "  \"lastPrice\": {\n" +
            "    \"date\": 1476375715646,\n" +
            "    \"value\": 656.93\n" +
            "  },\n" +
            "  \"bidSize\": 49701,\n" +
            "  \"date\": 1476375717202\n" +
            "}", PriceSpread.class);
        assertFalse(toTest.equals(other8));
        PriceSpread other9 = gson.fromJson("{\n" +
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
            "    \"value\": 1\n" +
            "  },\n" +
            "  \"bidSize\": 49701,\n" +
            "  \"date\": 1476375717202\n" +
            "}", PriceSpread.class);
        assertFalse(toTest.equals(other9));
    }

    @Test
    public void testHashCode() throws Exception {
        PriceSpread reference = gson.fromJson(JSON, PriceSpread.class);
        assertEquals(reference.hashCode(), toTest.hashCode());
    }
}