package com.alphatrader.rest;

import com.alphatrader.rest.util.ZonedDateTimeDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import org.apache.http.HttpResponseFactory;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.DefaultHttpResponseFactory;
import org.apache.http.message.BasicStatusLine;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.ZonedDateTime;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test case for the {@link BankAccount} class.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0.0
 */
public class BankAccountTest {
    private static HttpResponder httpResponder = HttpResponder.getInstance();
    private static final Gson gson = new GsonBuilder().registerTypeAdapter(ZonedDateTime.class,
        new ZonedDateTimeDeserializer()).create();

    private static final String JSON = "{\n" +
        "  \"cash\": 10000,\n" +
        "  \"id\": \"443db0b7-c399-4252-8166-061d8de1d634\"\n" +
        "}";

    private BankAccount toTest;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        Http.setInstance(httpResponder.getMock());
    }

    @Before
    public void setUp() throws Exception {
        toTest = gson.fromJson(JSON, BankAccount.class);
    }

    @Test
    public void testGetUserBankAccount() throws Exception {
        BankAccount testAccount = BankAccount.getUserBankAccount();
        BankAccount reference = gson.fromJson(httpResponder.getJsonForRequest("/api/bankaccounts/"),
            BankAccount.class);
        assertNotNull(testAccount);
        assertEquals(reference, testAccount);
    }

    @Test
    public void testGetCash() throws Exception {
        assertEquals(10000.0, toTest.getCash(), 0.0001);
    }

    @Test
    public void testGetId() throws Exception {
        assertEquals("443db0b7-c399-4252-8166-061d8de1d634", toTest.getId());
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
    }

    @Test
    public void testHashCode() throws Exception {
        BankAccount reference = gson.fromJson(JSON, BankAccount.class);
        assertEquals(reference.hashCode(), toTest.hashCode());
    }
}