package com.alphatrader.rest;

import com.alphatrader.rest.util.PropertyGson;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.junit.Assert.*;

/**
 * Test case for the {@link LastPrice} class.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0.0
 */
public class LastPriceTest {
    private static final Gson gson = new PropertyGson().create();

    private static final String JSON = "{\n" +
        "  \"date\": 1476375715646,\n" +
        "  \"value\": 656.93\n" +
        "}";

    private LastPrice toTest;

    @Before
    public void setUp() throws Exception {
        toTest = gson.fromJson(JSON, LastPrice.class);
    }

    @Test
    public void getDate() throws Exception {
        ZonedDateTime date = ZonedDateTime.ofInstant(Instant.ofEpochMilli(1476375715646L),
            ZoneId.systemDefault());
        assertEquals(date, toTest.getDate());
    }

    @Test
    public void getValue() throws Exception {
        assertEquals(656.93, toTest.getValue(), 0.0001);
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

        LastPrice other = gson.fromJson("{\n" +
            "  \"value\": \"12345\"\n" +
            "}", LastPrice.class);

        assertFalse(toTest.equals(other));
    }

    @Test
    public void testHashCode() throws Exception {
        LastPrice reference = gson.fromJson(JSON, LastPrice.class);
        assertEquals(reference.hashCode(), toTest.hashCode());
    }
}