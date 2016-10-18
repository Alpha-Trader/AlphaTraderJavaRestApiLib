package com.alphatrader.rest;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import org.apache.http.HttpResponseFactory;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.DefaultHttpResponseFactory;
import org.apache.http.message.BasicStatusLine;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test case for the {@link BankAccount} class.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0.0
 */
public class BankAccountTest {
    private static final String JSON = "{\n" +
        "  \"cash\": 10000,\n" +
        "  \"id\": \"443db0b7-c399-4252-8166-061d8de1d634\"\n" +
        "}";

    private BankAccount toTest;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        HttpResponseFactory factory = new DefaultHttpResponseFactory();
        org.apache.http.HttpResponse response = factory.newHttpResponse(new BasicStatusLine(HttpVersion.HTTP_1_1, HttpStatus.SC_OK, null), null);
        response.setEntity(new StringEntity(JSON));
        HttpResponse<JsonNode> httpResponse = new HttpResponse<>(response, JsonNode.class);

        Http mockHttp = mock(Http.class);
        when(mockHttp.get(any(String.class))).thenReturn(httpResponse);
        Http.setInstance(mockHttp);
    }

    @Before
    public void setUp() throws Exception {
        toTest = new BankAccount("443db0b7-c399-4252-8166-061d8de1d634", 10000.0);
    }

    @Test
    public void testGetUserBankAccount() throws Exception {
        BankAccount testAccount = BankAccount.getUserBankAccount();
        assertEquals(toTest, testAccount);
    }

    @Test
    public void testGetCash() throws Exception {
        assertEquals(10000.0, toTest.getCash(), 0.0001);
    }

    @Test
    public void testGetId() throws Exception {
        assertEquals("443db0b7-c399-4252-8166-061d8de1d634", toTest.getId());
    }
}