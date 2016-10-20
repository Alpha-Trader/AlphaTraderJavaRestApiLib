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
 * Test case for the {@link SalaryPayment} class.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0.0
 */
public class SalaryPaymentTest {
    private static final Gson gson = new GsonBuilder().registerTypeAdapter(ZonedDateTime.class,
        new ZonedDateTimeDeserializer()).create();

    private static final String JSON = "{\n" +
        "  \"companyId\": \"companyId\",\n" +
        "  \"date\": 0,\n" +
        "  \"id\": \"id\",\n" +
        "  \"nextPossiblePaymentDate\": 1,\n" +
        "  \"salaryAmount\": 0\n" +
        "}";

    private SalaryPayment toTest;

    @Before
    public void setUp() throws Exception {
        toTest = gson.fromJson(JSON, SalaryPayment.class);
    }

    @Test
    public void getById() throws Exception {

    }

    @Test
    public void getCompanyId() throws Exception {
        assertEquals("companyId", toTest.getCompanyId());
    }

    @Test
    public void getNextPossiblePaymentDate() throws Exception {
        ZonedDateTime reference = ZonedDateTime.ofInstant(Instant.ofEpochMilli(1L),
            ZoneId.systemDefault());
        assertEquals(reference, toTest.getNextPossiblePaymentDate());
    }

    @Test
    public void getSalaryAmount() throws Exception {
        assertEquals(0.0, toTest.getSalaryAmount(), 0.0001);
    }

    @Test
    public void getDate() throws Exception {
        ZonedDateTime reference = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L),
            ZoneId.systemDefault());
        assertEquals(reference, toTest.getDate());
    }

    @Test
    public void getId() throws Exception {
        assertEquals("id", toTest.getId());
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

        SalaryPayment other = gson.fromJson("{\n" +
            "  \"id\": \"12345\"\n" +
            "}", SalaryPayment.class);

        assertFalse(toTest.equals(other));
    }

    @Test
    public void testHashCode() throws Exception {
        SalaryPayment reference = gson.fromJson(JSON, SalaryPayment.class);
        assertEquals(reference.hashCode(), toTest.hashCode());
    }
}