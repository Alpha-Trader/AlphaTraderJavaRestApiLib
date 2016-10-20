package com.alphatrader.rest;

import com.alphatrader.rest.util.ZonedDateTimeDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.junit.Before;
import org.junit.Test;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0.0
 */
public class PollTest {
    private static final Gson gson = new GsonBuilder().registerTypeAdapter(ZonedDateTime.class,
        new ZonedDateTimeDeserializer()).create();

    private static final String JSON = "{\n" +
        "  \"dailyWage\": 0,\n" +
        "  \"applicant\": {\n" +
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
        "  \"abstentionRule\": \"COUNTS_AS_REFUSAL\",\n" +
        "  \"approvalVotesPercentage\": 0,\n" +
        "  \"company\": {\n" +
        "    \"securityIdentifier\": \"STSB8312\",\n" +
        "    \"listing\": {\n" +
        "      \"startDate\": 1475678348697,\n" +
        "      \"endDate\": null,\n" +
        "      \"securityIdentifier\": \"STSB8312\",\n" +
        "      \"name\": \"randolphduke\",\n" +
        "      \"type\": \"STOCK\"\n" +
        "    },\n" +
        "    \"name\": \"randolphduke\",\n" +
        "    \"id\": \"cb1cbdfe-d23e-42a7-b840-0eec1d00c85f\"\n" +
        "  },\n" +
        "  \"totalNumberOfVoices\": 107576,\n" +
        "  \"votes\": [\n" +
        "    {\n" +
        "      \"voices\": 96576,\n" +
        "      \"voter\": {\n" +
        "        \"gravatarHash\": \"7b7f03e2a716b0efaf4ff8728ad070c3\",\n" +
        "        \"userCapabilities\": {\n" +
        "          \"level2UserEndDate\": null,\n" +
        "          \"premiumEndDate\": null,\n" +
        "          \"level2User\": false,\n" +
        "          \"partner\": true,\n" +
        "          \"premium\": false,\n" +
        "          \"locale\": null\n" +
        "        },\n" +
        "        \"username\": \"FauserneEist\",\n" +
        "        \"id\": \"43986f13-edde-486c-9ef0-718b100a1949\"\n" +
        "      },\n" +
        "      \"type\": \"NO\"\n" +
        "    }\n" +
        "  ],\n" +
        "  \"motion\": \"Employ new CEO or change the employment agreement of current CEO\",\n" +
        "  \"startDate\": 1476525024497,\n" +
        "  \"endDate\": 1477129824497,\n" +
        "  \"group\": [\n" +
        "    {\n" +
        "      \"groupMember\": {\n" +
        "        \"gravatarHash\": \"935898e6728ebf500286179394800122\",\n" +
        "        \"userCapabilities\": {\n" +
        "          \"level2UserEndDate\": null,\n" +
        "          \"premiumEndDate\": null,\n" +
        "          \"level2User\": false,\n" +
        "          \"partner\": false,\n" +
        "          \"premium\": false,\n" +
        "          \"locale\": null\n" +
        "        },\n" +
        "        \"username\": \"Alphabank\",\n" +
        "        \"id\": \"fe97f737-09b2-4a20-a2bd-1bb4c504a687\"\n" +
        "      },\n" +
        "      \"numberOfVoices\": 1000\n" +
        "    },\n" +
        "    {\n" +
        "      \"groupMember\": {\n" +
        "        \"gravatarHash\": \"ab879a8dfc27eea39eb4df0202e8ae8f\",\n" +
        "        \"userCapabilities\": {\n" +
        "          \"level2UserEndDate\": null,\n" +
        "          \"premiumEndDate\": null,\n" +
        "          \"level2User\": false,\n" +
        "          \"partner\": false,\n" +
        "          \"premium\": false,\n" +
        "          \"locale\": null\n" +
        "        },\n" +
        "        \"username\": \"Alphabanker\",\n" +
        "        \"id\": \"da678935-e66e-4c22-9feb-94284472e238\"\n" +
        "      },\n" +
        "      \"numberOfVoices\": 1000\n" +
        "    },\n" +
        "  ],\n" +
        "  \"castVotesPercentage\": 89.77467093,\n" +
        "  \"totalNumberOfCastVotes\": 96576,\n" +
        "  \"pollInitiator\": {\n" +
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
        "  \"resultExpireDate\": 1477734624497,\n" +
        "  \"id\": \"f1672fda-6689-414c-b68e-94477905e672\",\n" +
        "  \"type\": \"YES_NO\"\n" +
        "}";

    private Poll toTest;

    @Before
    public void setUp() throws Exception {
        toTest = gson.fromJson(JSON, Poll.class);
    }

    @Test
    public void getInitiatedPolls() throws Exception {

    }

    @Test
    public void getPolls() throws Exception {

    }

    @Test
    public void getAbstentionRule() throws Exception {
        assertEquals(Poll.AbstentionRule.COUNTS_AS_REFUSAL, toTest.getAbstentionRule());
    }

    @Test
    public void getApprovalVotesPercentage() throws Exception {
        assertEquals(0, toTest.getApprovalVotesPercentage(), 0.0001);
    }

    @Test
    public void getTotalNumberOfVoices() throws Exception {
        assertEquals(Integer.valueOf(107576), toTest.getTotalNumberOfVoices());
    }

    @Test
    public void getVotes() throws Exception {
        List<Vote> reference = gson.fromJson("[\n" +
        "  {\n" +
        "    \"voices\": 96576,\n" +
        "    \"voter\": {\n" +
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
        "    \"type\": \"NO\"\n" +
        "  }\n" +
        "]", new TypeToken<ArrayList<Vote>>() { }.getType());
        assertEquals(new HashSet<>(reference), new HashSet<>(toTest.getVotes()));
    }

    @Test
    public void getMotion() throws Exception {
        assertEquals("Employ new CEO or change the employment agreement of current CEO",
            toTest.getMotion());
    }

    @Test
    public void getStartDate() throws Exception {
        ZonedDateTime reference = ZonedDateTime.ofInstant(Instant.ofEpochMilli(1476525024497L),
            ZoneId.systemDefault());
        assertEquals(reference, toTest.getStartDate());
    }

    @Test
    public void getEndDate() throws Exception {
        ZonedDateTime reference = ZonedDateTime.ofInstant(Instant.ofEpochMilli(1477129824497L),
            ZoneId.systemDefault());
        assertEquals(reference, toTest.getEndDate());
    }

    @Test
    public void getGroup() throws Exception {
        List<VotingGroupMember> reference = gson.fromJson("[\n" +
        "    {\n" +
        "      \"groupMember\": {\n" +
        "        \"gravatarHash\": \"935898e6728ebf500286179394800122\",\n" +
        "        \"userCapabilities\": {\n" +
        "          \"level2UserEndDate\": null,\n" +
        "          \"premiumEndDate\": null,\n" +
        "          \"level2User\": false,\n" +
        "          \"partner\": false,\n" +
        "          \"premium\": false,\n" +
        "          \"locale\": null\n" +
        "        },\n" +
        "        \"username\": \"Alphabank\",\n" +
        "        \"id\": \"fe97f737-09b2-4a20-a2bd-1bb4c504a687\"\n" +
        "      },\n" +
        "      \"numberOfVoices\": 1000\n" +
        "    },\n" +
        "    {\n" +
        "      \"groupMember\": {\n" +
        "        \"gravatarHash\": \"ab879a8dfc27eea39eb4df0202e8ae8f\",\n" +
        "        \"userCapabilities\": {\n" +
        "          \"level2UserEndDate\": null,\n" +
        "          \"premiumEndDate\": null,\n" +
        "          \"level2User\": false,\n" +
        "          \"partner\": false,\n" +
        "          \"premium\": false,\n" +
        "          \"locale\": null\n" +
        "        },\n" +
        "        \"username\": \"Alphabanker\",\n" +
        "        \"id\": \"da678935-e66e-4c22-9feb-94284472e238\"\n" +
        "      },\n" +
        "      \"numberOfVoices\": 1000\n" +
        "    },\n" +
        "  ]", new TypeToken<ArrayList<VotingGroupMember>>() { }.getType());
        assertEquals(new HashSet<>(reference), new HashSet<>(toTest.getGroup()));
    }

    @Test
    public void getCastVotesPercentage() throws Exception {
        assertEquals(89.77467093, toTest.getCastVotesPercentage(), 0.0001);
    }

    @Test
    public void getTotalNumberOfCastVotes() throws Exception {
        assertEquals(Integer.valueOf(96576), toTest.getTotalNumberOfCastVotes());
    }

    @Test
    public void getPollInitiator() throws Exception {
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
        assertEquals(reference, toTest.getPollInitiator());
    }

    @Test
    public void getResultExpireDate() throws Exception {
        ZonedDateTime reference = ZonedDateTime.ofInstant(Instant.ofEpochMilli(1477734624497L),
            ZoneId.systemDefault());
        assertEquals(reference, toTest.getResultExpireDate());
    }

    @Test
    public void getId() throws Exception {
        assertEquals("f1672fda-6689-414c-b68e-94477905e672", toTest.getId());
    }

    @Test
    public void getType() throws Exception {
        assertEquals(Poll.Type.YES_NO, toTest.getType());
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

        Poll other = gson.fromJson("{\n" +
            "  \"id\": \"12345\"\n" +
            "}", Poll.class);

        assertFalse(toTest.equals(other));
    }

    @Test
    public void testHashCode() throws Exception {
        Poll reference = gson.fromJson(JSON, Poll.class);
        assertEquals(reference.hashCode(), toTest.hashCode());
    }
}