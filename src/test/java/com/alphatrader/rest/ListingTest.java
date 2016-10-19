package com.alphatrader.rest;

import com.alphatrader.rest.util.ZonedDateTimeDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Before;
import org.junit.Test;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0.0
 */
public class ListingTest {
    private static final Gson gson = new GsonBuilder().registerTypeAdapter(ZonedDateTime.class,
        new ZonedDateTimeDeserializer()).create();

    private static final String JSON = "{\n" +
        "  \"startDate\": 1469951698361,\n" +
        "  \"endDate\": null,\n" +
        "  \"securityIdentifier\": \"STK0F513\",\n" +
        "  \"name\": \"Katholische Kirche AG\",\n" +
        "  \"type\": \"STOCK\"\n" +
        "}";

    private Listing toTest;

    @Before
    public void setUp() throws Exception {
        toTest = gson.fromJson(JSON, Listing.class);
    }

    @Test
    public void getAllListings() throws Exception {

    }

    @Test
    public void getById() throws Exception {

    }

    @Test
    public void getOutstandingShares() throws Exception {

    }

    @Test
    public void getOutstandingShares1() throws Exception {

    }

    @Test
    public void searchBySecurityIdentifier() throws Exception {

    }

    @Test
    public void getShareholders() throws Exception {

    }

    @Test
    public void getShareholders1() throws Exception {

    }

    @Test
    public void getStartDate() throws Exception {
        ZonedDateTime reference = ZonedDateTime.ofInstant(Instant.ofEpochMilli(1469951698361L),
            ZoneId.systemDefault());
        assertEquals(reference, toTest.getStartDate());
    }

    @Test
    public void getEndDate() throws Exception {
        assertEquals(null, toTest.getEndDate());
    }

    @Test
    public void getSecurityIdentifier() throws Exception {
        assertEquals("STK0F513", toTest.getSecurityIdentifier());
    }

    @Test
    public void getName() throws Exception {
        assertEquals("Katholische Kirche AG", toTest.getName());
    }

    @Test
    public void getType() throws Exception {
        assertEquals(Listing.Type.STOCK, toTest.getType());
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

        Listing other = gson.fromJson("{\n" +
            "  \"id\": \"12345\"\n" +
            "}", Listing.class);

        assertFalse(toTest.equals(other));
    }

    @Test
    public void testHashCode() throws Exception {
        Listing reference = gson.fromJson(JSON, Listing.class);
        assertEquals(reference.hashCode(), toTest.hashCode());
    }
}