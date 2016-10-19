package com.alphatrader.rest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * Represents an employment agreement in the game.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0.0
 */
@SuppressWarnings("ConstantConditions")
public class EmploymentAgreement {
    /**
     * The logger for this class. Use this to write messages to the console.
     */
    private static final Log log = LogFactory.getLog(EmploymentAgreement.class);

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
    private final ZonedDateTime startDate = null;

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
        return Http.getMultipleObjectFromApi(EmploymentAgreement.class, "/api/employmentagreements/");
    }

    /**
     * Fetches the current employment agreement of the given company.
     *
     * @param companyId the company id
     * @return the current employment agreement
     */
    @Nullable
    public static EmploymentAgreement getEmploymentAgreement(String companyId) {
        return Http.getSingleObjectFromApi(EmploymentAgreement.class,
            "/api/employmentagreements/company/" + companyId);
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
    public ZonedDateTime getStartDate() {
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

        return id != null ? id.equals(that.id) : that.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
