package com.alphatrader.rest;

import com.alphatrader.rest.util.PropertyGson;
import com.google.gson.Gson;
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
 * Test case for the {@link CashTransferLog} class.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0.0
 */
public class CashTransferLogTest {
    private static HttpResponder httpResponder = HttpResponder.getInstance();
    private static final Gson gson = new PropertyGson().create();

    private static final String JSON = "{\n" +
        "  \"amount\": 0.02,\n" +
        "  \"receiverBankAccount\": \"2ba8236d-b334-4315-a7ad-217b38fa50f5\",\n" +
        "  \"senderBankAccount\": \"c4d789b2-5e74-4618-a62e-fedc37fc2e4f\",\n" +
        "  \"date\": 1476896811034,\n" +
        "  \"message\": {\n" +
        "    \"message\": \"Buy # shares of # (#)\",\n" +
        "    \"substitutions\": [\n" +
        "      \"1\",\n" +
        "      \"superJbobo Inc.\",\n" +
        "      \"STSD64F5\"\n" +
        "    ],\n" +
        "    \"filledString\": \"Buy 1 shares of superJbobo Inc. (STSD64F5)\"\n" +
        "  },\n" +
        "  \"id\": \"ffe68cdf-aec7-4c30-b9a2-33c207040faf\"\n" +
        "}";

    private CashTransferLog toTest;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        Http.setInstance(httpResponder.getMock());
    }

    @Before
    public void setUp() throws Exception {
        toTest = gson.fromJson(JSON, CashTransferLog.class);
    }

    @Test
    public void testGetCashTransferLogs() throws Exception {
        ZonedDateTime date = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault());

        List<CashTransferLog> reference = gson.fromJson(httpResponder
            .getJsonForRequest("/api/cashtransferlogs/?startDate=0"),
            new TypeToken<ArrayList<CashTransferLog>>() { }.getType());
        List<CashTransferLog> testObject = CashTransferLog.getCashTransferLogs(date, null, null, null);

        assertNotNull(testObject);
        assertEquals(new HashSet<>(reference), new HashSet<>(testObject));
    }

    @Test
    public void testGetCashTransferLogs1() throws Exception {
        ZonedDateTime date = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault());

        List<CashTransferLog> reference = gson.fromJson(httpResponder
                .getJsonForRequest("/api/cashtransferlogs/?startDate=0&endDate=0"),
            new TypeToken<ArrayList<CashTransferLog>>() { }.getType());
        List<CashTransferLog> testObject = CashTransferLog.getCashTransferLogs(date, date, null, null);

        assertNotNull(testObject);
        assertEquals(new HashSet<>(reference), new HashSet<>(testObject));
    }

    @Test
    public void testGetCashTransferLogs2() throws Exception {
        ZonedDateTime date = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault());

        List<CashTransferLog> reference = gson.fromJson(httpResponder.getJsonForRequest(
                    "/api/cashtransferlogs/?senderBankAccountId=e542e37b-cb17-4575-ad80-d03d61cb4ffc"),
            new TypeToken<ArrayList<CashTransferLog>>() { }.getType());
        List<CashTransferLog> testObject = CashTransferLog.getCashTransferLogs(null, null,
            "e542e37b-cb17-4575-ad80-d03d61cb4ffc", null);

        assertNotNull(testObject);
        assertEquals(new HashSet<>(reference), new HashSet<>(testObject));
    }

    @Test
    public void testGetCashTransferLogs3() throws Exception {
        ZonedDateTime date = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault());

        List<CashTransferLog> reference = gson.fromJson(httpResponder.getJsonForRequest(
                    "/api/cashtransferlogs/?receiverBankAccountId=3c58de5a-6c59-41bf-be48-bf387d56f3d0"),
            new TypeToken<ArrayList<CashTransferLog>>() { }.getType());
        List<CashTransferLog> testObject = CashTransferLog.getCashTransferLogs(null, null, null,
            "3c58de5a-6c59-41bf-be48-bf387d56f3d0");

        assertNotNull(testObject);
        assertEquals(new HashSet<>(reference), new HashSet<>(testObject));
    }

    @Test
    public void testGetAmount() throws Exception {
        assertEquals(0.02, toTest.getAmount(), 0.0001);
    }

    @Test
    public void testGetReceiverBankAccount() throws Exception {
        assertEquals("2ba8236d-b334-4315-a7ad-217b38fa50f5", toTest.getReceiverBankAccount());
    }

    @Test
    public void testGetSenderBankAccount() throws Exception {
        assertEquals("c4d789b2-5e74-4618-a62e-fedc37fc2e4f", toTest.getSenderBankAccount());
    }

    @Test
    public void testGetDate() throws Exception {
        ZonedDateTime reference = ZonedDateTime.ofInstant(Instant.ofEpochMilli(1476896811034L),
            ZoneId.systemDefault());
        assertEquals(reference, toTest.getDate());
    }

    @Test
    public void testGetMessage() throws Exception {
        Message reference = gson.fromJson("{\n" +
        "  \"message\": \"Buy # shares of # (#)\",\n" +
        "  \"substitutions\": [\n" +
        "    \"1\",\n" +
        "    \"superJbobo Inc.\",\n" +
        "    \"STSD64F5\"\n" +
        "  ],\n" +
        "  \"filledString\": \"Buy 1 shares of superJbobo Inc. (STSD64F5)\"\n" +
        "}", Message.class);

        assertEquals(reference, toTest.getMessage());
    }

    @Test
    public void testGetId() throws Exception {
        assertEquals("ffe68cdf-aec7-4c30-b9a2-33c207040faf", toTest.getId());
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

        CashTransferLog other = gson.fromJson("{\n" +
            "  \"id\": \"12345\"\n" +
            "}", CashTransferLog.class);

        assertFalse(toTest.equals(other));
    }

    @Test
    public void testHashCode() throws Exception {
        CashTransferLog reference = gson.fromJson(JSON, CashTransferLog.class);
        assertEquals(reference.hashCode(), toTest.hashCode());
    }
}