package com.alphatrader.rest;

import com.alphatrader.rest.util.ZonedDateTimeDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Test case for the {@link Partner} class.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0.0
 */
public class PartnerTest {
    private static HttpResponder httpResponder = HttpResponder.getInstance();
    private static final Gson gson = new GsonBuilder().registerTypeAdapter(ZonedDateTime.class,
        new ZonedDateTimeDeserializer()).create();

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        Http.setInstance(httpResponder.getMock());
    }
    @Test
    public void getAllPartners() throws Exception {
        List<User> reference = gson.fromJson(httpResponder.getJsonForRequest("/api/partners/"),
            new TypeToken<ArrayList<User>>() { }.getType());
        List<User> testObject = Partner.getAllPartners();
        assertNotEquals(0, testObject.size());
        assertEquals(new HashSet<>(reference), new HashSet<>(testObject));
    }

    @Test
    public void testConstructorIsPrivate() throws Exception {
        Constructor<Partner> constructor = Partner.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        constructor.setAccessible(true);
        constructor.newInstance();
    }
}