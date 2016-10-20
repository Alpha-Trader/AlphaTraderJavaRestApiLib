package com.alphatrader.rest.util;

import com.alphatrader.rest.User;
import org.junit.Before;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.*;

/**
 * Test case for the {@link ApiLibConfig} class.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0.0
 */
public class ApiLibConfigTest {
    private ApiLibConfig toTest;

    @Before
    public void setUp() throws Exception {
        toTest = ApiLibConfig.getInstance();
    }

    @Test
    public void testGetInstance() throws Exception {
        assertEquals(toTest, ApiLibConfig.getInstance());
    }

    @Test
    public void testGetSetApiUrl() throws Exception {
        URL testUrl = new URL("http://example.org");
        toTest.setApiUrl(testUrl);
        assertEquals(testUrl, toTest.getApiUrl());
    }

    @Test
    public void testGetSetApiUrl1() throws Exception {
        URL testUrl = new URL("http://example.org");
        toTest.setApiUrl(testUrl.toString());
        assertEquals(testUrl, toTest.getApiUrl());
    }

    @Test
    public void testGetSetApiUrl2() throws Exception {
        toTest.setApiUrl("malformed-url");
        assertNotNull(toTest.getApiUrl());
    }

    @Test
    public void testGetSetUser() throws Exception {
        User testUser = new User("Test", "PW");
        toTest.setUser(testUser);
        assertEquals(testUser, toTest.getUser());
    }

    @Test
    public void testGetSetPartnerId() throws Exception {
        String partnerId = "12345";
        toTest.setPartnerId(partnerId);
        assertEquals(partnerId, toTest.getPartnerId());
    }
}