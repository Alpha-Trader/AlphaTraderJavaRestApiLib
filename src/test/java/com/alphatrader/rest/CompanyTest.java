package com.alphatrader.rest;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Test case for the {@link Company} class.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0
 */
public class CompanyTest {
    private Company toTest;

    @Before
    public void setUp() throws Exception {
        toTest = new Company(
            "43986f13-edde-486c-9ef0-718b100a1949",
            "Katholische Kirche AG",
            "STK0F513",
            "57875cf3-de0a-48e4-a3bc-314d4550df12",
            3348544.74,
            1000
        );
    }

    @Test
    public void testCreateFromJson() throws Exception {
        assertNotNull(toTest);
    }

    @Test
    public void testGetName() throws Exception {
        assertEquals("Katholische Kirche AG", toTest.getName());
    }

    @Test
    public void testGetSecurityIdentifier() throws Exception {
        assertEquals("STK0F513", toTest.getSecurityIdentifier());
    }

    @Test
    public void testGetSecuritiesAccountId() throws Exception {
        assertEquals("57875cf3-de0a-48e4-a3bc-314d4550df12", toTest.getSecuritiesAccountId());
    }

    @Test
    public void testGetCash() throws Exception {
        assertEquals(3348544.74, toTest.getCash(), 0.0001);
    }

    @Test
    public void testGetId() throws Exception {
        assertEquals("43986f13-edde-486c-9ef0-718b100a1949", toTest.getId());
    }
}