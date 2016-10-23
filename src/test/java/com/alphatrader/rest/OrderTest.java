package com.alphatrader.rest;

import com.alphatrader.rest.util.ZonedDateTimeDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Testcase for the {@link Order} class.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0
 */
public class OrderTest {
    private static HttpResponder httpResponder = HttpResponder.getInstance();
    private static final Gson gson = new GsonBuilder().registerTypeAdapter(ZonedDateTime.class,
        new ZonedDateTimeDeserializer()).create();

    private static final String JSON = "{\n" +
        "    \"counterPartyName\": null,\n" +
        "    \"creationDate\": 1475619375635,\n" +
        "    \"committedCash\": 787.18,\n" +
        "    \"numberOfShares\": 39359,\n" +
        "    \"action\": \"BUY\",\n" +
        "    \"securityIdentifier\": \"STG6CFBB\",\n" +
        "    \"listing\": {\n" +
        "      \"startDate\": 1474099164997,\n" +
        "      \"endDate\": null,\n" +
        "      \"securityIdentifier\": \"STG6CFBB\",\n" +
        "      \"name\": \"gierigundstolz Inc.\",\n" +
        "      \"type\": \"STOCK\"\n" +
        "    },\n" +
        "    \"counterParty\": null,\n" +
        "    \"price\": 0.02,\n" +
        "    \"ownerName\": \"Church of Scientology AG\",\n" +
        "    \"owner\": \"26ea411d-f873-4976-80c4-09e3f1cdabea\",\n" +
        "    \"id\": \"a7859ce3-75d0-44fd-98ab-bfc77aa205d5\",\n" +
        "    \"type\": \"LIMIT\"\n" +
        "  }";

    private Order toTest;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        Http.setInstance(httpResponder.getMock());
    }

    @Before
    public void setUp() {
        toTest = gson.fromJson(JSON, Order.class);
    }

    @Test
    public void getName() throws Exception {
        assertEquals("gierigundstolz Inc.", toTest.getName());
    }

    @Test
    public void getCreationDate() throws Exception {
        ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(Instant.ofEpochMilli(1475619375635L),
            ZoneId.systemDefault());
        assertEquals(zonedDateTime, toTest.getCreationDate());
    }

    @Test
    public void getType() throws Exception {
        assertEquals(Order.Type.LIMIT, toTest.getType());
    }

    @Test
    public void getSecurityIdentifier() throws Exception {
        assertEquals("STG6CFBB", toTest.getSecurityIdentifier());
    }

    @Test
    public void getNumberOfShares() throws Exception {
        assertEquals(Long.valueOf(39359), toTest.getNumberOfShares());
    }

    @Test
    public void getOtcOrders() throws Exception {
        Company company = Company.getById("81dcf5a1-b0b6-462a-a40c-e374619edc2f");
        List<Order> reference = gson.fromJson(httpResponder.getJsonForRequest(
            "/api/securityorders/counterparty/57875cf3-de0a-48e4-a3bc-314d4550df12"),
            new TypeToken<ArrayList<Order>>() { }.getType());
        List<Order> testObject = Order.getOtcOrders(company);
        assertNotEquals(0, testObject.size());
        assertEquals(new HashSet<>(reference), new HashSet<>(testObject));
    }

    @Test
    public void getOrdersForCompany() throws Exception {
        Company company = Company.getById("81dcf5a1-b0b6-462a-a40c-e374619edc2f");
        List<Order> reference = gson.fromJson(httpResponder.getJsonForRequest(
            "/api/orderlist/STK0F513"),
            new TypeToken<ArrayList<Order>>() { }.getType());
        List<Order> testObject = Order.getOrdersForCompany(company);
        assertNotEquals(0, testObject.size());
        assertEquals(new HashSet<>(reference), new HashSet<>(testObject));
    }

    @Test
    public void getOrders() throws Exception {
        Company company = Company.getById("81dcf5a1-b0b6-462a-a40c-e374619edc2f");
        List<Order> reference = gson.fromJson(httpResponder.getJsonForRequest(
            "/api/securityorders/securitiesaccount/57875cf3-de0a-48e4-a3bc-314d4550df12"),
            new TypeToken<ArrayList<Order>>() { }.getType());
        List<Order> testObject = Order.getOrders(company);
        assertNotEquals(0, testObject.size());
        assertEquals(new HashSet<>(reference), new HashSet<>(testObject));
    }

    @Test
    public void getById() throws Exception {
        Order reference = gson.fromJson(httpResponder.getJsonForRequest(
            "/api/securityorders/4a813308-a9ad-4755-876c-d46df4a723f0"), Order.class);
        Order testObject = Order.getById("4a813308-a9ad-4755-876c-d46df4a723f0");
        assertNotNull(testObject);
        assertEquals(reference, testObject);
    }

    @Test
    public void getListing() throws Exception {
        Listing reference = gson.fromJson("{\n" +
        "  \"startDate\": 1474099164997,\n" +
        "  \"endDate\": null,\n" +
        "  \"securityIdentifier\": \"STG6CFBB\",\n" +
        "  \"name\": \"gierigundstolz Inc.\",\n" +
        "  \"type\": \"STOCK\"\n" +
        "}", Listing.class);
        assertEquals(reference, toTest.getListing());
    }

    @Test
    public void getCounterPartyName() throws Exception {
        assertEquals(null, toTest.getCounterPartyName());
    }

    @Test
    public void getCounterParty() throws Exception {
        assertEquals(null, toTest.getCounterParty());
    }

    @Test
    public void getAction() throws Exception {
        assertEquals(Order.Action.BUY, toTest.getAction());
    }

    @Test
    public void getCommittedCash() throws Exception {
        assertEquals(787.18, toTest.getCommittedCash(), 0.0001);
    }

    @Test
    public void getPrice() throws Exception {
        assertEquals(0.02, toTest.getPrice(), 0.0001);
    }

    @Test
    public void getOwnerName() throws Exception {
        assertEquals("Church of Scientology AG", toTest.getOwnerName());
    }

    @Test
    public void getOwner() throws Exception {
        assertEquals("26ea411d-f873-4976-80c4-09e3f1cdabea", toTest.getOwner());
    }

    @Test
    public void getId() throws Exception {
        assertEquals("a7859ce3-75d0-44fd-98ab-bfc77aa205d5", toTest.getId());
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

        Order other = gson.fromJson("{\n" +
            "  \"id\": \"12345\"\n" +
            "}", Order.class);

        assertFalse(toTest.equals(other));
    }

    @Test
    public void testHashCode() throws Exception {
        Order reference = gson.fromJson(JSON, Order.class);
        assertEquals(reference.hashCode(), toTest.hashCode());
    }
}