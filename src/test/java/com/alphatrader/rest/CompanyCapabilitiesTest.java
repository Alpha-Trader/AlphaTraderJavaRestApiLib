package com.alphatrader.rest;

import com.alphatrader.rest.util.ZonedDateTimeDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Before;
import org.junit.Test;

import java.time.ZonedDateTime;

import static org.junit.Assert.*;

/**
 * Test case for the {@link CompanyCapabilities} class.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0.0
 */
public class CompanyCapabilitiesTest {
    private static final Gson gson = new GsonBuilder().registerTypeAdapter(ZonedDateTime.class,
        new ZonedDateTimeDeserializer()).create();

    private static final String JSON = "{\n" +
        "  \"bankReady\": false,\n" +
        "  \"bank\": true,\n" +
        "  \"takenCentralBankLoans\": 11000000,\n" +
        "  \"designatedSponsor\": false,\n" +
        "  \"reserves\": 10000000,\n" +
        "  \"maxCentralBankLoans\": 11000000,\n" +
        "  \"netCash\": 1489797.09\n" +
        "}";

    private CompanyCapabilities toTest;

    @Before
    public void setUp() throws Exception {
        toTest = gson.fromJson(JSON, CompanyCapabilities.class);
    }

    @Test
    public void isBankReady() throws Exception {
        assertEquals(false, toTest.isBankReady());
    }

    @Test
    public void isBank() throws Exception {
        assertEquals(true, toTest.isBank());
    }

    @Test
    public void getTakenCentralBankLoans() throws Exception {
        assertEquals(11000000.0, toTest.getTakenCentralBankLoans(), 0.0001);
    }

    @Test
    public void isDesignatedSponsor() throws Exception {
        assertEquals(false, toTest.isDesignatedSponsor());
    }

    @Test
    public void getReserves() throws Exception {
        assertEquals(10000000, toTest.getReserves(), 0.0001);
    }

    @Test
    public void getMaxCentralBankLoans() throws Exception {
        assertEquals(11000000, toTest.getMaxCentralBankLoans(), 0.0001);
    }

    @Test
    public void getNetCash() throws Exception {
        assertEquals(1489797.09, toTest.getNetCash(), 0.0001);
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

        CompanyCapabilities other1 = gson.fromJson("{\n" +
            "  \"bankReady\": true,\n" +
            "  \"bank\": true,\n" +
            "  \"takenCentralBankLoans\": 11000000,\n" +
            "  \"designatedSponsor\": false,\n" +
            "  \"reserves\": 10000000,\n" +
            "  \"maxCentralBankLoans\": 11000000,\n" +
            "  \"netCash\": 1489797.09\n" +
            "}", CompanyCapabilities.class);
        CompanyCapabilities other2 = gson.fromJson("{\n" +
            "  \"bankReady\": false,\n" +
            "  \"bank\": false,\n" +
            "  \"takenCentralBankLoans\": 11000000,\n" +
            "  \"designatedSponsor\": false,\n" +
            "  \"reserves\": 10000000,\n" +
            "  \"maxCentralBankLoans\": 11000000,\n" +
            "  \"netCash\": 1489797.09\n" +
            "}", CompanyCapabilities.class);
        CompanyCapabilities other3 = gson.fromJson("{\n" +
            "  \"bankReady\": false,\n" +
            "  \"bank\": true,\n" +
            "  \"takenCentralBankLoans\": 0,\n" +
            "  \"designatedSponsor\": false,\n" +
            "  \"reserves\": 10000000,\n" +
            "  \"maxCentralBankLoans\": 11000000,\n" +
            "  \"netCash\": 1489797.09\n" +
            "}", CompanyCapabilities.class);
        CompanyCapabilities other4 = gson.fromJson("{\n" +
            "  \"bankReady\": false,\n" +
            "  \"bank\": true,\n" +
            "  \"takenCentralBankLoans\": 11000000,\n" +
            "  \"designatedSponsor\": true,\n" +
            "  \"reserves\": 10000000,\n" +
            "  \"maxCentralBankLoans\": 11000000,\n" +
            "  \"netCash\": 1489797.09\n" +
            "}", CompanyCapabilities.class);
        CompanyCapabilities other5 = gson.fromJson("{\n" +
            "  \"bankReady\": false,\n" +
            "  \"bank\": true,\n" +
            "  \"takenCentralBankLoans\": 11000000,\n" +
            "  \"designatedSponsor\": false,\n" +
            "  \"reserves\": 0,\n" +
            "  \"maxCentralBankLoans\": 11000000,\n" +
            "  \"netCash\": 1489797.09\n" +
            "}", CompanyCapabilities.class);
        CompanyCapabilities other6 = gson.fromJson("{\n" +
            "  \"bankReady\": false,\n" +
            "  \"bank\": true,\n" +
            "  \"takenCentralBankLoans\": 11000000,\n" +
            "  \"designatedSponsor\": false,\n" +
            "  \"reserves\": 10000000,\n" +
            "  \"maxCentralBankLoans\": 0,\n" +
            "  \"netCash\": 1489797.09\n" +
            "}", CompanyCapabilities.class);
        CompanyCapabilities other7 = gson.fromJson("{\n" +
            "  \"bankReady\": false,\n" +
            "  \"bank\": true,\n" +
            "  \"takenCentralBankLoans\": 11000000,\n" +
            "  \"designatedSponsor\": false,\n" +
            "  \"reserves\": 10000000,\n" +
            "  \"maxCentralBankLoans\": 11000000,\n" +
            "  \"netCash\": 0\n" +
            "}", CompanyCapabilities.class);

        assertFalse(toTest.equals(other1));
        assertFalse(toTest.equals(other2));
        assertFalse(toTest.equals(other3));
        assertFalse(toTest.equals(other4));
        assertFalse(toTest.equals(other5));
        assertFalse(toTest.equals(other6));
        assertFalse(toTest.equals(other7));
    }

    @Test
    public void testHashCode() throws Exception {
        CompanyCapabilities reference = gson.fromJson(JSON, CompanyCapabilities.class);
        assertEquals(reference.hashCode(), toTest.hashCode());
    }
}