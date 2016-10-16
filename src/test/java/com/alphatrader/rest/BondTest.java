package com.alphatrader.rest;

import com.alphatrader.rest.util.Http;
import com.alphatrader.rest.util.LocalDateTimeDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test case for the {@link Bond} class.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0
 */
public class BondTest {
    private static final Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class,
        new LocalDateTimeDeserializer()).create();

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
        org.apache.http.HttpResponse responseArray = factory.newHttpResponse(new BasicStatusLine(
            HttpVersion.HTTP_1_1, HttpStatus.SC_OK, null), null);
        responseArray.setEntity(new StringEntity("[" + JSON + "]"));
        HttpResponse<JsonNode> httpResponseArray = new HttpResponse<>(responseArray, JsonNode.class);

        org.apache.http.HttpResponse responseSingle = factory.newHttpResponse(new BasicStatusLine(
            HttpVersion.HTTP_1_1, HttpStatus.SC_OK, null), null);
        responseSingle.setEntity(new StringEntity(JSON));
        HttpResponse<JsonNode> httpResponseSingle = new HttpResponse<>(responseSingle, JsonNode.class);

        Http mockHttp = mock(Http.class);
        when(mockHttp.get(eq("/api/bonds/"))).thenReturn(httpResponseArray);
        when(mockHttp.get(eq("/api/bonds/securityidentifier/BOS34377"))).thenReturn(httpResponseSingle);
        when(mockHttp.get(eq("/api/bonds/8213bee0-b64a-46b8-bb9b-99ea7f67991d")))
            .thenReturn(httpResponseSingle);
        Http.setInstance(mockHttp);
    }

    @Before
    public void setUp() throws Exception {
        toTest = gson.fromJson(JSON, Bond.class);
    }

    @Test
    public void testGetAllBonds() {
        List<Bond> bonds = Bond.getAllBonds();
        assertEquals(1, bonds.size());
        assertEquals(toTest, bonds.get(0));
    }

    @Test
    public void testGetBondById() {
        Bond bond = Bond.getBondById("8213bee0-b64a-46b8-bb9b-99ea7f67991d");
        assertEquals(toTest, bond);
    }

    @Test
    public void testGetBondBySecurityIdentifier() {
        Bond bond = Bond.getBondBySecurityIdentifier("BOS34377");
        assertEquals(toTest, bond);
    }

    @Test
    public void testGetName() throws Exception {
        assertEquals("Steffen Inc. 2.0500% 13/10/2016", toTest.getName());
    }

    @Test
    public void testGetId() throws Exception {
        assertEquals("8213bee0-b64a-46b8-bb9b-99ea7f67991d", toTest.getId());
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
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(1476387663798L),
            ZoneId.systemDefault());
        assertEquals(localDateTime, toTest.getMaturityDate());
    }

    @Test
    public void testGetIssueDate() throws Exception {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(1476301249868L),
            ZoneId.systemDefault());
        assertEquals(localDateTime, toTest.getIssueDate());
    }

    @Test
    public void testGetPriceSpread() throws Exception {
        String refJson = "{\n" +
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
        "    }";
        PriceSpread reference = gson.fromJson(refJson, PriceSpread.class);
        assertEquals(reference, toTest.getPriceSpread());
    }

    @Test
    public void testGetIssuer() throws Exception {
        String refJson = "{\n" +
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
        "    }";
        Company reference = gson.fromJson(refJson, Company.class);
        assertEquals(reference, toTest.getIssuer());
    }

    @Test
    public void testGetListing() throws Exception {
        String refJson = "{\n" +
        "      \"startDate\": 1476301249869,\n" +
        "      \"endDate\": null,\n" +
        "      \"securityIdentifier\": \"BOS34377\",\n" +
        "      \"name\": \"Steffen Inc. 2.0500% 13/10/2016\",\n" +
        "      \"type\": \"BOND\"\n" +
        "    }";
        Listing reference = gson.fromJson(refJson, Listing.class);
        assertEquals(reference, toTest.getListing());
    }

    @Test
    public void testGetRepurchaseListing() throws Exception {
        String refJson = "{\n" +
        "      \"startDate\": 1476301249869,\n" +
        "      \"endDate\": null,\n" +
        "      \"securityIdentifier\": \"RES34377\",\n" +
        "      \"name\": \"Steffen Inc. 2.0500% 13/10/2016\",\n" +
        "      \"type\": \"REPO\"\n" +
        "    }";
        Listing reference = gson.fromJson(refJson, Listing.class);
        assertEquals(reference, toTest.getRepurchaseListing());
    }
}