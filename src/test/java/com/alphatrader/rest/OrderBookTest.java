package com.alphatrader.rest;

import com.alphatrader.rest.util.PropertyGson;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Test case for the {@link OrderBook} class.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0.0
 */
public class OrderBookTest {
    private static HttpResponder httpResponder = HttpResponder.getInstance();
    private static final Gson gson = new PropertyGson().create();

    private static final String JSON = "{\n" +
        "  \"buyEntries\": [\n" +
        "    {\n" +
        "      \"action\": \"BUY\",\n" +
        "      \"priceLimit\": 25,\n" +
        "      \"size\": 49701\n" +
        "    },\n" +
        "    {\n" +
        "      \"action\": \"BUY\",\n" +
        "      \"priceLimit\": 20,\n" +
        "      \"size\": 48768\n" +
        "    },\n" +
        "    {\n" +
        "      \"action\": \"BUY\",\n" +
        "      \"priceLimit\": 0.32,\n" +
        "      \"size\": 292\n" +
        "    }\n" +
        "  ],\n" +
        "  \"sellEntries\": [],\n" +
        "  \"maxBuySize\": 49701,\n" +
        "  \"maxSellSize\": 0\n" +
        "}";

    private OrderBook toTest;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        Http.setInstance(httpResponder.getMock());
    }

    @Before
    public void setUp() throws Exception {
        toTest = gson.fromJson(JSON, OrderBook.class);
    }

    @Test
    public void getOrderBook() throws Exception {
        OrderBook reference = gson.fromJson(httpResponder.getJsonForRequest("/api/orderbook/STK0F513"),
            OrderBook.class);
        OrderBook testObject = OrderBook.getOrderBook("STK0F513");
        assertNotNull(testObject);
        assertEquals(reference, testObject);
    }

    @Test
    public void getMaxBuySize() throws Exception {
        assertEquals(49701, toTest.getMaxBuySize(), 0.0001);
    }

    @Test
    public void getMaxSellSize() throws Exception {
        assertEquals(0, toTest.getMaxSellSize(), 0.0001);
    }

    @Test
    public void getBuyEntries() throws Exception {
        List<Order> reference = gson.fromJson("[\n" +
        "  {\n" +
        "    \"action\": \"BUY\",\n" +
        "    \"priceLimit\": 25,\n" +
        "    \"size\": 49701\n" +
        "  },\n" +
        "  {\n" +
        "    \"action\": \"BUY\",\n" +
        "    \"priceLimit\": 20,\n" +
        "    \"size\": 48768\n" +
        "  },\n" +
        "  {\n" +
        "    \"action\": \"BUY\",\n" +
        "    \"priceLimit\": 0.32,\n" +
        "    \"size\": 292\n" +
        "  }\n" +
        "]", new TypeToken<ArrayList<Order>>() { }.getType());
        assertEquals(new HashSet<>(reference), new HashSet<>(toTest.getBuyEntries()));
    }

    @Test
    public void getSellEntries() throws Exception {
        List<Order> reference = gson.fromJson("[]", new TypeToken<ArrayList<Order>>() { }.getType());
        assertEquals(new HashSet<>(reference), new HashSet<>(toTest.getSellEntries()));
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

        OrderBook other = gson.fromJson("{\n" +
            "  \"id\": \"12345\"\n" +
            "}", OrderBook.class);

        assertFalse(toTest.equals(other));
    }

    @Test
    public void testHashCode() throws Exception {
        OrderBook reference = gson.fromJson(JSON, OrderBook.class);
        assertEquals(reference.hashCode(), toTest.hashCode());
    }
}