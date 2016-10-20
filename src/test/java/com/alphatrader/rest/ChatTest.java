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
 * Test case for the {@link Chat} class.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0.0
 */
public class ChatTest {
    private static HttpResponder httpResponder = HttpResponder.getInstance();
    private static final Gson gson = new GsonBuilder().registerTypeAdapter(ZonedDateTime.class,
        new ZonedDateTimeDeserializer()).create();

    private static final String JSON = "{\n" +
        "  \"dateCreated\": 1475153983964,\n" +
        "  \"lastMessage\": {\n" +
        "    \"dateSent\": 1475154250577,\n" +
        "    \"chatId\": \"959e870f-e6f9-48fd-960d-3bcabfda2089\",\n" +
        "    \"sender\": {\n" +
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
        "    \"read\": true,\n" +
        "    \"id\": \"e34f9a86-ed1b-4d1f-b057-bd095929f75e\",\n" +
        "    \"content\": \"np\"\n" +
        "  },\n" +
        "  \"participants\": [\n" +
        "    {\n" +
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
        "    {\n" +
        "      \"gravatarHash\": \"11594bc5f6299ebf37a400c6b5c0b38c\",\n" +
        "      \"userCapabilities\": {\n" +
        "        \"level2UserEndDate\": null,\n" +
        "        \"premiumEndDate\": null,\n" +
        "        \"level2User\": false,\n" +
        "        \"partner\": false,\n" +
        "        \"premium\": false,\n" +
        "        \"locale\": null\n" +
        "      },\n" +
        "      \"username\": \"Kalle\",\n" +
        "      \"id\": \"0e9468b8-6e72-4382-91d7-c94d34d41df3\"\n" +
        "    }\n" +
        "  ],\n" +
        "  \"readonly\": false,\n" +
        "  \"chatName\": \"ChatName\",\n" +
        "  \"owner\": {\n" +
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
        "  \"id\": \"959e870f-e6f9-48fd-960d-3bcabfda2089\"\n" +
        "}";

    private Chat toTest;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        Http.setInstance(httpResponder.getMock());
    }

    @Before
    public void setUp() throws Exception {
        toTest = gson.fromJson(JSON, Chat.class);
    }

    @Test
    public void testGetAllChatsForThisUser() throws Exception {
        List<Chat> reference = gson.fromJson(httpResponder.getJsonForRequest("/api/chats"),
            new TypeToken<ArrayList<Chat>>() { }.getType());
        List<Chat> testObject = Chat.getAllChatsForThisUser();
        assertNotEquals(0, testObject.size());
        assertEquals(new HashSet<>(reference), new HashSet<>(testObject));
    }

    @Test
    public void testGetAllUnreadChatsForThisUser() throws Exception {
        List<Chat> reference = gson.fromJson(httpResponder.getJsonForRequest("/api/chats/unread"),
            new TypeToken<ArrayList<Chat>>() { }.getType());
        List<Chat> testObject = Chat.getAllUnreadChatsForThisUser();
        assertNotEquals(0, testObject.size());
        assertEquals(new HashSet<>(reference), new HashSet<>(testObject));
    }

    @Test
    public void testGetChatById() throws Exception {
        Chat testObject = Chat.getChatById("959e870f-e6f9-48fd-960d-3bcabfda2089");
        Chat reference = gson.fromJson(httpResponder.getJsonForRequest(
            "/api/chats/959e870f-e6f9-48fd-960d-3bcabfda2089"),Chat.class);
        assertNotNull(testObject);
        assertEquals(reference, testObject);
    }

    @Test
    public void testGetDateCreated() throws Exception {
        ZonedDateTime reference = ZonedDateTime.ofInstant(Instant.ofEpochMilli(1475153983964L),
            ZoneId.systemDefault());

        assertEquals(reference, toTest.getDateCreated());
    }

    @Test
    public void testGetLastMessage() throws Exception {
        ChatMessage reference = gson.fromJson("{\n" +
        "  \"dateSent\": 1475154250577,\n" +
        "  \"chatId\": \"959e870f-e6f9-48fd-960d-3bcabfda2089\",\n" +
        "  \"sender\": {\n" +
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
        "  \"read\": true,\n" +
        "  \"id\": \"e34f9a86-ed1b-4d1f-b057-bd095929f75e\",\n" +
        "  \"content\": \"np\"\n" +
        "}", ChatMessage.class);
        assertEquals(reference, toTest.getLastMessage());
    }

    @Test
    public void testGetParticipants() throws Exception {
        List<User> reference = gson.fromJson("[\n" +
        "  {\n" +
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
        "  {\n" +
        "    \"gravatarHash\": \"11594bc5f6299ebf37a400c6b5c0b38c\",\n" +
        "    \"userCapabilities\": {\n" +
        "      \"level2UserEndDate\": null,\n" +
        "      \"premiumEndDate\": null,\n" +
        "      \"level2User\": false,\n" +
        "      \"partner\": false,\n" +
        "      \"premium\": false,\n" +
        "      \"locale\": null\n" +
        "    },\n" +
        "    \"username\": \"Kalle\",\n" +
        "    \"id\": \"0e9468b8-6e72-4382-91d7-c94d34d41df3\"\n" +
        "  }\n" +
        "]", new TypeToken<ArrayList<User>>() {}.getType());
        assertEquals(new HashSet<User>(reference), new HashSet<User>(toTest.getParticipants()));
    }

    @Test
    public void testGetReadonly() throws Exception {
        assertEquals(false, toTest.isReadonly());
    }

    @Test
    public void testGetChatName() throws Exception {
        assertEquals("ChatName", toTest.getChatName());
    }

    @Test
    public void testGetOwner() throws Exception {
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

        assertEquals(reference, toTest.getOwner());
    }

    @Test
    public void testGetId() throws Exception {
        assertEquals("959e870f-e6f9-48fd-960d-3bcabfda2089", toTest.getId());
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

        Chat other = gson.fromJson("{\n" +
            "  \"id\": \"12345\"\n" +
            "}", Chat.class);

        assertFalse(toTest.equals(other));
    }

    @Test
    public void testHashCode() throws Exception {
        Chat reference = gson.fromJson(JSON, Chat.class);
        assertEquals(reference.hashCode(), toTest.hashCode());
    }
}