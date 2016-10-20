package com.alphatrader.rest;

import com.alphatrader.rest.util.ZonedDateTimeDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Before;
import org.junit.Test;

import java.time.ZonedDateTime;

import static org.junit.Assert.*;

/**
 * Test case for the {@link Vote} class.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0.0
 */
public class VoteTest {
    private static final Gson gson = new GsonBuilder().registerTypeAdapter(ZonedDateTime.class,
        new ZonedDateTimeDeserializer()).create();

    private static final String JSON = "{\n" +
        "  \"voices\": 96576,\n" +
        "  \"voter\": {\n" +
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
        "  \"type\": \"NO\"\n" +
        "}";

    private Vote toTest;

    @Before
    public void setUp() throws Exception {
        toTest = gson.fromJson(JSON, Vote.class);
    }

    @Test
    public void getType() throws Exception {
        assertEquals(Vote.Answer.NO, toTest.getType());
    }

    @Test
    public void getVoices() throws Exception {
        assertEquals(Long.valueOf(96576), toTest.getVoices());
    }

    @Test
    public void getVoter() throws Exception {
        User reference = gson.fromJson("{\n" +
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
        "  }", User.class);
        assertEquals(reference, toTest.getVoter());
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

        Vote other1 = gson.fromJson("{\n" +
        "  \"voices\": 1,\n" +
        "  \"voter\": {\n" +
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
        "  \"type\": \"NO\"\n" +
        "}", Vote.class);

        Vote other2 = gson.fromJson("{\n" +
            "  \"voices\": 96576,\n" +
            "  \"voter\": {\n" +
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
            "  \"type\": \"YES\"\n" +
            "}", Vote.class);

        assertFalse(toTest.equals(other1));
        assertFalse(toTest.equals(other2));
    }

    @Test
    public void testHashCode() throws Exception {
        Vote reference = gson.fromJson(JSON, Vote.class);
        assertEquals(reference.hashCode(), toTest.hashCode());
    }
}