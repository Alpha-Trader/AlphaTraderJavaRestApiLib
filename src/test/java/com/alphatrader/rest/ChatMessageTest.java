package com.alphatrader.rest;

import com.alphatrader.rest.util.PropertyGson;
import com.google.gson.Gson;
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
 * Test case for the {@link ChatMessage} class.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0.0
 */
public class ChatMessageTest {
    private static HttpResponder httpResponder = HttpResponder.getInstance();
    private static final Gson gson = new PropertyGson().create();

    private static final String JSON = "{\n" +
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
        "}";

    private ChatMessage toTest;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        Http.setInstance(httpResponder.getMock());
    }

    @Before
    public void setUp() throws Exception {
        toTest = gson.fromJson(JSON, ChatMessage.class);
    }


    @Test
    public void getChatMessages() throws Exception {
        Chat chat = gson.fromJson(httpResponder.getJsonForRequest(
            "/api/chats/959e870f-e6f9-48fd-960d-3bcabfda2089"), Chat.class);
        List<ChatMessage> reference = gson.fromJson(httpResponder.getJsonForRequest(
            "/api/messages/chat/959e870f-e6f9-48fd-960d-3bcabfda2089"),
            new TypeToken<ArrayList<ChatMessage>>() { }.getType());
        List<ChatMessage> testObject = ChatMessage.getChatMessages(chat);
        assertNotEquals(0, testObject.size());
        assertEquals(new HashSet<>(reference), new HashSet<>(testObject));
    }

    @Test
    public void getChatMessages1() throws Exception {
        ZonedDateTime date = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault());
        Chat chat = gson.fromJson(httpResponder.getJsonForRequest(
            "/api/chats/959e870f-e6f9-48fd-960d-3bcabfda2089"), Chat.class);
        List<ChatMessage> reference = gson.fromJson(httpResponder.getJsonForRequest(
            "/api/messages/chat/959e870f-e6f9-48fd-960d-3bcabfda2089?afterDate=0"),
            new TypeToken<ArrayList<ChatMessage>>() { }.getType());

        List<ChatMessage> testObject = ChatMessage.getChatMessages(chat, date);
        assertNotEquals(0, testObject.size());
        assertEquals(new HashSet<>(reference), new HashSet<>(testObject));
    }

    @Test
    public void getChatMessages2() throws Exception {
        ZonedDateTime date = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault());
        Chat chat = gson.fromJson(httpResponder.getJsonForRequest(
            "/api/chats/959e870f-e6f9-48fd-960d-3bcabfda2089"), Chat.class);
        List<ChatMessage> reference = gson.fromJson(httpResponder.getJsonForRequest(
            "/api/messages/chat/959e870f-e6f9-48fd-960d-3bcabfda2089?afterDate=0&beforeDate=0"),
            new TypeToken<ArrayList<ChatMessage>>() { }.getType());
        List<ChatMessage> testObject = ChatMessage.getChatMessages(chat, date, date);
        assertNotEquals(0, testObject.size());
        assertEquals(new HashSet<>(reference), new HashSet<>(testObject));    }


    @Test
    public void getById() throws Exception {
        ChatMessage reference = gson.fromJson(httpResponder.getJsonForRequest(
            "/api/messages/61fd4b1d-226b-4a66-97ef-0f404c6b7f40"), ChatMessage.class);
        ChatMessage testObject = ChatMessage.getById("61fd4b1d-226b-4a66-97ef-0f404c6b7f40");
        assertNotNull(testObject);
        assertEquals(reference, testObject);
    }

    @Test
    public void getUnreadMessages() throws Exception {
       List<ChatMessage> reference = gson.fromJson(httpResponder.getJsonForRequest(
            "/api/messages/unread"),
            new TypeToken<ArrayList<ChatMessage>>() { }.getType());
        List<ChatMessage> testObject = ChatMessage.getUnreadMessages();
        assertNotEquals(0, testObject.size());
        assertEquals(new HashSet<>(reference), new HashSet<>(testObject));
    }

    @Test
    public void getDateSent() throws Exception {
        ZonedDateTime reference = ZonedDateTime.ofInstant(Instant.ofEpochMilli(1475154250577L),
            ZoneId.systemDefault());

        assertEquals(reference, toTest.getDateSent());
    }

    @Test
    public void getChatId() throws Exception {
        assertEquals("959e870f-e6f9-48fd-960d-3bcabfda2089", toTest.getChatId());
    }

    @Test
    public void getSender() throws Exception {
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

        assertEquals(reference, toTest.getSender());
    }

    @Test
    public void isRead() throws Exception {
        assertEquals(true, toTest.isRead());
    }

    @Test
    public void getId() throws Exception {
        assertEquals("e34f9a86-ed1b-4d1f-b057-bd095929f75e", toTest.getId());
    }

    @Test
    public void getContent() throws Exception {
        assertEquals("np", toTest.getContent());
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
        ChatMessage reference = gson.fromJson(JSON, ChatMessage.class);
        assertEquals(reference.hashCode(), toTest.hashCode());
    }
}