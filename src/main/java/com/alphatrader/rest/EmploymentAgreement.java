package com.alphatrader.rest;

import com.alphatrader.rest.util.LocalDateTimeDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents an employment agreement in the game.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0.0
 */
public class EmploymentAgreement {
    /**
     * The logger for this class. Use this to write messages to the console.
     */
    private static final Log log = LogFactory.getLog(EmploymentAgreement.class);

    /**
     * Gson instance for deserialization.
     */
    private static final Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class,
        new LocalDateTimeDeserializer()).create();

    /**
     * List type for gson deserialization.
     */
    private static final Type listType = new TypeToken<ArrayList<EmploymentAgreement>>() {
    }.getType();

    /**
     * The company which employs.
     */
    private final Company company = null;

    /**
     * The daily wage.
     */
    private final Double dailyWage = null;

    /**
     * The unique id.
     */
    private final String id = null;

    /**
     * The date the CEO was employed.
     */
    private final LocalDateTime startDate = null;

    /**
     * Fetches the current employment agreement of the given company.
     *
     * @param company the company
     * @return the current employment agreement
     */
    @Nullable
    public static EmploymentAgreement getEmploymentAgreement(Company company) {
        return getEmploymentAgreement(company.getId());
    }

    /**
     * Fetches all employment agreements of the current user
     *
     * @return the current user's employment agreements
     */
    @NotNull
    public static List<EmploymentAgreement> getEmploymentAgreements() {
        List<EmploymentAgreement> myReturn = new ArrayList<>();

        try {
            HttpResponse<JsonNode> response = Http.getInstance().get("/api/employmentagreements/");

            if (response != null && response.getStatus() == 200) {
                myReturn.addAll(gson.fromJson(response.getBody()
                    .getArray().toString(), listType));
            }
        }
        catch (UnirestException ue) {
            log.error("Error fetching employment agreements: " + ue.getMessage());
            StringWriter stringWriter = new StringWriter();
            ue.printStackTrace(new PrintWriter(stringWriter));
            log.debug(stringWriter.toString());
        }

        return myReturn;
    }

    /**
     * Fetches the current employment agreement of the given company.
     *
     * @param companyId the company id
     * @return the current employment agreement
     */
    @Nullable
    public static EmploymentAgreement getEmploymentAgreement(String companyId) {
        EmploymentAgreement myReturn = null;

        try {
            HttpResponse<JsonNode> response = Http.getInstance().get("/api/employmentagreements/company/"
                + companyId);

            if (response != null && response.getStatus() == 200) {
                myReturn = gson.fromJson(response.getBody()
                    .getObject().toString(), EmploymentAgreement.class);
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
     * @return the employing company
     */
    public Company getCompany() {
        return company;
    }

    /**
     * @return the daily wage
     */
    public Double getDailyWage() {
        return dailyWage;
    }

    /**
     * @return the unique id
     */
    public String getId() {
        return id;
    }

    /**
     * @return the start date
     */
    public LocalDateTime getStartDate() {
        return startDate;
    }

    @Override
    public String toString() {
        return "EmploymentAgreement{"
            + "company=" + company
            + ", dailyWage=" + dailyWage
            + ", id='" + id + '\''
            + ", startDate=" + startDate
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

        EmploymentAgreement that = (EmploymentAgreement) o;

        if (company != null ? !company.equals(that.company) : that.company != null) {
            return false;
        }
        if (dailyWage != null ? !dailyWage.equals(that.dailyWage) : that.dailyWage != null) {
            return false;
        }
        if (id != null ? !id.equals(that.id) : that.id != null) {
            return false;
        }
        return startDate != null ? startDate.equals(that.startDate) : that.startDate == null;

    }

    @Override
    public int hashCode() {
        int result = company != null ? company.hashCode() : 0;
        result = 31 * result + (dailyWage != null ? dailyWage.hashCode() : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        return result;
    }
}
