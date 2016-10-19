package com.alphatrader.rest;

import com.alphatrader.rest.util.ZonedDateTimeDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.junit.Before;
import org.junit.Test;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Test case {@link Event} class.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0.0
 */
public class EventTest {
    private static final Gson gson = new GsonBuilder().registerTypeAdapter(ZonedDateTime.class,
        new ZonedDateTimeDeserializer()).create();

    private static final String JSON = "{\n" +
        "  \"content\": {\n" +
        "    \"counterPartyName\": null,\n" +
        "    \"creationDate\": 1476876989631,\n" +
        "    \"committedCash\": 9985.88,\n" +
        "    \"numberOfShares\": 499294,\n" +
        "    \"action\": \"BUY\",\n" +
        "    \"securityIdentifier\": \"STSD64F5\",\n" +
        "    \"listing\": {\n" +
        "      \"startDate\": 1474099135309,\n" +
        "      \"endDate\": null,\n" +
        "      \"securityIdentifier\": \"STSD64F5\",\n" +
        "      \"name\": \"superJbobo Inc.\",\n" +
        "      \"type\": \"STOCK\"\n" +
        "    },\n" +
        "    \"counterParty\": null,\n" +
        "    \"price\": 0.02,\n" +
        "    \"ownerName\": \"Katholische Kirche AG\",\n" +
        "    \"owner\": \"57875cf3-de0a-48e4-a3bc-314d4550df12\",\n" +
        "    \"id\": \"868e59cf-1170-4fee-a40a-8647d56e3b02\",\n" +
        "    \"type\": \"LIMIT\"\n" +
        "  },\n" +
        "  \"date\": 1476896236112,\n" +
        "  \"realms\": [\n" +
        "    \"868e59cf-1170-4fee-a40a-8647d56e3b02\",\n" +
        "    \"BUY\",\n" +
        "    \"LIMIT\",\n" +
        "    \"STSD64F5\",\n" +
        "    \"57875cf3-de0a-48e4-a3bc-314d4550df12\"\n" +
        "  ],\n" +
        "  \"type\": \"ORDER_DELETED\"\n" +
        "}";

    private Event toTest;

    @Before
    public void setUp() throws Exception {
        toTest = gson.fromJson(JSON, Event.class);
    }

    @Test
    public void getAllEvents() throws Exception {

    }

    @Test
    public void getAllEvents1() throws Exception {

    }

    @Test
    public void getAllUserEvents() throws Exception {

    }

    @Test
    public void getAllUserEvents1() throws Exception {

    }

    @Test
    public void getEventsByType() throws Exception {

    }

    @Test
    public void getEventsByType1() throws Exception {

    }

    @Test
    public void searchEvents() throws Exception {

    }

    @Test
    public void searchEvents1() throws Exception {

    }

    @Test
    public void getContent() throws Exception {
        assertNotNull(toTest.getContent());
    }

    @Test
    public void getType() throws Exception {
        assertEquals(Event.Type.ORDER_DELETED, toTest.getType());
    }

    @Test
    public void getDate() throws Exception {
        ZonedDateTime reference = ZonedDateTime.ofInstant(Instant.ofEpochMilli(1476896236112L),
            ZoneId.systemDefault());
        assertEquals(reference, toTest.getDate());
    }

    @Test
    public void getRealms() throws Exception {
        List<String> reference = gson.fromJson("[\n" +
        "    \"868e59cf-1170-4fee-a40a-8647d56e3b02\",\n" +
        "    \"BUY\",\n" +
        "    \"LIMIT\",\n" +
        "    \"STSD64F5\",\n" +
        "    \"57875cf3-de0a-48e4-a3bc-314d4550df12\"\n" +
        "  ]", new TypeToken<ArrayList<String>>() {}.getType());
        assertEquals(new HashSet<>(reference),
            new HashSet<>(toTest.getRealms()));
    }

    @Test
    public void testToString() throws Exception {
        assertTrue(toTest
            .toString()
            .startsWith(
                toTest
                    .getClass()
                    .getSimpleName()
            )
        );
    }

    @Test
    public void testEquals() throws Exception {
        assertTrue(toTest.equals(toTest));
        assertFalse(toTest.equals(null));
        assertFalse(toTest.equals("Test"));

        Event other = gson.fromJson("{\n" +
            "  \"id\": \"12345\"\n" +
            "}", Event.class);

        assertFalse(toTest.equals(other));
    }

    @Test
    public void testHashCode() throws Exception {
        Event reference = gson.fromJson(JSON, Event.class);
        assertEquals(reference.hashCode(), toTest.hashCode());
    }
}