package com.alphatrader.rest;

import com.alphatrader.rest.util.ZonedDateTimeDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Before;
import org.junit.Test;

import java.time.ZonedDateTime;

import static org.junit.Assert.*;

/**
 * Test case for the {@link SecuritySponsorship} class.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0.0
 */
public class SecuritySponsorshipTest {
    private static final Gson gson = new GsonBuilder().registerTypeAdapter(ZonedDateTime.class,
        new ZonedDateTimeDeserializer()).create();

    private static final String JSON = "{\n" +
        "  \"designatedSponsor\": {\n" +
        "    \"ceo\": {\n" +
        "      \"gravatarHash\": \"hash\",\n" +
        "      \"id\": \"id\",\n" +
        "      \"userCapabilities\": {\n" +
        "        \"level2User\": true,\n" +
        "        \"level2UserEndDate\": 0,\n" +
        "        \"locale\": \"string\",\n" +
        "        \"partner\": true,\n" +
        "        \"premium\": true,\n" +
        "        \"premiumEndDate\": 1\n" +
        "      },\n" +
        "      \"username\": \"username\"\n" +
        "    },\n" +
        "    \"id\": \"id\",\n" +
        "    \"name\": \"name\",\n" +
        "    \"securitiesAccountId\": \"securitiesAccountId\",\n" +
        "    \"securityIdentifier\": \"securityIdentifier\"\n" +
        "  },\n" +
        "  \"listing\": {\n" +
        "    \"endDate\": 2,\n" +
        "    \"name\": \"name\",\n" +
        "    \"securityIdentifier\": \"securityIdentifier\",\n" +
        "    \"startDate\": 3,\n" +
        "    \"type\": \"STOCK\"\n" +
        "  },\n" +
        "  \"sponsorRating\": {\n" +
        "    \"salary\": 4,\n" +
        "    \"value\": \"value\"\n" +
        "  }\n" +
        "}";

    private SecuritySponsorship toTest;

    @Before
    public void setUp() throws Exception {
        toTest = gson.fromJson(JSON, SecuritySponsorship.class);
    }

    @Test
    public void testGetDesignatedSponsor() throws Exception {
        Company reference = gson.fromJson("{\n" +
        "        \"ceo\": {\n" +
        "          \"gravatarHash\": \"hash\",\n" +
        "          \"id\": \"id\",\n" +
        "          \"userCapabilities\": {\n" +
        "            \"level2User\": true,\n" +
        "            \"level2UserEndDate\": 0,\n" +
        "            \"locale\": \"string\",\n" +
        "            \"partner\": true,\n" +
        "            \"premium\": true,\n" +
        "            \"premiumEndDate\": 1\n" +
        "          },\n" +
        "          \"username\": \"username\"\n" +
        "        },\n" +
        "        \"id\": \"id\",\n" +
        "        \"name\": \"name\",\n" +
        "        \"securitiesAccountId\": \"securitiesAccountId\",\n" +
        "        \"securityIdentifier\": \"securityIdentifier\"\n" +
        "      }", Company.class);

        assertEquals(reference, toTest.getDesignatedSponsor());
    }

    @Test
    public void testGetListing() throws Exception {
        Listing reference = gson.fromJson("{\n" +
        "    \"endDate\": 2,\n" +
        "    \"name\": \"name\",\n" +
        "    \"securityIdentifier\": \"securityIdentifier\",\n" +
        "    \"startDate\": 3,\n" +
        "    \"type\": \"STOCK\"\n" +
        "  }", Listing.class);

        assertEquals(reference, toTest.getListing());
    }

    @Test
    public void testGetSponsorRating() throws Exception {
        SponsorRating reference = gson.fromJson("{\n" +
        "    \"salary\": 4,\n" +
        "    \"value\": \"value\"\n" +
        "  }", SponsorRating.class);

        assertEquals(reference, toTest.getSponsorRating());
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
        SecuritySponsorship reference = gson.fromJson(JSON, SecuritySponsorship.class);
        assertEquals(reference.hashCode(), toTest.hashCode());
    }

}