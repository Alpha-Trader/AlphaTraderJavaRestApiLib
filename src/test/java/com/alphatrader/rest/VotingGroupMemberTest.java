package com.alphatrader.rest;

import com.alphatrader.rest.util.PropertyGson;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test case for the {@link VotingGroupMember} class.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0.0
 */
public class VotingGroupMemberTest {
    private static final Gson gson = new PropertyGson().create();

    private static final String JSON = "{\n" +
        "  \"groupMember\": {\n" +
        "    \"gravatarHash\": \"62e31c045bcdd2246badc14b6a4321ea\",\n" +
        "    \"userCapabilities\": {\n" +
        "      \"level2UserEndDate\": null,\n" +
        "      \"premiumEndDate\": null,\n" +
        "      \"level2User\": false,\n" +
        "      \"partner\": false,\n" +
        "      \"premium\": false,\n" +
        "      \"locale\": null\n" +
        "    },\n" +
        "    \"username\": \"DerDumme\",\n" +
        "    \"id\": \"9836d530-92af-45dd-a597-8f694fef77a6\"\n" +
        "  },\n" +
        "  \"numberOfVoices\": 1000\n" +
        "}";

    private VotingGroupMember toTest;

    @Before
    public void setUp() throws Exception {
        toTest = gson.fromJson(JSON, VotingGroupMember.class);
    }

    @Test
    public void getGroupMember() throws Exception {
        User reference = gson.fromJson("{\n" +
        "    \"gravatarHash\": \"62e31c045bcdd2246badc14b6a4321ea\",\n" +
        "    \"userCapabilities\": {\n" +
        "      \"level2UserEndDate\": null,\n" +
        "      \"premiumEndDate\": null,\n" +
        "      \"level2User\": false,\n" +
        "      \"partner\": false,\n" +
        "      \"premium\": false,\n" +
        "      \"locale\": null\n" +
        "    },\n" +
        "    \"username\": \"DerDumme\",\n" +
        "    \"id\": \"9836d530-92af-45dd-a597-8f694fef77a6\"\n" +
        "  }", User.class);
        assertEquals(reference, toTest.getGroupMember());
    }

    @Test
    public void getNumberOfVoices() throws Exception {
        assertEquals(Long.valueOf(1000), toTest.getNumberOfVoices());
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
        VotingGroupMember reference = gson.fromJson(JSON, VotingGroupMember.class);
        assertEquals(reference.hashCode(), toTest.hashCode());
    }
}