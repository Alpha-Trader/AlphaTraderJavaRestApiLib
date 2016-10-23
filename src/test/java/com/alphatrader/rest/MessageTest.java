package com.alphatrader.rest;

import com.alphatrader.rest.util.ZonedDateTimeDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Test case for the {@link Message} class.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0.0
 */
public class MessageTest {
    private static HttpResponder httpResponder = HttpResponder.getInstance();
    private static final Gson gson = new GsonBuilder().registerTypeAdapter(ZonedDateTime.class,
        new ZonedDateTimeDeserializer()).create();

    private static final String JSON = "{\n" +
        "  \"message\": \"Order filled1\",\n" +
        "  \"substitutions\": [],\n" +
        "  \"filledString\": \"Order filled2\"\n" +
        "}";

    private Message toTest;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        Http.setInstance(httpResponder.getMock());
    }

    @Before
    public void setUp() throws Exception {
        toTest = gson.fromJson(JSON, Message.class);
    }

    @Test
    public void getMessage() throws Exception {
        assertEquals("Order filled1", toTest.getMessage());
    }

    @Test
    public void getSubstitutions() throws Exception {
        List<String> reference = gson.fromJson("[]", new TypeToken<ArrayList<String>>() { }.getType());
        assertEquals(new HashSet<>(reference), new HashSet<String>(toTest.getSubstitutions()));
    }

    @Test
    public void getFilledString() throws Exception {
        assertEquals("Order filled2", toTest.getFilledString());
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

        Message other = gson.fromJson("{\n" +
            "  \"id\": \"12345\"\n" +
            "}", Message.class);

        assertFalse(toTest.equals(other));
    }

    @Test
    public void testHashCode() throws Exception {
        Message reference = gson.fromJson(JSON, Message.class);
        assertEquals(reference.hashCode(), toTest.hashCode());
    }
}