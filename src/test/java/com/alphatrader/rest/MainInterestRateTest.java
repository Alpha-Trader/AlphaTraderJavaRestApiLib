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
 * Test case for the {@link MainInterestRate} class.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0.0
 */
public class MainInterestRateTest {
    private static HttpResponder httpResponder = HttpResponder.getInstance();
    private static final Gson gson = new GsonBuilder().registerTypeAdapter(ZonedDateTime.class,
        new ZonedDateTimeDeserializer()).create();

    private static final String JSON = "{\n" +
        "  \"date\": 1465585935322,\n" +
        "  \"value\": 2,\n" +
        "  \"id\": \"9682dab9-b0de-4d59-b1ac-42b7f85bede2\"\n" +
        "}";

    private MainInterestRate toTest;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        Http.setInstance(httpResponder.getMock());
    }

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
    public void getCurrent() throws Exception {
        MainInterestRate reference = gson.fromJson(httpResponder.getJsonForRequest(
            "/api/maininterestrate/latest/"), MainInterestRate.class);
        MainInterestRate testObject = MainInterestRate.getCurrent();
        assertNotNull(testObject);
        assertEquals(reference, testObject);
    }

    @Test
    public void getAll() throws Exception {
        List<MainInterestRate> reference = gson.fromJson(httpResponder.getJsonForRequest(
            "/api/maininterestrate/"), new TypeToken<ArrayList<MainInterestRate>>() { }.getType());
        List<MainInterestRate> testObject = MainInterestRate.getAll();
        assertNotEquals(0, testObject.size());
        assertEquals(new HashSet<>(reference), new HashSet<>(testObject));
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