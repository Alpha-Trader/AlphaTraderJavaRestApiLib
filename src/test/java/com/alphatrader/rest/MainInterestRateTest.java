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
public class MainInterestRateTest {
    private static final Gson gson = new GsonBuilder().registerTypeAdapter(ZonedDateTime.class,
        new ZonedDateTimeDeserializer()).create();

    private static final String JSON = "{\n" +
        "  \"date\": 1465585935322,\n" +
        "  \"value\": 2,\n" +
        "  \"id\": \"9682dab9-b0de-4d59-b1ac-42b7f85bede2\"\n" +
        "}";

    private MainInterestRate toTest;

    @Before
    public void setUp() throws Exception {
        toTest = gson.fromJson(JSON, MainInterestRate.class);
    }

    @Test
    public void getId() throws Exception {
        assertEquals("9682dab9-b0de-4d59-b1ac-42b7f85bede2", toTest.getId());
    }

    @Test
    public void getValue() throws Exception {
        assertEquals(2, toTest.getValue(), 0.0001);
    }

    @Test
    public void getDate() throws Exception {
        ZonedDateTime reference = ZonedDateTime.ofInstant(Instant.ofEpochMilli(1465585935322L),
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

        MainInterestRate other = gson.fromJson("{\n" +
            "  \"id\": \"12345\"\n" +
            "}", MainInterestRate.class);

        assertFalse(toTest.equals(other));
    }

    @Test
    public void testHashCode() throws Exception {
        MainInterestRate reference = gson.fromJson(JSON, MainInterestRate.class);
        assertEquals(reference.hashCode(), toTest.hashCode());
    }
}