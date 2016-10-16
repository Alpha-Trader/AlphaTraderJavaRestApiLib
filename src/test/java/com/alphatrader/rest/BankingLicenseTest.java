package com.alphatrader.rest;

import com.alphatrader.rest.util.Http;
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

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.junit.Assert.*;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test case for the {@link BankingLicense} class.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0.0
 */
public class BankingLicenseTest {
    private final static String JSON = "{\n" +
        "  \"startDate\": 1475335931401,\n" +
        "  \"company\": {\n" +
        "    \"securitiesAccountId\": \"57875cf3-de0a-48e4-a3bc-314d4550df12\",\n" +
        "    \"securityIdentifier\": \"STK0F513\",\n" +
        "    \"ceo\": {\n" +
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
        "    \"name\": \"Katholische Kirche AG\",\n" +
        "    \"id\": \"81dcf5a1-b0b6-462a-a40c-e374619edc2f\"\n" +
        "  },\n" +
        "  \"id\": \"257e1004-8c5e-4d09-beca-8377e8be070c\"\n" +
        "}";

    private final static String NO_LICENSE = "{\n" +
        "  \"code\": 400,\n" +
        "  \"messagePrototype\": {\n" +
        "    \"message\": \"Banking license does not exist\",\n" +
        "    \"substitutions\": [],\n" +
        "    \"filledString\": \"Banking license does not exist\"\n" +
        "  },\n" +
        "  \"message\": \"Banking license does not exist\"\n" +
        "}";

    private static final LocalDateTime testDate = LocalDateTime.ofInstant(Instant.ofEpochMilli(1475335931401L),
        ZoneId.systemDefault());

    private static final Company testCompany = new Company(
        "81dcf5a1-b0b6-462a-a40c-e374619edc2f",
        "Katholische Kirche AG",
        "STK0F513",
        "57875cf3-de0a-48e4-a3bc-314d4550df12",
        0.0,
        0
    );

    private BankingLicense toTest;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        HttpResponseFactory factory = new DefaultHttpResponseFactory();
        org.apache.http.HttpResponse response = factory.newHttpResponse(new BasicStatusLine(
            HttpVersion.HTTP_1_1, HttpStatus.SC_OK, null), null);
        response.setEntity(new StringEntity(JSON));
        HttpResponse<JsonNode> httpResponse = new HttpResponse<>(response, JsonNode.class);

        org.apache.http.HttpResponse emptyResponse = factory.newHttpResponse(new BasicStatusLine(
            HttpVersion.HTTP_1_1, HttpStatus.SC_BAD_REQUEST, null), null);
        emptyResponse.setEntity(new StringEntity(NO_LICENSE));
        HttpResponse<JsonNode> emptyHttpResponse = new HttpResponse<>(emptyResponse, JsonNode.class);

        Http mockHttp = mock(Http.class);
        when(mockHttp.get(eq("/api/bankinglicense/?companyId=99cfe1b7-8bd5-42eb-aec7-8a98d7a3d63d")))
            .thenReturn(emptyHttpResponse);
        when(mockHttp.get(eq("/api/bankinglicense/?companyId=81dcf5a1-b0b6-462a-a40c-e374619edc2f")))
            .thenReturn(httpResponse);
        when(mockHttp.get(eq("/api/bankinglicense/257e1004-8c5e-4d09-beca-8377e8be070c")))
            .thenReturn(httpResponse);
        Http.setInstance(mockHttp);
    }

    @Before
    public void setUp() throws Exception {
        toTest = new BankingLicense(
            "257e1004-8c5e-4d09-beca-8377e8be070c",
            testCompany,
            testDate
        );
    }

    @Test
    public void testGetStartDate() throws Exception {
        assertEquals(testDate, toTest.getStartDate());
    }

    @Test
    public void testGetCompany() throws Exception {
        assertEquals(testCompany, toTest.getCompany());
    }

    @Test
    public void testGetId() throws Exception {
        assertEquals("257e1004-8c5e-4d09-beca-8377e8be070c", toTest.getId());
    }

    @Test
    public void testToString() throws Exception {
        System.out.println(toTest);
    }

    @Test
    public void testGetById() throws Exception {
        assertEquals(toTest, BankingLicense.getBankingLicenseById("257e1004-8c5e-4d09-beca-8377e8be070c"));
    }

    @Test
    public void testGetByCompany() throws Exception {
        assertEquals(toTest, BankingLicense.getBankingLicenseOfCompany(testCompany));
    }
}