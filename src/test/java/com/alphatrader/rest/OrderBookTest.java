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
 * Test case for the {@link OrderBook} class.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0.0
 */
public class OrderBookTest {
    private static final Gson gson = new GsonBuilder().registerTypeAdapter(ZonedDateTime.class,
        new ZonedDateTimeDeserializer()).create();

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

    @Before
    public void setUp() throws Exception {
        toTest = gson.fromJson(JSON, OrderBook.class);
    }

    @Test
    public void getOrderBook() throws Exception {

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
}