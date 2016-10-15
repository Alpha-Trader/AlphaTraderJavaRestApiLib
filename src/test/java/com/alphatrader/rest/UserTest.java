package com.alphatrader.rest;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Testcase for the {@link User} class.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0
 */
public class UserTest {
    private User toTest;

    @Before
    public void setUp() throws Exception {
        toTest = new User("test", "pw");
    }

    @Test
    public void testGetName() throws Exception {
        assertEquals("test", toTest.getName());
    }

    @Test
    public void testGetPassword() throws Exception {
        assertEquals("pw", toTest.getPassword());
    }

    @Test
    public void testGetToken() throws Exception {
        assertNull(toTest.getToken());
    }

}