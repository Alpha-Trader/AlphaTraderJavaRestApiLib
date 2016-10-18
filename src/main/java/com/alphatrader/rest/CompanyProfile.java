package com.alphatrader.rest;

import com.alphatrader.rest.util.LocalDateTimeDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jetbrains.annotations.Nullable;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * Represents a company profile in the game.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0.0
 */
public class CompanyProfile {
    /**
     * The logger for this class. Use this to write messages to the console.
     */
    private static final Log log = LogFactory.getLog(CompanyProfile.class);

    /**
     * Gson instance for deserialization.
     */
    private static final Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class,
        new LocalDateTimeDeserializer()).create();

    /**
     * The ceo employment agreement.
     */
    private final EmploymentAgreement ceoEmploymentAgreement = null;

    /**
     * The company capabilities.
     */
    private final CompanyCapabilities companyCapabilities = null;

    /**
     * The issued bonds of this company.
     */
    private final Bond[] issuedBonds = null;

    /**
     * The last trades made by this company.
     */
    private final SecurityOrderLog[] lastTrades = null;

    /**
     * The sponsored listings.
     */
    //TODO: find out more
    private final Object[] sponsoredListings = null;

    /**
     * The current price spread.
     */
    private final PriceSpread currentSpread = null;

    /**
     * The designated sponsors of this company.
     */
    private final Company[] designatedSponsors = null;

    /**
     * The last order of this company.
     */
    private final SecurityOrderLog lastOrderLogEntry = null;

    /**
     * The last trading price of this company.
     */
    private final LastPrice lastPrice = null;

    /**
     * The number of outstanding shares.
     */
    private final Integer outstandingShares = null;

    /**
     * The trading prices of the company for the last 14 days.
     */
    private final LastPrice[] prices14d = null;

    /**
     * The current market cap for the company.
     */
    private final Double marketCap = null;

    /**
     * The company's securities account id.
     */
    private final String securitiesAccountId = null;

    /**
     * The company's bank account.
     */
    private final BankAccount bankAccount = null;

    /**
     * The URL of the company logo.
     */
    private final URL logoUrl = null;

    /**
     * The CEO of the company.
     */
    private final User ceo = null;

    /**
     * The company listing.
     */
    private final Listing listing = null;

    /**
     * The company name.
     */
    private final String name = null;

    /**
     * The unique id.
     */
    private final String id = null;

    /**
     * Returns the profile of the given company.
     *
     * @param company the company
     * @return the CompanyProfile requested or null of not found
     */
    @Nullable
    public static CompanyProfile getByCompany(Company company) {
        return getByCompany(company.getId());
    }

    /**
     * Returns the profile of the given company.
     *
     * @param companyId the company id
     * @return the CompanyProfile requested or null of not found
     */
    @Nullable
    public static CompanyProfile getByCompany(String companyId) {
        CompanyProfile myReturn = null;

        try {
            HttpResponse<JsonNode> response = Http.getInstance().get("/api/companyprofiles/"
                + companyId);

            if (response != null && response.getStatus() == 200) {
                myReturn = gson.fromJson(response.getBody()
                    .getObject().toString(), CompanyProfile.class);
            }
        }
        catch (UnirestException ue) {
            log.error("Error fetching employment agreement: " + ue.getMessage());
            StringWriter stringWriter = new StringWriter();
            ue.printStackTrace(new PrintWriter(stringWriter));
            log.debug(stringWriter.toString());
        }

        return myReturn;
    }

    /**
     * @return the current CEO employment agreement
     */
    public EmploymentAgreement getCeoEmploymentAgreement() {
        return ceoEmploymentAgreement;
    }

    /**
     * @return the company capabilities
     */
    public CompanyCapabilities getCompanyCapabilities() {
        return companyCapabilities;
    }

    /**
     * @return the bonds issued by this company
     */
    public List<Bond> getIssuedBonds() {
        return Arrays.asList(issuedBonds);
    }

    /**
     * @return the last trades
     */
    public List<SecurityOrderLog> getLastTrades() {
        return Arrays.asList(lastTrades);
    }

    /**
     * @return the sponsored listings
     */
    public List<Object> getSponsoredListings() {
        return Arrays.asList(sponsoredListings);
    }

    /**
     * @return the current price spread
     */
    public PriceSpread getCurrentSpread() {
        return currentSpread;
    }

    /**
     * @return the designated sponsors of this company
     */
    public List<Company> getDesignatedSponsors() {
        return Arrays.asList(designatedSponsors);
    }

    /**
     * @return the last order of this company
     */
    public SecurityOrderLog getLastOrderLogEntry() {
        return lastOrderLogEntry;
    }

    /**
     * @return the last price of this company
     */
    public LastPrice getLastPrice() {
        return lastPrice;
    }

    /**
     * @return the number of outstanding shares
     */
    public Integer getOutstandingShares() {
        return outstandingShares;
    }

    /**
     * @return the prices of this company for the last 14 days
     */
    public List<LastPrice> getPrices14d() {
        return Arrays.asList(prices14d);
    }

    /**
     * @return the market cap
     */
    public Double getMarketCap() {
        return marketCap;
    }

    /**
     * @return the securities account id
     */
    public String getSecuritiesAccountId() {
        return securitiesAccountId;
    }

    /**
     * @return the bank account
     */
    public BankAccount getBankAccount() {
        return bankAccount;
    }

    /**
     * @return the company logo URL
     */
    public URL getLogoUrl() {
        return logoUrl;
    }

    /**
     * @return the current CEO
     */
    public User getCeo() {
        return ceo;
    }

    /**
     * @return the company listing
     */
    public Listing getListing() {
        return listing;
    }

    /**
     * @return the company name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the company id
     */
    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "CompanyProfile{"
            + "ceoEmploymentAgreement=" + ceoEmploymentAgreement
            + ", companyCapabilities=" + companyCapabilities
            + ", issuedBonds=" + Arrays.toString(issuedBonds)
            + ", lastTrades=" + Arrays.toString(lastTrades)
            + ", sponsoredListings=" + Arrays.toString(sponsoredListings)
            + ", currentSpread=" + currentSpread
            + ", designatedSponsors=" + Arrays.toString(designatedSponsors)
            + ", lastOrderLogEntry=" + lastOrderLogEntry
            + ", lastPrice=" + lastPrice
            + ", outstandingShares=" + outstandingShares
            + ", prices14d=" + Arrays.toString(prices14d)
            + ", marketCap=" + marketCap
            + ", securitiesAccountId='" + securitiesAccountId + '\''
            + ", bankAccount=" + bankAccount
            + ", logoUrl=" + logoUrl
            + ", ceo=" + ceo
            + ", listing=" + listing
            + ", name='" + name + '\''
            + ", id='" + id + '\''
            + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CompanyProfile that = (CompanyProfile) o;

        return id != null ? id.equals(that.id) : that.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
