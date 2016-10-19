package com.alphatrader.rest;

import com.alphatrader.rest.util.ZonedDateTimeDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Before;
import org.junit.Test;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.junit.Assert.*;

/**
 * Test case for the {@link ChatMessage} class.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0.0
 */
public class ChatMessageTest {
    private static final Gson gson = new GsonBuilder().registerTypeAdapter(ZonedDateTime.class,
        new ZonedDateTimeDeserializer()).create();

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

    @Before
    public void setUp() throws Exception {
        toTest = gson.fromJson(JSON, ChatMessage.class);
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

        ChatMessage other = gson.fromJson("{\n" +
            "  \"id\": \"12345\"\n" +
            "}", ChatMessage.class);

        assertFalse(toTest.equals(other));
    }

    @Test
    public void testHashCode() throws Exception {
        ChatMessage reference = gson.fromJson(JSON, ChatMessage.class);
        assertEquals(reference.hashCode(), toTest.hashCode());
    }
}