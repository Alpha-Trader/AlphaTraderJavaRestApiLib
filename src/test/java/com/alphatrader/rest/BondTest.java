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
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test case for the {@link Bond} class.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0
 */
public class BondTest {
    private static final String JSON = "{\n" +
        "    \"priceSpread\": {\n" +
        "      \"askSize\": 10,\n" +
        "      \"bidPrice\": null,\n" +
        "      \"askPrice\": 100,\n" +
        "      \"spreadAbs\": null,\n" +
        "      \"spreadPercent\": null,\n" +
        "      \"lastPrice\": {\n" +
        "        \"date\": 1476380754066,\n" +
        "        \"value\": 100\n" +
        "      },\n" +
        "      \"maxBidPrice\": 120,\n" +
        "      \"minAskPrice\": 80,\n" +
        "      \"bidSize\": null,\n" +
        "      \"date\": 1476380812858\n" +
        "    },\n" +
        "    \"issuer\": {\n" +
        "      \"securityIdentifier\": \"STSCA0F8\",\n" +
        "      \"listing\": {\n" +
        "        \"startDate\": 1470153913715,\n" +
        "        \"endDate\": null,\n" +
        "        \"securityIdentifier\": \"STSCA0F8\",\n" +
        "        \"name\": \"Steffen Inc.\",\n" +
        "        \"type\": \"STOCK\"\n" +
        "      },\n" +
        "      \"name\": \"Steffen Inc.\",\n" +
        "      \"id\": \"9a476535-4712-4e43-b597-c94d57a30abf\"\n" +
        "    },\n" +
        "    \"interestRate\": 2.05,\n" +
        "    \"volume\": 100000,\n" +
        "    \"listing\": {\n" +
        "      \"startDate\": 1476301249869,\n" +
        "      \"endDate\": null,\n" +
        "      \"securityIdentifier\": \"BOS34377\",\n" +
        "      \"name\": \"Steffen Inc. 2.0500% 13/10/2016\",\n" +
        "      \"type\": \"BOND\"\n" +
        "    },\n" +
        "    \"issueDate\": 1476301249868,\n" +
        "    \"maturityDate\": 1476387663798,\n" +
        "    \"faceValue\": 100,\n" +
        "    \"repurchaseListing\": {\n" +
        "      \"startDate\": 1476301249869,\n" +
        "      \"endDate\": null,\n" +
        "      \"securityIdentifier\": \"RES34377\",\n" +
        "      \"name\": \"Steffen Inc. 2.0500% 13/10/2016\",\n" +
        "      \"type\": \"REPO\"\n" +
        "    },\n" +
        "    \"name\": \"Steffen Inc. 2.0500% 13/10/2016\",\n" +
        "    \"id\": \"8213bee0-b64a-46b8-bb9b-99ea7f67991d\"\n" +
        "  }";

    private Bond toTest;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        HttpResponseFactory factory = new DefaultHttpResponseFactory();
        org.apache.http.HttpResponse response = factory.newHttpResponse(new BasicStatusLine(HttpVersion.HTTP_1_1, HttpStatus.SC_OK, null), null);
        response.setEntity(new StringEntity("[" + JSON + "]"));
        HttpResponse<JsonNode> httpResponse = new HttpResponse<JsonNode>(response, JsonNode.class);

        Http mockHttp = mock(Http.class);
        when(mockHttp.get(any(String.class))).thenReturn(httpResponse);
        Http.setInstance(mockHttp);
    }

    @Before
    public void setUp() throws Exception {
        toTest = Bond.createFromJson(JSON);
    }

    @Test
    public void testGetAllBonds() {
        List<Bond> bonds = Bond.getAllBonds();
        assertEquals(1, bonds.size());
        assertEquals(Bond.createFromJson(JSON), bonds.get(0));
    }

    @Test
    public void testCreateFromJson() throws Exception {
        assertNotNull(toTest);
    }

    @Test
    public void testGetName() throws Exception {
        assertEquals("Steffen Inc. 2.0500% 13/10/2016", toTest.getName());
    }

    @Test
    public void testGetVolume() throws Exception {
        assertEquals(100000, toTest.getVolume());
    }

    @Test
    public void testGetInterestRate() throws Exception {
        assertEquals(2.05, toTest.getInterestRate(), 0.0001);
    }

    @Test
    public void testGetFaceValue() throws Exception {
        assertEquals(100.0, toTest.getFaceValue(), 0.0001);
    }

    @Test
    public void testGetMaturityDate() throws Exception {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(1476387663798L), ZoneId.systemDefault());
        assertEquals(localDateTime, toTest.getMaturityDate());
    }
}