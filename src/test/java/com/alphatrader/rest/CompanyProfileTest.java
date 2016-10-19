package com.alphatrader.rest;

import com.alphatrader.rest.util.ZonedDateTimeDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.junit.Before;
import org.junit.Test;

import java.net.URL;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Test case for the {@link CompanyProfile} class.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0.0
 */
public class CompanyProfileTest {
    private static final Gson gson = new GsonBuilder().registerTypeAdapter(ZonedDateTime.class,
        new ZonedDateTimeDeserializer()).create();

    private static final String JSON = "{\n" +
        "  \"ceoEmploymentAgreement\": {\n" +
        "    \"dailyWage\": 30000,\n" +
        "    \"startDate\": 1475336107328,\n" +
        "    \"id\": \"7bdef87f-6f55-4a2e-9eb5-15559432d057\"\n" +
        "  },\n" +
        "  \"companyCapabilities\": {\n" +
        "    \"bankReady\": false,\n" +
        "    \"bank\": true,\n" +
        "    \"takenCentralBankLoans\": 11000000,\n" +
        "    \"designatedSponsor\": false,\n" +
        "    \"reserves\": 10000000,\n" +
        "    \"maxCentralBankLoans\": 11000000,\n" +
        "    \"netCash\": 1489797.09\n" +
        "  },\n" +
        "  \"issuedBonds\": [\n" +
        "    {\n" +
        "      \"priceSpread\": null,\n" +
        "      \"issuer\": {\n" +
        "        \"securityIdentifier\": \"STK0F513\",\n" +
        "        \"listing\": {\n" +
        "          \"startDate\": 1469951698361,\n" +
        "          \"endDate\": null,\n" +
        "          \"securityIdentifier\": \"STK0F513\",\n" +
        "          \"name\": \"Katholische Kirche AG\",\n" +
        "          \"type\": \"STOCK\"\n" +
        "        },\n" +
        "        \"name\": \"Katholische Kirche AG\",\n" +
        "        \"id\": \"81dcf5a1-b0b6-462a-a40c-e374619edc2f\"\n" +
        "      },\n" +
        "      \"interestRate\": 20,\n" +
        "      \"volume\": 10000000,\n" +
        "      \"listing\": {\n" +
        "        \"startDate\": 1475267497534,\n" +
        "        \"endDate\": null,\n" +
        "        \"securityIdentifier\": \"BOK8986B\",\n" +
        "        \"name\": \"Katholische Kirche AG 20.0000% 29/10/2016\",\n" +
        "        \"type\": \"BOND\"\n" +
        "      },\n" +
        "      \"issueDate\": 1475267497534,\n" +
        "      \"maturityDate\": 1477773157000,\n" +
        "      \"faceValue\": 100,\n" +
        "      \"repurchaseListing\": {\n" +
        "        \"startDate\": 1475267497534,\n" +
        "        \"endDate\": null,\n" +
        "        \"securityIdentifier\": \"REK8986B\",\n" +
        "        \"name\": \"Katholische Kirche AG 20.0000% 29/10/2016\",\n" +
        "        \"type\": \"REPO\"\n" +
        "      },\n" +
        "      \"name\": \"Katholische Kirche AG 20.0000% 29/10/2016\",\n" +
        "      \"id\": \"1e395cc4-b7e1-431b-8710-ceae653ec753\"\n" +
        "    }\n" +
        "  ],\n" +
        "  \"lastTrades\": [\n" +
        "    {\n" +
        "      \"buyerSecuritiesAccount\": \"57875cf3-de0a-48e4-a3bc-314d4550df12\",\n" +
        "      \"sellerSecuritiesAccount\": \"5822c2f7-8ae5-4ada-8a29-726901e5b5b0\",\n" +
        "      \"numberOfShares\": 25513,\n" +
        "      \"volume\": 510.26,\n" +
        "      \"securityIdentifier\": \"STQA98BF\",\n" +
        "      \"price\": 0.02,\n" +
        "      \"date\": 1476907251194,\n" +
        "      \"id\": \"95830544-33c4-48f0-85f9-4364eb5f4087\"\n" +
        "    },\n" +
        "    {\n" +
        "      \"buyerSecuritiesAccount\": \"57875cf3-de0a-48e4-a3bc-314d4550df12\",\n" +
        "      \"sellerSecuritiesAccount\": \"9c548900-112b-420c-85a7-74c07132d458\",\n" +
        "      \"numberOfShares\": 1,\n" +
        "      \"volume\": 0.02,\n" +
        "      \"securityIdentifier\": \"STS2AED3\",\n" +
        "      \"price\": 0.02,\n" +
        "      \"date\": 1476907292374,\n" +
        "      \"id\": \"667db69d-0c09-4b4a-aead-848e3fc93e4d\"\n" +
        "    },\n" +
        "    {\n" +
        "      \"buyerSecuritiesAccount\": \"57875cf3-de0a-48e4-a3bc-314d4550df12\",\n" +
        "      \"sellerSecuritiesAccount\": \"718d6632-1397-4197-bdd7-4904bfed8ff2\",\n" +
        "      \"numberOfShares\": 25513,\n" +
        "      \"volume\": 510.26,\n" +
        "      \"securityIdentifier\": \"STN1CA81\",\n" +
        "      \"price\": 0.02,\n" +
        "      \"date\": 1476907308392,\n" +
        "      \"id\": \"5e8ec4d9-d39c-4311-a00e-216ab80270fc\"\n" +
        "    }\n" +
        "  ],\n" +
        "  \"sponsoredListings\": [],\n" +
        "  \"currentSpread\": {\n" +
        "    \"askSize\": null,\n" +
        "    \"bidPrice\": 25,\n" +
        "    \"askPrice\": null,\n" +
        "    \"spreadAbs\": null,\n" +
        "    \"spreadPercent\": null,\n" +
        "    \"lastPrice\": {\n" +
        "      \"date\": 1476375715646,\n" +
        "      \"value\": 656.93\n" +
        "    },\n" +
        "    \"maxBidPrice\": 788.32,\n" +
        "    \"minAskPrice\": 25,\n" +
        "    \"bidSize\": 49701,\n" +
        "    \"date\": 1476375717202\n" +
        "  },\n" +
        "  \"designatedSponsors\": [],\n" +
        "  \"lastOrderLogEntry\": {\n" +
        "    \"buyerSecuritiesAccount\": \"57875cf3-de0a-48e4-a3bc-314d4550df12\",\n" +
        "    \"sellerSecuritiesAccount\": \"77fbda7c-5790-4074-87d1-126561cd9cf5\",\n" +
        "    \"numberOfShares\": 3,\n" +
        "    \"volume\": 1970.79,\n" +
        "    \"securityIdentifier\": \"STK0F513\",\n" +
        "    \"price\": 656.93,\n" +
        "    \"date\": 1476375715662,\n" +
        "    \"id\": \"156503bc-380c-4bf0-8905-3bcbbe0c2f70\"\n" +
        "  },\n" +
        "  \"lastPrice\": {\n" +
        "    \"date\": 1476375715646,\n" +
        "    \"value\": 656.93\n" +
        "  },\n" +
        "  \"outstandingShares\": 56947,\n" +
        "  \"prices14d\": [\n" +
        "    {\n" +
        "      \"date\": 1476116914348,\n" +
        "      \"value\": 25\n" +
        "    },\n" +
        "    {\n" +
        "      \"date\": 1476375706503,\n" +
        "      \"value\": 525.54\n" +
        "    },\n" +
        "    {\n" +
        "      \"date\": 1476375715623,\n" +
        "      \"value\": 656.93\n" +
        "    },\n" +
        "    {\n" +
        "      \"date\": 1476375715646,\n" +
        "      \"value\": 656.93\n" +
        "    }\n" +
        "  ],\n" +
        "  \"marketCap\": 37410192.71,\n" +
        "  \"securitiesAccountId\": \"57875cf3-de0a-48e4-a3bc-314d4550df12\",\n" +
        "  \"bankAccount\": {\n" +
        "    \"cash\": 13709807.09,\n" +
        "    \"id\": \"c4d789b2-5e74-4618-a62e-fedc37fc2e4f\"\n" +
        "  },\n" +
        "  \"logoUrl\": \"http://fs5.directupload.net/images/161015/ovahdq4p.jpg\",\n" +
        "  \"ceo\": {\n" +
        "    \"gravatarHash\": \"7b7f03e2a716b0efaf4ff8728ad070c3\",\n" +
        "    \"userCapabilities\": {\n" +
        "      \"level2UserEndDate\": null,\n" +
        "      \"premiumEndDate\": null,\n" +
        "      \"level2User\": false,\n" +
        "      \"partner\": true,\n" +
        "      \"premium\": false,\n" +
        "      \"locale\": null\n" +
        "    },\n" +
        "    \"username\": \"FauserneEist\",\n" +
        "    \"id\": \"43986f13-edde-486c-9ef0-718b100a1949\"\n" +
        "  },\n" +
        "  \"listing\": {\n" +
        "    \"startDate\": 1469951698361,\n" +
        "    \"endDate\": null,\n" +
        "    \"securityIdentifier\": \"STK0F513\",\n" +
        "    \"name\": \"Katholische Kirche AG\",\n" +
        "    \"type\": \"STOCK\"\n" +
        "  },\n" +
        "  \"name\": \"Katholische Kirche AG\",\n" +
        "  \"id\": \"81dcf5a1-b0b6-462a-a40c-e374619edc2f\"\n" +
        "}";
    private CompanyProfile toTest;

    @Before
    public void setUp() throws Exception {
        toTest = gson.fromJson(JSON, CompanyProfile.class);
    }

    @Test
    public void testGetByCompany() throws Exception {

    }

    @Test
    public void testGetByCompanyString() throws Exception {

    }

    @Test
    public void testGetCeoEmploymentAgreement() throws Exception {
        EmploymentAgreement reference = gson.fromJson("{\n" +
        "    \"dailyWage\": 30000,\n" +
        "    \"startDate\": 1475336107328,\n" +
        "    \"id\": \"7bdef87f-6f55-4a2e-9eb5-15559432d057\"\n" +
        "  }", EmploymentAgreement.class);
        assertEquals(reference, toTest.getCeoEmploymentAgreement());
    }

    @Test
    public void testGetCompanyCapabilities() throws Exception {
        CompanyCapabilities reference = gson.fromJson("{\n" +
        "    \"bankReady\": false,\n" +
        "    \"bank\": true,\n" +
        "    \"takenCentralBankLoans\": 11000000,\n" +
        "    \"designatedSponsor\": false,\n" +
        "    \"reserves\": 10000000,\n" +
        "    \"maxCentralBankLoans\": 11000000,\n" +
        "    \"netCash\": 1489797.09\n" +
        "  }", CompanyCapabilities.class);
        assertEquals(reference, toTest.getCompanyCapabilities());
    }

    @Test
    public void testGetIssuedBonds() throws Exception {
        List<Bond> reference = gson.fromJson("[\n" +
        "    {\n" +
        "      \"priceSpread\": null,\n" +
        "      \"issuer\": {\n" +
        "        \"securityIdentifier\": \"STK0F513\",\n" +
        "        \"listing\": {\n" +
        "          \"startDate\": 1469951698361,\n" +
        "          \"endDate\": null,\n" +
        "          \"securityIdentifier\": \"STK0F513\",\n" +
        "          \"name\": \"Katholische Kirche AG\",\n" +
        "          \"type\": \"STOCK\"\n" +
        "        },\n" +
        "        \"name\": \"Katholische Kirche AG\",\n" +
        "        \"id\": \"81dcf5a1-b0b6-462a-a40c-e374619edc2f\"\n" +
        "      },\n" +
        "      \"interestRate\": 20,\n" +
        "      \"volume\": 10000000,\n" +
        "      \"listing\": {\n" +
        "        \"startDate\": 1475267497534,\n" +
        "        \"endDate\": null,\n" +
        "        \"securityIdentifier\": \"BOK8986B\",\n" +
        "        \"name\": \"Katholische Kirche AG 20.0000% 29/10/2016\",\n" +
        "        \"type\": \"BOND\"\n" +
        "      },\n" +
        "      \"issueDate\": 1475267497534,\n" +
        "      \"maturityDate\": 1477773157000,\n" +
        "      \"faceValue\": 100,\n" +
        "      \"repurchaseListing\": {\n" +
        "        \"startDate\": 1475267497534,\n" +
        "        \"endDate\": null,\n" +
        "        \"securityIdentifier\": \"REK8986B\",\n" +
        "        \"name\": \"Katholische Kirche AG 20.0000% 29/10/2016\",\n" +
        "        \"type\": \"REPO\"\n" +
        "      },\n" +
        "      \"name\": \"Katholische Kirche AG 20.0000% 29/10/2016\",\n" +
        "      \"id\": \"1e395cc4-b7e1-431b-8710-ceae653ec753\"\n" +
        "    }\n" +
        "  ]", new TypeToken<ArrayList<Bond>>() {}.getType());
        assertEquals(new HashSet<Bond>(reference), new HashSet<Bond>(toTest.getIssuedBonds()));
    }

    @Test
    public void testGetLastTrades() throws Exception {
        List<SecurityOrderLog> reference = gson.fromJson("[\n" +
        "    {\n" +
        "      \"buyerSecuritiesAccount\": \"57875cf3-de0a-48e4-a3bc-314d4550df12\",\n" +
        "      \"sellerSecuritiesAccount\": \"5822c2f7-8ae5-4ada-8a29-726901e5b5b0\",\n" +
        "      \"numberOfShares\": 25513,\n" +
        "      \"volume\": 510.26,\n" +
        "      \"securityIdentifier\": \"STQA98BF\",\n" +
        "      \"price\": 0.02,\n" +
        "      \"date\": 1476907251194,\n" +
        "      \"id\": \"95830544-33c4-48f0-85f9-4364eb5f4087\"\n" +
        "    },\n" +
        "    {\n" +
        "      \"buyerSecuritiesAccount\": \"57875cf3-de0a-48e4-a3bc-314d4550df12\",\n" +
        "      \"sellerSecuritiesAccount\": \"9c548900-112b-420c-85a7-74c07132d458\",\n" +
        "      \"numberOfShares\": 1,\n" +
        "      \"volume\": 0.02,\n" +
        "      \"securityIdentifier\": \"STS2AED3\",\n" +
        "      \"price\": 0.02,\n" +
        "      \"date\": 1476907292374,\n" +
        "      \"id\": \"667db69d-0c09-4b4a-aead-848e3fc93e4d\"\n" +
        "    },\n" +
        "    {\n" +
        "      \"buyerSecuritiesAccount\": \"57875cf3-de0a-48e4-a3bc-314d4550df12\",\n" +
        "      \"sellerSecuritiesAccount\": \"718d6632-1397-4197-bdd7-4904bfed8ff2\",\n" +
        "      \"numberOfShares\": 25513,\n" +
        "      \"volume\": 510.26,\n" +
        "      \"securityIdentifier\": \"STN1CA81\",\n" +
        "      \"price\": 0.02,\n" +
        "      \"date\": 1476907308392,\n" +
        "      \"id\": \"5e8ec4d9-d39c-4311-a00e-216ab80270fc\"\n" +
        "    }\n" +
        "  ]", new TypeToken<ArrayList<SecurityOrderLog>>() {}.getType());
        assertEquals(new HashSet<SecurityOrderLog>(reference),
            new HashSet<SecurityOrderLog>(toTest.getLastTrades()));
    }

    @Test
    public void testGetSponsoredListings() throws Exception {
        List<SecuritySponsorship> reference = gson.fromJson("[]",
            new TypeToken<ArrayList<SecuritySponsorship>>() {}.getType());
        assertEquals(new HashSet<>(reference),
            new HashSet<SecuritySponsorship>(toTest.getSponsoredListings()));
    }

    @Test
    public void testGetCurrentSpread() throws Exception {
        PriceSpread reference = gson.fromJson("{\n" +
        "    \"askSize\": null,\n" +
        "    \"bidPrice\": 25,\n" +
        "    \"askPrice\": null,\n" +
        "    \"spreadAbs\": null,\n" +
        "    \"spreadPercent\": null,\n" +
        "    \"lastPrice\": {\n" +
        "      \"date\": 1476375715646,\n" +
        "      \"value\": 656.93\n" +
        "    },\n" +
        "    \"maxBidPrice\": 788.32,\n" +
        "    \"minAskPrice\": 25,\n" +
        "    \"bidSize\": 49701,\n" +
        "    \"date\": 1476375717202\n" +
        "  }", PriceSpread.class);
        assertEquals(reference, toTest.getCurrentSpread());
    }

    @Test
    public void testGetDesignatedSponsors() throws Exception {
        List<SecuritySponsorship> reference = gson.fromJson("[]", new TypeToken<ArrayList<SecuritySponsorship>>() {}.getType());
        assertEquals(new HashSet<SecuritySponsorship>(reference),
            new HashSet<SecuritySponsorship>(toTest.getDesignatedSponsors()));
    }

    @Test
    public void testGetLastOrderLogEntry() throws Exception {
        SecurityOrderLog reference = gson.fromJson("{\n" +
        "    \"buyerSecuritiesAccount\": \"57875cf3-de0a-48e4-a3bc-314d4550df12\",\n" +
        "    \"sellerSecuritiesAccount\": \"77fbda7c-5790-4074-87d1-126561cd9cf5\",\n" +
        "    \"numberOfShares\": 3,\n" +
        "    \"volume\": 1970.79,\n" +
        "    \"securityIdentifier\": \"STK0F513\",\n" +
        "    \"price\": 656.93,\n" +
        "    \"date\": 1476375715662,\n" +
        "    \"id\": \"156503bc-380c-4bf0-8905-3bcbbe0c2f70\"\n" +
        "  }", SecurityOrderLog.class);
        assertEquals(reference, toTest.getLastOrderLogEntry());
    }

    @Test
    public void testGetLastPrice() throws Exception {
        LastPrice reference = gson.fromJson("{\n" +
        "    \"date\": 1476375715646,\n" +
        "    \"value\": 656.93\n" +
        "  }", LastPrice.class);
        assertEquals(reference, toTest.getLastPrice());
    }

    @Test
    public void testGetOutstandingShares() throws Exception {
        Double reference = gson.fromJson("56947", Double.class);
        assertEquals(reference, toTest.getOutstandingShares(), 0.0001);
    }

    @Test
    public void testGetPrices14d() throws Exception {
        List<LastPrice> reference = gson.fromJson("[\n" +
        "    {\n" +
        "      \"date\": 1476116914348,\n" +
        "      \"value\": 25\n" +
        "    },\n" +
        "    {\n" +
        "      \"date\": 1476375706503,\n" +
        "      \"value\": 525.54\n" +
        "    },\n" +
        "    {\n" +
        "      \"date\": 1476375715623,\n" +
        "      \"value\": 656.93\n" +
        "    },\n" +
        "    {\n" +
        "      \"date\": 1476375715646,\n" +
        "      \"value\": 656.93\n" +
        "    }\n" +
        "  ]", new TypeToken<ArrayList<LastPrice>>() {}.getType());
        assertEquals(new HashSet<LastPrice>(reference),
            new HashSet<LastPrice>(toTest.getPrices14d()));
    }

    @Test
    public void testGetMarketCap() throws Exception {
        assertEquals(37410192.71, toTest.getMarketCap(), 0.0001);
    }

    @Test
    public void testGetSecuritiesAccountId() throws Exception {
        assertEquals("57875cf3-de0a-48e4-a3bc-314d4550df12", toTest.getSecuritiesAccountId());
    }

    @Test
    public void testGetBankAccount() throws Exception {
        BankAccount reference = gson.fromJson("{\n" +
        "    \"cash\": 13709807.09,\n" +
        "    \"id\": \"c4d789b2-5e74-4618-a62e-fedc37fc2e4f\"\n" +
        "  }", BankAccount.class);
        assertEquals(reference, toTest.getBankAccount());
    }

    @Test
    public void testGetLogoUrl() throws Exception {
        URL reference = gson.fromJson("\"http://fs5.directupload.net/images/161015/ovahdq4p.jpg\"", URL.class);
        assertEquals(reference, toTest.getLogoUrl());
    }

    @Test
    public void testGetCeo() throws Exception {
        User reference = gson.fromJson("{\n" +
        "    \"gravatarHash\": \"7b7f03e2a716b0efaf4ff8728ad070c3\",\n" +
        "    \"userCapabilities\": {\n" +
        "      \"level2UserEndDate\": null,\n" +
        "      \"premiumEndDate\": null,\n" +
        "      \"level2User\": false,\n" +
        "      \"partner\": true,\n" +
        "      \"premium\": false,\n" +
        "      \"locale\": null\n" +
        "    },\n" +
        "    \"username\": \"FauserneEist\",\n" +
        "    \"id\": \"43986f13-edde-486c-9ef0-718b100a1949\"\n" +
        "  }", User.class);
        assertEquals(reference, toTest.getCeo());
    }

    @Test
    public void testGetListing() throws Exception {
        Listing reference = gson.fromJson("{\n" +
        "    \"startDate\": 1469951698361,\n" +
        "    \"endDate\": null,\n" +
        "    \"securityIdentifier\": \"STK0F513\",\n" +
        "    \"name\": \"Katholische Kirche AG\",\n" +
        "    \"type\": \"STOCK\"\n" +
        "  }", Listing.class);
        assertEquals(reference, toTest.getListing());
    }

    @Test
    public void testGetName() throws Exception {
        assertEquals("Katholische Kirche AG", toTest.getName());
    }

    @Test
    public void testGetId() throws Exception {
        assertEquals("81dcf5a1-b0b6-462a-a40c-e374619edc2f", toTest.getId());
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

        CompanyProfile other = gson.fromJson("{\n" +
            "  \"id\": \"12345\"\n" +
            "}", CompanyProfile.class);

        assertFalse(toTest.equals(other));
    }

    @Test
    public void testHashCode() throws Exception {
        CompanyProfile reference = gson.fromJson(JSON, CompanyProfile.class);
        assertEquals(reference.hashCode(), toTest.hashCode());
    }
}