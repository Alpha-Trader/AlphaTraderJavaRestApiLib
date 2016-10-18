package com.alphatrader.rest;

import com.alphatrader.rest.util.LocalDateTimeDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;

/**
 * Represents a banking licence in the game.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0.0
 */
public class BankingLicense {
    /**
     * The logger for this class.
     */
    private static final Log log = LogFactory.getLog(BankingLicense.class);

    /**
     * The gson object mapper for this class.
     */
    private static final Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class,
        new LocalDateTimeDeserializer()).create();

    /**
     * The date the license has been issued.
     */
    private final LocalDateTime startDate;

    /**
     * The company this license enables banking functions for.
     */
    private final Company company;

    /**
     * The id of this banking license.
     */
    private final String id;

    /**
     * Creates a new banking license with the given parameters.
     *
     * @param id        the id of the license
     * @param company   the company this license belongs to
     * @param startDate the issue date of this license
     */
    public BankingLicense(String id, Company company, LocalDateTime startDate) {
        this.id = id;
        this.company = company;
        this.startDate = startDate;
    }

    /**
     * Fetches the banking license for the specified company from the API.
     *
     * @param companyId the company id to look for a banking license
     * @return the banking license or null if it doesn't exist.
     */
    public static BankingLicense getBankingLicenseOfCompany(String companyId) {
        BankingLicense myReturn = null;
        try {
            HttpResponse<JsonNode> response = Http.getInstance().get("/api/bankinglicense/?companyId="
                + companyId);

            if (response != null && response.getStatus() == 200) {
                myReturn = gson.fromJson(
                    response.getBody()
                        .getObject()
                        .toString(),
                    BankingLicense.class);
            }
        }
        catch (UnirestException ue) {
            log.error("Error loading banking license: " + ue.getMessage());
            StringWriter stringWriter = new StringWriter();
            ue.printStackTrace(new PrintWriter(stringWriter));
            log.debug(stringWriter.toString());
        }

        return myReturn;
    }

    /**
     * Fetches the banking license for the specified company from the API.
     *
     * @param company the company to look for a banking license
     * @return the banking license or null if it doesn't exist.
     */
    public static BankingLicense getBankingLicenseOfCompany(Company company) {
        return getBankingLicenseOfCompany(company.getId());
    }

    /**
     * Fetches the banking license for the specified license id from the API.
     *
     * @param licenseId the license id to look for a banking license
     * @return the banking license or null if it doesn't exist.
     */
    public static BankingLicense getBankingLicenseById(String licenseId) {
        BankingLicense myReturn = null;
        try {
            HttpResponse<JsonNode> response = Http.getInstance().get("/api/bankinglicense/"
                + licenseId);

            if (response != null && response.getStatus() == 200) {
                myReturn = gson.fromJson(
                    response.getBody()
                        .getObject()
                        .toString(),
                    BankingLicense.class);
            }
        }
        catch (UnirestException ue) {
            log.error("Error loading banking license: " + ue.getMessage());
            StringWriter stringWriter = new StringWriter();
            ue.printStackTrace(new PrintWriter(stringWriter));
            log.debug(stringWriter.toString());
        }

        return myReturn;
    }

    /**
     * @return the issue date of this license
     */
    public LocalDateTime getStartDate() {
        return startDate;
    }

    /**
     * @return the company this license belongs to
     */
    public Company getCompany() {
        return company;
    }

    /**
     * @return the id of this license
     */
    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "BankingLicense{"
            + "startDate=" + startDate
            + ", company=" + company
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

        BankingLicense that = (BankingLicense) o;

        if (startDate != null ? !startDate.equals(that.startDate) : that.startDate != null) {
            return false;
        }
        if (company != null ? !company.equals(that.company) : that.company != null) {
            return false;
        }
        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        int result = startDate != null ? startDate.hashCode() : 0;
        result = 31 * result + (company != null ? company.hashCode() : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        return result;
    }
}
