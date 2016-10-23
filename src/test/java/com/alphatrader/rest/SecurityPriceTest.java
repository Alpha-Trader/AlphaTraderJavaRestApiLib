package com.alphatrader.rest;

import com.alphatrader.rest.util.ZonedDateTimeDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Test case for the {@link SecurityPrice} class.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0.0
 */
public class SecurityPriceTest {
    private static HttpResponder httpResponder = HttpResponder.getInstance();
    private static final Gson gson = new GsonBuilder().registerTypeAdapter(ZonedDateTime.class,
        new ZonedDateTimeDeserializer()).create();

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        Http.setInstance(httpResponder.getMock());
    }

    @Test
    public void getSecurityPrices() throws Exception {
        List<LastPrice> reference = gson.fromJson(httpResponder.getJsonForRequest(
            "/api/securityPrices/?securityIdentifier=STK0F513"),
            new TypeToken<ArrayList<LastPrice>>() { }.getType());
        List<LastPrice> testObject = SecurityPrice.getSecurityPrices("STK0F513");
        assertNotEquals(0, testObject.size());
        assertEquals(new HashSet<>(reference), new HashSet<>(testObject));
    }

    @Test
    public void getSecurityPrices1() throws Exception {
        ZonedDateTime date = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault());
        List<LastPrice> reference = gson.fromJson(httpResponder.getJsonForRequest(
            "/api/securityPrices/?securityIdentifier=STK0F513&startDate=0"),
            new TypeToken<ArrayList<LastPrice>>() { }.getType());
        List<LastPrice> testObject = SecurityPrice.getSecurityPrices("STK0F513", date);
        assertNotEquals(0, testObject.size());
        assertEquals(new HashSet<>(reference), new HashSet<>(testObject));
    }

    @Test
    public void getSecurityPrices2() throws Exception {
        ZonedDateTime date = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault());
        List<LastPrice> reference = gson.fromJson(httpResponder.getJsonForRequest(
            "/api/securityPrices/?securityIdentifier=STK0F513&startDate=0&endDate=0"),
            new TypeToken<ArrayList<LastPrice>>() { }.getType());
        List<LastPrice> testObject = SecurityPrice.getSecurityPrices("STK0F513", date, date);
        assertNotEquals(0, testObject.size());
        assertEquals(new HashSet<>(reference), new HashSet<>(testObject));
    }

    @Test
    public void testConstructorIsPrivate() throws Exception {
        Constructor<SecurityPrice> constructor = SecurityPrice.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        constructor.setAccessible(true);
        constructor.newInstance();
    }
}