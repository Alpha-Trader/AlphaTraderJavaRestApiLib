package com.alphatrader.rest;

import com.alphatrader.rest.util.ZonedDateTimeDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Before;
import org.junit.Test;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneId;

import static org.junit.Assert.assertEquals;

/**
 * Testcase for the {@link Order} class.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0
 */
public class OrderTest {
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

    }

    @Test
    public void getOtcOrders1() throws Exception {

    }

    @Test
    public void getOrderForCompany() throws Exception {

    }

    @Test
    public void getOrderForCompany1() throws Exception {

    }

    @Test
    public void getOrders() throws Exception {

    }

    @Test
    public void getOrders1() throws Exception {

    }

    @Test
    public void getById() throws Exception {

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
}