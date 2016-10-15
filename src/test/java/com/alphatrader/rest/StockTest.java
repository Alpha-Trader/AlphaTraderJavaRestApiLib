package com.alphatrader.rest;

import org.junit.Before;
import org.junit.Test;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Test case for the {@link Stock} class.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0
 */
public class StockTest {
    private static final String JSON = "{\n" +
        "    \"startDate\": 1469950288064,\n" +
        "    \"endDate\": null,\n" +
        "    \"securityIdentifier\": \"STS63548\",\n" +
        "    \"name\": \"Solid Profit AG\",\n" +
        "    \"type\": \"STOCK\"\n" +
        "  }";

    private Stock toTest;

    @Before
    public void setUp() throws Exception {
        toTest = Stock.createFromJson(JSON);
    }

    @Test
    public void testCreateFromJson() throws Exception {
        assertNotNull(toTest);
    }

    @Test
    public void testGetName() throws Exception {
        assertEquals("Solid Profit AG", toTest.getName());
    }

    @Test
    public void testGetSecurityIdentifier() throws Exception {
        assertEquals("STS63548", toTest.getSecurityIdentifier());
    }

    @Test
    public void testGetStartDate() throws Exception {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(1469950288064L), ZoneId.systemDefault());
        assertEquals(localDateTime, toTest.getStartDate());
    }
}