package com.alphatrader.rest;

import com.alphatrader.rest.util.LocalDateTimeDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Test case for the {@link Company} class.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0
 */
public class CompanyTest {
    private static final Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class,
        new LocalDateTimeDeserializer()).create();

    private Company toTest;

    @Before
    public void setUp() throws Exception {
        toTest = gson.fromJson("{\n" +
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
    }

    @Test
    public void testCreateFromJson() throws Exception {
        assertNotNull(toTest);
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
}