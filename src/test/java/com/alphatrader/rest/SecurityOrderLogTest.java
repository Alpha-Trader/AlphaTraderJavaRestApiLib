package com.alphatrader.rest;

import com.alphatrader.rest.util.ZonedDateTimeDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import javafx.util.Pair;
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
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0.0
 */
public class SecurityOrderLogTest {
    private static HttpResponder httpResponder = HttpResponder.getInstance();
    private static final Gson gson = new GsonBuilder().registerTypeAdapter(ZonedDateTime.class,
        new ZonedDateTimeDeserializer()).create();

    private static final String JSON = "{\n" +
        "  \"buyerSecuritiesAccount\": \"ca74b0ce-b77a-4125-93c8-bb7a2d2cb920\",\n" +
        "  \"sellerSecuritiesAccount\": \"de97775a-11be-47c5-af33-529b996d2b53\",\n" +
        "  \"numberOfShares\": 12,\n" +
        "  \"volume\": 25.68,\n" +
        "  \"securityIdentifier\": \"STSD5676\",\n" +
        "  \"price\": 2.14,\n" +
        "  \"date\": 1476953559806,\n" +
        "  \"id\": \"b7a4c7c7-c870-4bf1-badd-667d16fbd274\"\n" +
        "}";

    private SecurityOrderLog toTest;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        Http.setInstance(httpResponder.getMock());
    }

    @Before
    public void setUp() throws Exception {
        toTest = gson.fromJson(JSON, SecurityOrderLog.class);
    }

    @Test
    public void getAllLogs() throws Exception {
        List<SecurityOrderLog> reference = gson.fromJson(httpResponder.getJsonForRequest(
            "/api/securityorderlogs"),
            new TypeToken<ArrayList<SecurityOrderLog>>() { }.getType());
        List<SecurityOrderLog> testObject = SecurityOrderLog.getAllLogs();
        assertNotEquals(0, testObject.size());
        assertEquals(new HashSet<>(reference), new HashSet<>(testObject));
    }

    @Test
    public void searchLogs() throws Exception {
        ZonedDateTime date = ZonedDateTime.ofInstant(Instant.ofEpochMilli(1477030780257L),
            ZoneId.systemDefault());
        List<SecurityOrderLog> reference = gson.fromJson(httpResponder.getJsonForRequest(
            "/api/securityorderlogs?startDate=1477030780257"),
            new TypeToken<ArrayList<SecurityOrderLog>>() { }.getType());
        List<SecurityOrderLog> testObject = SecurityOrderLog.searchLogs(date, null, null);
        assertNotEquals(0, testObject.size());
        assertEquals(new HashSet<>(reference), new HashSet<>(testObject));
    }

    @Test
    public void searchLogs1() throws Exception {
        ZonedDateTime date = ZonedDateTime.ofInstant(Instant.ofEpochMilli(1477030780257L),
            ZoneId.systemDefault());
        List<SecurityOrderLog> reference = gson.fromJson(httpResponder.getJsonForRequest(
            "/api/securityorderlogs?startDate=1477030780257&endDate=1477030780257"),
            new TypeToken<ArrayList<SecurityOrderLog>>() { }.getType());
        List<SecurityOrderLog> testObject = SecurityOrderLog.searchLogs(date, date, null);
        assertNotEquals(0, testObject.size());
        assertEquals(new HashSet<>(reference), new HashSet<>(testObject));
    }

    @Test
    public void searchLogs2() throws Exception {
        ZonedDateTime date = ZonedDateTime.ofInstant(Instant.ofEpochMilli(1477030780257L),
            ZoneId.systemDefault());
        List<SecurityOrderLog> reference = gson.fromJson(httpResponder.getJsonForRequest(
            "/api/securityorderlogs?securityIdentifier=STBDD981&startDate=1477030780257" +
                "&endDate=1477030780257"),
            new TypeToken<ArrayList<SecurityOrderLog>>() { }.getType());
        List<SecurityOrderLog> testObject = SecurityOrderLog.searchLogs(date, date,
            new Pair<SecurityOrderLog.SearchType, String>(
                SecurityOrderLog.SearchType.SECURITY_IDENTIFIER, "STBDD981"));
        assertNotEquals(0, testObject.size());
        assertEquals(new HashSet<>(reference), new HashSet<>(testObject));
    }

    @Test
    public void getBuyerSecuritiesAccount() throws Exception {
        assertEquals("ca74b0ce-b77a-4125-93c8-bb7a2d2cb920", toTest.getBuyerSecuritiesAccount());
    }

    @Test
    public void getSellerSecuritiesAccount() throws Exception {
        assertEquals("de97775a-11be-47c5-af33-529b996d2b53", toTest.getSellerSecuritiesAccount());
    }

    @Test
    public void getNumberOfShares() throws Exception {
        assertEquals(12.0, toTest.getNumberOfShares(), 0.0001);
    }

    @Test
    public void getVolume() throws Exception {
        assertEquals(25.68, toTest.getVolume(), 0.0001);
    }

    @Test
    public void getSecurityIdentifier() throws Exception {
        assertEquals("STSD5676", toTest.getSecurityIdentifier());
    }

    @Test
    public void getPrice() throws Exception {
        assertEquals(2.14, toTest.getPrice(), 0.0001);
    }

    @Test
    public void getDate() throws Exception {
        ZonedDateTime reference = ZonedDateTime.ofInstant(Instant.ofEpochMilli(1476953559806L),
            ZoneId.systemDefault());
        assertEquals(reference, toTest.getDate());
    }

    @Test
    public void getId() throws Exception {
        assertEquals("b7a4c7c7-c870-4bf1-badd-667d16fbd274", toTest.getId());
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

        SecurityOrderLog other = gson.fromJson("{\n" +
            "  \"id\": \"12345\"\n" +
            "}", SecurityOrderLog.class);

        assertFalse(toTest.equals(other));
    }

    @Test
    public void testHashCode() throws Exception {
        SecurityOrderLog reference = gson.fromJson(JSON, SecurityOrderLog.class);
        assertEquals(reference.hashCode(), toTest.hashCode());
    }
}