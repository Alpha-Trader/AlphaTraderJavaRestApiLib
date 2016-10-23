package com.alphatrader.rest;

import com.alphatrader.rest.util.ZonedDateTimeDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
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
 * Test case for the {@link EmploymentAgreement} class.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0.0
 */
public class EmploymentAgreementTest {
    private static HttpResponder httpResponder = HttpResponder.getInstance();
    private static final Gson gson = new GsonBuilder().registerTypeAdapter(ZonedDateTime.class,
        new ZonedDateTimeDeserializer()).create();

    private static final String JSON = "{\n" +
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
        "  \"dailyWage\": 30000,\n" +
        "  \"startDate\": 1475336107328,\n" +
        "  \"id\": \"7bdef87f-6f55-4a2e-9eb5-15559432d057\"\n" +
        "}";

    private EmploymentAgreement toTest;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        Http.setInstance(httpResponder.getMock());
    }

    @Before
    public void setUp() throws Exception {
        toTest = gson.fromJson(JSON, EmploymentAgreement.class);
    }

    @Test
    public void getEmploymentAgreement() throws Exception {
        Company company = gson.fromJson("{\"id\": \"81dcf5a1-b0b6-462a-a40c-e374619edc2f\"}"
            , Company.class);
        EmploymentAgreement reference = gson.fromJson(httpResponder.getJsonForRequest(
            "/api/employmentagreements/company/81dcf5a1-b0b6-462a-a40c-e374619edc2f"),
            EmploymentAgreement.class);
        EmploymentAgreement testObject = EmploymentAgreement.getEmploymentAgreement(company);
        assertEquals(reference, testObject);
    }

    @Test
    public void getEmploymentAgreements() throws Exception {
        List<EmploymentAgreement> reference = gson.fromJson(httpResponder.getJsonForRequest(
            "/api/employmentagreements/"), new TypeToken<ArrayList<EmploymentAgreement>>() { }
            .getType());
        List<EmploymentAgreement> testObject = EmploymentAgreement.getEmploymentAgreements();
        assertNotEquals(0, testObject.size());
        assertEquals(new HashSet<>(reference), new HashSet<>(testObject));
    }

    @Test
    public void getCompany() throws Exception {
        Company reference = gson.fromJson("{\n" +
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
        "  }", Company.class);
        assertEquals(reference, toTest.getCompany());
    }

    @Test
    public void getDailyWage() throws Exception {
        assertEquals(30000, toTest.getDailyWage(), 0.0001);
    }

    @Test
    public void getId() throws Exception {
        assertEquals("7bdef87f-6f55-4a2e-9eb5-15559432d057", toTest.getId());
    }

    @Test
    public void getStartDate() throws Exception {
        ZonedDateTime reference = ZonedDateTime.ofInstant(Instant.ofEpochMilli(1475336107328L),
            ZoneId.systemDefault());
        assertEquals(reference, toTest.getStartDate());
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

        EmploymentAgreement other = gson.fromJson("{\n" +
            "  \"id\": \"12345\"\n" +
            "}", EmploymentAgreement.class);

        assertFalse(toTest.equals(other));
    }

    @Test
    public void testHashCode() throws Exception {
        EmploymentAgreement reference = gson.fromJson(JSON, EmploymentAgreement.class);
        assertEquals(reference.hashCode(), toTest.hashCode());
    }
}