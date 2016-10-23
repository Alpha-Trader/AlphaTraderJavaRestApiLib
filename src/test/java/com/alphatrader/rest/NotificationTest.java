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
import static org.junit.Assert.assertFalse;

/**
 * Test case for the {@link Notification} class.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0
 */
public class NotificationTest {
    private static HttpResponder httpResponder = HttpResponder.getInstance();
    private static final Gson gson = new GsonBuilder().registerTypeAdapter(ZonedDateTime.class,
        new ZonedDateTimeDeserializer()).create();

    private static final String JSON = "{\n" +
        "  \"subject\": {\n" +
        "    \"message\": \"Order filled\",\n" +
        "    \"substitutions\": [],\n" +
        "    \"filledString\": \"Order filled\"\n" +
        "  },\n" +
        "  \"receiver\": {\n" +
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
        "  \"readByReceiver\": false,\n" +
        "  \"date\": 1476379738684,\n" +
        "  \"id\": \"b60a46a4-967a-4066-b9ca-d654add0e5eb\",\n" +
        "  \"content\": {\n" +
        "    \"message\": \"#: Your order was filled: # # # shares of # (#) at #.\",\n" +
        "    \"substitutions\": [\n" +
        "      \"Church of Scientology AG\",\n" +
        "      \"LIMIT\",\n" +
        "      \"BUY\",\n" +
        "      \"1\",\n" +
        "      \"gierigundstolz Inc.\",\n" +
        "      \"STG6CFBB\",\n" +
        "      \"0.02\"\n" +
        "    ],\n" +
        "    \"filledString\": \"Church of Scientology AG: Your order was filled: LIMIT BUY 1 shares of gierigundstolz Inc. (STG6CFBB) at 0.02.\"\n" +
        "  }\n" +
        "}";

    private Notification toTest;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        Http.setInstance(httpResponder.getMock());
    }

    @Before
    public void setUp() {
        toTest = gson.fromJson(JSON, Notification.class);
    }

    @Test
    public void getNotifications() throws Exception {
        List<Notification> reference = gson.fromJson(httpResponder.getJsonForRequest(
            "/api/notifications"), new TypeToken<ArrayList<Notification>>() { }.getType());
        List<Notification> testObject = Notification.getNotifications();
        assertNotEquals(0, testObject.size());
        assertEquals(new HashSet<>(reference), new HashSet<>(testObject));
    }

    @Test
    public void getUnreadNotifications() throws Exception {
        List<Notification> reference = gson.fromJson(httpResponder.getJsonForRequest(
            "/api/notifications/unread/"), new TypeToken<ArrayList<Notification>>() { }.getType());
        List<Notification> testObject = Notification.getUnreadNotifications();
        assertNotEquals(0, testObject.size());
        assertEquals(new HashSet<>(reference), new HashSet<>(testObject));
    }

    @Test
    public void testGetMessage() throws Exception {
        Message reference = gson.fromJson("{\n" +
        "  \"message\": \"#: Your order was filled: # # # shares of # (#) at #.\",\n" +
        "  \"substitutions\": [\n" +
        "    \"Church of Scientology AG\",\n" +
        "    \"LIMIT\",\n" +
        "    \"BUY\",\n" +
        "    \"1\",\n" +
        "    \"gierigundstolz Inc.\",\n" +
        "    \"STG6CFBB\",\n" +
        "    \"0.02\"\n" +
        "  ],\n" +
        "  \"filledString\": \"Church of Scientology AG: Your order was filled: LIMIT BUY 1 shares " +
            "of gierigundstolz Inc. (STG6CFBB) at 0.02.\"\n" +
        "}", Message.class);
        assertEquals(reference, toTest.getMessage());
    }

    @Test
    public void testGetDate() {
        ZonedDateTime reference = ZonedDateTime.ofInstant(Instant.ofEpochMilli(1476379738684L),
            ZoneId.systemDefault());
        assertEquals(reference, toTest.getDate());
    }

    @Test
    public void testGetSubject() {
        Message reference = gson.fromJson("{\n" +
        "  \"message\": \"Order filled\",\n" +
        "  \"substitutions\": [],\n" +
        "  \"filledString\": \"Order filled\"\n" +
        "}", Message.class);
        assertEquals(reference, toTest.getSubject());
    }

    @Test
    public void testGetReceiver() {
        User reference = gson.fromJson("{\n" +
        "  \"gravatarHash\": \"7b7f03e2a716b0efaf4ff8728ad070c3\",\n" +
        "  \"userCapabilities\": {\n" +
        "    \"level2UserEndDate\": null,\n" +
        "    \"premiumEndDate\": null,\n" +
        "    \"level2User\": false,\n" +
        "    \"partner\": true,\n" +
        "    \"premium\": false,\n" +
        "    \"locale\": null\n" +
        "  },\n" +
        "  \"username\": \"FauserneEist\",\n" +
        "  \"id\": \"43986f13-edde-486c-9ef0-718b100a1949\"\n" +
        "}", User.class);
        assertEquals(reference, toTest.getReceiver());
    }

    @Test
    public void testGetId() {
        assertEquals("b60a46a4-967a-4066-b9ca-d654add0e5eb", toTest.getId());
    }

    @Test
    public void testIsReadByReceiver() {
        assertEquals(false, toTest.isReadByReceiver());
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

        Notification other = gson.fromJson("{\n" +
            "  \"id\": \"12345\"\n" +
            "}", Notification.class);

        assertFalse(toTest.equals(other));
    }

    @Test
    public void testHashCode() throws Exception {
        Notification reference = gson.fromJson(JSON, Notification.class);
        assertEquals(reference.hashCode(), toTest.hashCode());
    }
}