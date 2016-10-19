package com.alphatrader.rest;

import com.alphatrader.rest.util.ZonedDateTimeDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Before;
import org.junit.Test;

import java.time.ZonedDateTime;

import static org.junit.Assert.*;

/**
 * Test case for the {@link CentralBankReserves} class.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0.0
 */
public class CentralBankReservesTest {
    private static final Gson gson = new GsonBuilder().registerTypeAdapter(ZonedDateTime.class,
        new ZonedDateTimeDeserializer()).create();

    private static final String JSON = "{\n" +
        "  \"bankingLicense\": {\n" +
        "    \"startDate\": 1475335931401,\n" +
        "    \"company\": {\n" +
        "      \"securitiesAccountId\": \"57875cf3-de0a-48e4-a3bc-314d4550df12\",\n" +
        "      \"securityIdentifier\": \"STK0F513\",\n" +
        "      \"ceo\": {\n" +
        "        \"gravatarHash\": \"7b7f03e2a716b0efaf4ff8728ad070c3\",\n" +
        "        \"userCapabilities\": {\n" +
        "          \"level2UserEndDate\": null,\n" +
        "          \"premiumEndDate\": null,\n" +
        "          \"level2User\": false,\n" +
        "          \"partner\": true,\n" +
        "          \"premium\": false,\n" +
        "          \"locale\": null\n" +
        "        },\n" +
        "        \"username\": \"FauserneEist\",\n" +
        "        \"id\": \"43986f13-edde-486c-9ef0-718b100a1949\"\n" +
        "      },\n" +
        "      \"name\": \"Katholische Kirche AG\",\n" +
        "      \"id\": \"81dcf5a1-b0b6-462a-a40c-e374619edc2f\"\n" +
        "    },\n" +
        "    \"id\": \"257e1004-8c5e-4d09-beca-8377e8be070c\"\n" +
        "  },\n" +
        "  \"maxCentralBankLoans\": 11000000,\n" +
        "  \"cashHolding\": 10000000,\n" +
        "  \"id\": \"7cd2fa36-0c98-4a31-bb7f-8043f0dfa1c1\"\n" +
        "}";

    private CentralBankReserves toTest;

    @Before
    public void setUp() throws Exception {
        toTest = gson.fromJson(JSON, CentralBankReserves.class);
    }

    @Test
    public void testGetByCompany() throws Exception {

    }

    @Test
    public void testGetByCompanyString() throws Exception {

    }

    @Test
    public void testGetId() throws Exception {
        assertEquals("7cd2fa36-0c98-4a31-bb7f-8043f0dfa1c1", toTest.getId());
    }

    @Test
    public void testGetBankingLicense() throws Exception {
        BankingLicense reference = gson.fromJson("{\n" +
        "  \"startDate\": 1475335931401,\n" +
        "  \"company\": {\n" +
        "    \"securitiesAccountId\": \"57875cf3-de0a-48e4-a3bc-314d4550df12\",\n" +
        "    \"securityIdentifier\": \"STK0F513\",\n" +
        "    \"ceo\": {\n" +
        "      \"gravatarHash\": \"7b7f03e2a716b0efaf4ff8728ad070c3\",\n" +
        "      \"userCapabilities\": {\n" +
        "        \"level2UserEndDate\": null,\n" +
        "        \"premiumEndDate\": null,\n" +
        "        \"level2User\": false,\n" +
        "        \"partner\": true,\n" +
        "        \"premium\": false,\n" +
        "        \"locale\": null\n" +
        "      },\n" +
        "      \"username\": \"FauserneEist\",\n" +
        "      \"id\": \"43986f13-edde-486c-9ef0-718b100a1949\"\n" +
        "    },\n" +
        "    \"name\": \"Katholische Kirche AG\",\n" +
        "    \"id\": \"81dcf5a1-b0b6-462a-a40c-e374619edc2f\"\n" +
        "  },\n" +
        "  \"id\": \"257e1004-8c5e-4d09-beca-8377e8be070c\"\n" +
        "}", BankingLicense.class);
        assertEquals(reference, toTest.getBankingLicense());
    }

    @Test
    public void testGetMaxCentralBankLoans() throws Exception {
        assertEquals(11000000.0, toTest.getMaxCentralBankLoans(), 0.0001);
    }

    @Test
    public void testGetCashHolding() throws Exception {
        assertEquals(10000000.0, toTest.getCashHolding(), 0.0001);
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

        CentralBankReserves other = gson.fromJson("{\n" +
            "  \"id\": \"12345\"\n" +
            "}", CentralBankReserves.class);

        assertFalse(toTest.equals(other));
    }

    @Test
    public void testHashCode() throws Exception {
        CentralBankReserves reference = gson.fromJson(JSON, CentralBankReserves.class);
        assertEquals(reference.hashCode(), toTest.hashCode());
    }
}