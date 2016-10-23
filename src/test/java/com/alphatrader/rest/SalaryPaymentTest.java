package com.alphatrader.rest;

import com.alphatrader.rest.util.PropertyGson;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.BeforeClass;
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
    private static HttpResponder httpResponder = HttpResponder.getInstance();
    private static final Gson gson = new PropertyGson().create();

    private static final String JSON = "{\n" +
        "  \"companyId\": \"companyId\",\n" +
        "  \"date\": 0,\n" +
        "  \"id\": \"id\",\n" +
        "  \"nextPossiblePaymentDate\": 1,\n" +
        "  \"salaryAmount\": 0\n" +
        "}";

    private SalaryPayment toTest;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        Http.setInstance(httpResponder.getMock());
    }

    @Before
    public void setUp() throws Exception {
        toTest = gson.fromJson(JSON, SalaryPayment.class);
    }

    @Test
    public void getById() throws Exception {
        SalaryPayment reference = gson.fromJson(httpResponder.getJsonForRequest(
            "/api/salarypayments/bf8d952f-65ac-474c-9a8d-ae7f2ef82cfa"), SalaryPayment.class);
        SalaryPayment testObject = SalaryPayment.getById("bf8d952f-65ac-474c-9a8d-ae7f2ef82cfa");
        assertNotNull(testObject);
        assertEquals(reference, testObject);
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