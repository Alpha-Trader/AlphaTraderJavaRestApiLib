package com.alphatrader.rest;

import com.alphatrader.rest.util.PropertyGson;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Testcase for the {@link User} class.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0
 */
public class UserTest {
    private static HttpResponder httpResponder = HttpResponder.getInstance();
    private static final Gson gson = new PropertyGson().create();

    private User toTest;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        Http.setInstance(httpResponder.getMock());
    }

    @Before
    public void setUp() throws Exception {
        toTest = new User("test", "pw");
    }

    @Test
    public void getLoggedInUser() throws Exception {
        User reference = gson.fromJson(httpResponder.getJsonForRequest("/api/user"), User.class);
        User testObject = User.getLoggedInUser();
        assertNotNull(testObject);
        assertEquals(reference, testObject);
    }

    @Test
    public void getByUsername() throws Exception {
        User reference = gson.fromJson(httpResponder.getJsonForRequest(
            "/api/users/username/FauserneEist"), User.class);
        User testObject = User.getByUsername("FauserneEist");
        assertNotNull(testObject);
        assertEquals(reference, testObject);
    }

    @Test
    public void getById() throws Exception {
        User reference = gson.fromJson(httpResponder.getJsonForRequest(
            "/api/users/43986f13-edde-486c-9ef0-718b100a1949"), User.class);
        User testObject = User.getById("43986f13-edde-486c-9ef0-718b100a1949");
        assertNotNull(testObject);
        assertEquals(reference, testObject);
    }

    @Test
    public void searchUser() throws Exception {
        List<User> reference = gson.fromJson(httpResponder.getJsonForRequest("/api/search/users/Fauser"),
            new TypeToken<ArrayList<User>>() { }.getType());
        List<User> testObject = User.searchUser("Fauser");
        assertNotEquals(0, testObject.size());
        assertEquals(new HashSet<>(reference), new HashSet<>(testObject));
    }

    @Test
    public void getAllUsers() throws Exception {
        List<User> reference = gson.fromJson(httpResponder.getJsonForRequest("/api/users"),
            new TypeToken<ArrayList<User>>() { }.getType());
        List<User> testObject = User.getAllUsers();
        assertNotEquals(0, testObject.size());
        assertEquals(new HashSet<>(reference), new HashSet<>(testObject));
    }

    @Test
    public void login() throws Exception {
        toTest.login();
        assertNull(toTest.getToken());
    }

    @Test
    public void testGetName() throws Exception {
        assertEquals("test", toTest.getUsername());
    }

    @Test
    public void testGetPassword() throws Exception {
        assertEquals("pw", toTest.getPassword());
    }

    @Test
    public void getEmailAddress() throws Exception {
        assertNull(toTest.getEmailAddress());
    }

    @Test
    public void getGravatarHash() throws Exception {
        assertNull(toTest.getGravatarHash());
    }

    @Test
    public void getCapabilities() throws Exception {
        assertNull(toTest.getUserCapabilities());
    }
}