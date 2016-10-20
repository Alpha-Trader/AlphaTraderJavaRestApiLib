package com.alphatrader.rest;

import com.alphatrader.rest.util.ZonedDateTimeDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

/**
 * Test case for the {@link Company} class.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0
 */
public class CompanyTest {
    private static HttpResponder httpResponder = HttpResponder.getInstance();
    private static final Gson gson = new GsonBuilder().registerTypeAdapter(ZonedDateTime.class,
        new ZonedDateTimeDeserializer()).create();

    private static final String JSON = "{\n" +
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
        "  \"listing\": {\n" +
        "          \"startDate\": 1469951698361,\n" +
        "          \"endDate\": null,\n" +
        "          \"securityIdentifier\": \"STK0F513\",\n" +
        "          \"name\": \"Katholische Kirche AG\",\n" +
        "          \"type\": \"STOCK\"\n" +
        "        }," +
        "  \"name\": \"Katholische Kirche AG\",\n" +
        "  \"id\": \"81dcf5a1-b0b6-462a-a40c-e374619edc2f\"\n" +
        "}";

    private Company toTest;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        Http.setInstance(httpResponder.getMock());
    }

    @Before
    public void setUp() throws Exception {
        toTest = gson.fromJson(JSON, Company.class);
    }

    @Test
    public void getAllUserCompanies() throws Exception {
        List<Company> reference = gson.fromJson(httpResponder.getJsonForRequest("/api/companies/"),
            new TypeToken<ArrayList<Company>>() { }.getType());
        List<Company> testObject = Company.getAllUserCompanies();
        assertNotEquals(0, testObject.size());
        assertEquals(new HashSet<>(reference), new HashSet<>(testObject));
    }

    @Test
    public void getAllUserCompaniesByUsername() throws Exception {
        List<Company> reference = gson.fromJson(httpResponder.getJsonForRequest(
            "/api/companies/ceo/username/FauserneEist"),
            new TypeToken<ArrayList<Company>>() { }.getType());
        List<Company> testObject = Company.getAllUserCompaniesByUsername("FauserneEist");
        assertNotEquals(0, testObject.size());
        assertEquals(new HashSet<>(reference), new HashSet<>(testObject));
    }

    @Test
    public void getAllUserCompanies1() throws Exception {
        User user = gson.fromJson("{" +
            "\"id\": \"43986f13-edde-486c-9ef0-718b100a1949\"" +
            "}", User.class);
        List<Company> reference = gson.fromJson(httpResponder.getJsonForRequest(
            "/api/companies/ceo/userid/43986f13-edde-486c-9ef0-718b100a1949"),
            new TypeToken<ArrayList<Company>>() { }.getType());
        List<Company> testObject = Company.getAllUserCompanies(user);
        assertNotEquals(0, testObject.size());
        assertEquals(new HashSet<>(reference), new HashSet<>(testObject));
    }

    @Test
    public void getAllCompanies() throws Exception {
        List<Company> reference = gson.fromJson(httpResponder.getJsonForRequest("/api/companies/all/"),
            new TypeToken<ArrayList<Company>>() { }.getType());
        List<Company> testObject = Company.getAllCompanies();
        assertNotEquals(0, testObject.size());
        assertEquals(new HashSet<>(reference), new HashSet<>(testObject));
    }

    @Test
    public void getBySecuritiesAccountId() throws Exception {
        Company reference = gson.fromJson(httpResponder.getJsonForRequest(
            "/api/companies/securitiesaccount/57875cf3-de0a-48e4-a3bc-314d4550df12"), Company.class);
        Company testObject = Company.getBySecuritiesAccountId("57875cf3-de0a-48e4-a3bc-314d4550df12");
        assertNotNull(testObject);
        assertEquals(reference, testObject);
    }

    @Test
    public void getBySecurityIdentifier() throws Exception {
        Company reference = gson.fromJson(httpResponder.getJsonForRequest(
            "/api/companies/securityIdentifier/STK0F513"), Company.class);
        Company testObject = Company.getBySecurityIdentifier("STK0F513");
        assertNotNull(testObject);
        assertEquals(reference, testObject);
    }

    @Test
    public void getById() throws Exception {
        Company reference = gson.fromJson(httpResponder.getJsonForRequest(
            "/api/companies/81dcf5a1-b0b6-462a-a40c-e374619edc2f"), Company.class);
        Company testObject = Company.getById("81dcf5a1-b0b6-462a-a40c-e374619edc2f");
        assertNotNull(testObject);
        assertEquals(reference, testObject);
    }

    @Test
    public void searchByName() throws Exception {
        List<Company> reference = gson.fromJson(httpResponder.getJsonForRequest(
            "/api/search/companies/Katholische"), new TypeToken<ArrayList<Company>>() { }.getType());
        List<Company> testObject = Company.searchByName("Katholische");
        assertNotEquals(0, testObject.size());
        assertEquals(new HashSet<>(reference), new HashSet<>(testObject));
    }

    @Test
    public void testGetName() throws Exception {
        assertEquals("Katholische Kirche AG", toTest.getName());
    }

   @Test
    public void testGetSecuritiesAccountId() throws Exception {
        assertEquals("57875cf3-de0a-48e4-a3bc-314d4550df12", toTest.getSecuritiesAccountId());
    }

    @Test
    public void testGetId() throws Exception {
        assertEquals("81dcf5a1-b0b6-462a-a40c-e374619edc2f", toTest.getId());
    }

    @Test
    public void testGetListing() throws Exception {
        Listing reference = gson.fromJson("{\n" +
            "  \"startDate\": 1469951698361,\n" +
            "  \"endDate\": null,\n" +
            "  \"securityIdentifier\": \"STK0F513\",\n" +
            "  \"name\": \"Katholische Kirche AG\",\n" +
            "  \"type\": \"STOCK\"\n" +
            "}", Listing.class);
        assertEquals(reference, toTest.getListing());
    }

    @Test
    public void testGetProfile() throws Exception {
        CompanyProfile reference = gson.fromJson(httpResponder.getJsonForRequest(
            "/api/companyprofiles/81dcf5a1-b0b6-462a-a40c-e374619edc2f"), CompanyProfile.class);
        assertEquals(reference, toTest.getProfile());
        assertEquals(reference, toTest.getProfile());
        assertNotNull(toTest.getProfile());
    }

    @Test
    public void testGetPortfolio() throws Exception {
        Portfolio reference = gson.fromJson(httpResponder.getJsonForRequest(
            "/api/portfolios/57875cf3-de0a-48e4-a3bc-314d4550df12"), Portfolio.class);
        assertEquals(reference, toTest.getPortfolio());
        assertEquals(reference, toTest.getPortfolio());
        assertNotNull(toTest.getPortfolio());
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

        Company other = gson.fromJson("{\n" +
            "  \"id\": \"12345\"\n" +
            "}", Company.class);

        assertFalse(toTest.equals(other));
    }

    @Test
    public void testHashCode() throws Exception {
        Company reference = gson.fromJson(JSON, Company.class);
        assertEquals(reference.hashCode(), toTest.hashCode());
    }
}