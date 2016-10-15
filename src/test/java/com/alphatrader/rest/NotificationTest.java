package com.alphatrader.rest;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Test case for the {@link Notification} class.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0
 */
public class NotificationTest {
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

    @Before
    public void setUp() {
        toTest = Notification.createFromJson(JSON);
    }

    @Test
    public void testCreateFromJson() throws Exception {
        assertNotNull(toTest);
    }

    @Test
    public void testGetMessage() throws Exception {
        assertEquals("Church of Scientology AG: Your order was filled: LIMIT BUY 1 shares of gierigundstolz Inc. (STG6CFBB) at 0.02.", toTest.getMessage());
    }
}