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
 * Test case for the {@link CashTransferLog} class.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0.0
 */
public class CashTransferLogTest {
    private static final Gson gson = new GsonBuilder().registerTypeAdapter(ZonedDateTime.class,
        new ZonedDateTimeDeserializer()).create();

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

    @Before
    public void setUp() throws Exception {
        toTest = gson.fromJson(JSON, CashTransferLog.class);
    }

    @Test
    public void testGetCashTransferLogs() throws Exception {

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