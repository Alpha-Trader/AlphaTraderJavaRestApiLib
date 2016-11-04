package com.alphatrader.rest;

import javafx.beans.property.*;
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
public class EmploymentAgreement {
    /**
     * The company which employs.
     */
    private final ObjectProperty<Company> company = new SimpleObjectProperty<>();

    /**
     * The daily wage.
     */
    private final DoubleProperty dailyWage = new SimpleDoubleProperty();

    /**
     * The unique id.
     */
    private final StringProperty id = new SimpleStringProperty();

    /**
     * The date the CEO was employed.
     */
    private final ObjectProperty<ZonedDateTime> startDate = new SimpleObjectProperty<>();

    /**
     * Fetches the current employment agreement of the given company.
     *
     * @param company the company
     * @return the current employment agreement
     */
    @PublicAPI
    @Nullable
    public static EmploymentAgreement getEmploymentAgreement(Company company) {
        return getEmploymentAgreement(company.getId());
    }

    /**
     * Fetches all employment agreements of the current user
     *
     * @return the current user's employment agreements
     */
    @PublicAPI
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
    @PublicAPI
    @Nullable
    public static EmploymentAgreement getEmploymentAgreement(String companyId) {
        return Http.getSingleObjectFromApi(EmploymentAgreement.class,
            "/api/employmentagreements/company/" + companyId);
    }

    /**
     * @return the employing company
     */
    @PublicAPI
    public Company getCompany() {
        return company.getValue();
    }

    /**
     * @return the daily wage
     */
    @PublicAPI
    public Double getDailyWage() {
        return dailyWage.getValue();
    }

    /**
     * @return the unique id
     */
    @PublicAPI
    public String getId() {
        return id.getValue();
    }

    /**
     * @return the start date
     */
    @PublicAPI
    public ZonedDateTime getStartDate() {
        return startDate.getValue();
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

        return id.getValue() != null ? id.getValue().equals(that.id.getValue())
            : that.id.getValue() == null;

    }

    @Override
    public int hashCode() {
        return id.getValue() != null ? id.getValue().hashCode() : 0;
    }
}
