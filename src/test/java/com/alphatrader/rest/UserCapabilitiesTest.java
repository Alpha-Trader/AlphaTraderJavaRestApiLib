package com.alphatrader.rest;

import com.alphatrader.rest.util.ZonedDateTimeDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Before;
import org.junit.Test;

import java.time.ZonedDateTime;

import static org.junit.Assert.*;

/**
 * Test case for the {@link UserCapabilities} class.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0.0
 */
public class UserCapabilitiesTest {
    private static final Gson gson = new GsonBuilder().registerTypeAdapter(ZonedDateTime.class,
        new ZonedDateTimeDeserializer()).create();

    private static final String JSON = "{\n" +
        "  \"level2UserEndDate\": null,\n" +
        "  \"premiumEndDate\": null,\n" +
        "  \"level2User\": false,\n" +
        "  \"partner\": true,\n" +
        "  \"premium\": false,\n" +
        "  \"locale\": null\n" +
        "}";

    private UserCapabilities toTest;

    @Before
    public void setUp() throws Exception {
        toTest = gson.fromJson(JSON, UserCapabilities.class);
    }

    @Test
    public void isLevel2User() throws Exception {
        assertFalse(toTest.isLevel2User());
    }

    @Test
    public void getLevel2UserEndDate() throws Exception {
        assertNull(toTest.getLevel2UserEndDate());
    }

    @Test
    public void getLocale() throws Exception {
        assertNull(toTest.getLocale());
    }

    @Test
    public void isPartner() throws Exception {
        assertTrue(toTest.isPartner());
    }

    @Test
    public void isPremium() throws Exception {
        assertFalse(toTest.isPremium());
    }

    @Test
    public void getPremiumEndDate() throws Exception {
        assertNull(toTest.getPremiumEndDate());
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

        UserCapabilities other = gson.fromJson("{\n" +
            "  \"id\": \"12345\"\n" +
            "}", UserCapabilities.class);

        assertFalse(toTest.equals(other));
    }

    @Test
    public void testHashCode() throws Exception {
        UserCapabilities reference = gson.fromJson(JSON, UserCapabilities.class);
        assertEquals(reference.hashCode(), toTest.hashCode());
    }
}