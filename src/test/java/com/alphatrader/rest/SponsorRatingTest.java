package com.alphatrader.rest;

import com.alphatrader.rest.util.PropertyGson;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test case for the {@link SponsorRating} class.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0.0
 */
public class SponsorRatingTest {
    private static final Gson gson = new PropertyGson().create();

    private static final String JSON = "{\n" +
        "  \"salary\": 1000.00,\n" +
        "  \"value\": \"A\"\n" +
        "}";

    SponsorRating toTest;

    @Before
    public void setUp() throws Exception {
        toTest = gson.fromJson(JSON, SponsorRating.class);
    }

    @Test
    public void testGetSalary() throws Exception {
        assertEquals(1000.0, toTest.getSalary(), 0.0001);
    }

    @Test
    public void testGetValue() throws Exception {
        assertEquals(SponsorRating.SponsorRatingLetter.A, toTest.getValue());
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

        SponsorRating other = gson.fromJson("{\n" +
            "  \"salary\": 2000.00,\n" +
            "  \"value\": \"A\"\n" +
            "}", SponsorRating.class);
        assertFalse(toTest.equals(other));
    }

    @Test
    public void testHashCode() throws Exception {
        SponsorRating reference = gson.fromJson(JSON, SponsorRating.class);
        assertEquals(reference.hashCode(), toTest.hashCode());
    }
}