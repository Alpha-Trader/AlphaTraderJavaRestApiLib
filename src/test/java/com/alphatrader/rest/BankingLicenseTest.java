package com.alphatrader.rest;

import com.alphatrader.rest.util.PropertyGson;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.junit.Assert.*;

/**
 * Test case for the {@link BankingLicense} class.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0.0
 */
public class BankingLicenseTest {
    private static HttpResponder httpResponder = HttpResponder.getInstance();
    private static final Gson gson = new PropertyGson().create();

    private final static String JSON = "{\n" +
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
        "}";

    private static final ZonedDateTime testDate = ZonedDateTime.ofInstant(Instant.ofEpochMilli(1475335931401L),
        ZoneId.systemDefault());

    private static final Company testCompany = gson.fromJson("{\n" +
        "  \"securitiesAccountId\": \"57875cf3-de0a-48e4-a3bc-314d4550df12\",\n" +
        "  \"securityIdentifier\": \"STK0F513\",\n" +
        "  \"ceo\": {\n" +
        "    \"gravatarHash\": \"7b7f03e2a716b0efaf4ff8728ad070c3\",\n" +
        "    \"userCapabilities\": {\n" +
        "      \"level2UserEndDate\": null,\n" +
        "      \"premiumEndDate\": null,\n" +
        "      \"level2User\": false,\n" +
        "      \"partner\": true,\n" +
        "      \"premium\": false,\n" +
        "      \"locale\": null\n" +
        "    },\n" +
        "    \"username\": \"FauserneEist\",\n" +
        "    \"id\": \"43986f13-edde-486c-9ef0-718b100a1949\"\n" +
        "  },\n" +
        "  \"name\": \"Katholische Kirche AG\",\n" +
        "  \"id\": \"81dcf5a1-b0b6-462a-a40c-e374619edc2f\"\n" +
        "}", Company.class);

    private BankingLicense toTest;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        Http.setInstance(httpResponder.getMock());
    }

    @Before
    public void setUp() throws Exception {
        toTest = gson.fromJson(JSON, BankingLicense.class);
    }

    @Test
    public void testGetStartDate() throws Exception {
        assertEquals(testDate, toTest.getStartDate());
    }

    @Test
    public void testGetCompany() throws Exception {
        assertEquals(testCompany, toTest.getCompany());
    }

    @Test
    public void testGetId() throws Exception {
        assertEquals("257e1004-8c5e-4d09-beca-8377e8be070c", toTest.getId());
    }

    @Test
    public void testGetById() throws Exception {
        BankingLicense reference = gson.fromJson(httpResponder
            .getJsonForRequest("/api/bankinglicense/257e1004-8c5e-4d09-beca-8377e8be070c"),
            BankingLicense.class);
        BankingLicense testObject = BankingLicense
            .getBankingLicenseById("257e1004-8c5e-4d09-beca-8377e8be070c");
        assertNotNull(testObject);
        assertEquals(reference,testObject );
    }

    @Test
    public void testGetByCompany() throws Exception {
        BankingLicense reference = gson.fromJson(httpResponder
                .getJsonForRequest("/api/bankinglicense/?companyId=81dcf5a1-b0b6-462a-a40c-e374619edc2f"),
            BankingLicense.class);
        BankingLicense testObject = BankingLicense.getBankingLicenseOfCompany(testCompany);
        assertEquals(reference, testObject);
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

        BankingLicense other = gson.fromJson("{\n" +
            "  \"id\": \"12345\"\n" +
            "}", BankingLicense.class);

        assertFalse(toTest.equals(other));
    }

    @Test
    public void testHashCode() throws Exception {
        BankingLicense reference = gson.fromJson(JSON, BankingLicense.class);
        assertEquals(reference.hashCode(), toTest.hashCode());
    }
}