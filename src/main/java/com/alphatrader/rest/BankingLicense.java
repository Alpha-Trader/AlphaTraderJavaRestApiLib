package com.alphatrader.rest;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.jetbrains.annotations.Nullable;

import java.time.ZonedDateTime;

/**
 * Represents a banking licence in the game.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0.0
 */
public class BankingLicense {
    /**
     * The date the license has been issued.
     */
    private final ObjectProperty<ZonedDateTime> startDate = new SimpleObjectProperty<>();

    /**
     * The company this license enables banking functions for.
     */
    private final ObjectProperty<Company> company = new SimpleObjectProperty<>();

    /**
     * The id of this banking license.
     */
    private final StringProperty id = new SimpleStringProperty();

    /**
     * Fetches the banking license for the specified company from the API.
     *
     * @param companyId the company id to look for a banking license
     * @return the banking license or null if it doesn't exist.
     */
    @PublicAPI
    @Nullable
    public static BankingLicense getBankingLicenseOfCompany(String companyId) {
        return getFromApi("?companyId=" + companyId);
    }

    /**
     * Fetches the banking license for the specified company from the API.
     *
     * @param company the company to look for a banking license
     * @return the banking license or null if it doesn't exist.
     */
    @PublicAPI
    @Nullable
    public static BankingLicense getBankingLicenseOfCompany(Company company) {
        return getBankingLicenseOfCompany(company.getId());
    }

    /**
     * Fetches the banking license for the specified license id from the API.
     *
     * @param licenseId the license id to look for a banking license
     * @return the banking license or null if it doesn't exist.
     */
    @PublicAPI
    @Nullable
    public static BankingLicense getBankingLicenseById(String licenseId) {
        return getFromApi(licenseId);
    }

    /**
     * Wrapper for the api access.
     *
     * @param suffix the api endpoint suffix
     * @return the requested license
     */
    @Nullable
    private static BankingLicense getFromApi(String suffix) {
        return Http.getSingleObjectFromApi(BankingLicense.class, "/api/bankinglicense/" + suffix);
    }

    /**
     * @return the issue date of this license
     */
    @PublicAPI
    public ZonedDateTime getStartDate() {
        return startDate.getValue();
    }

    /**
     * @return the company this license belongs to
     */
    @PublicAPI
    public Company getCompany() {
        return company.getValue();
    }

    /**
     * @return the id of this license
     */
    @PublicAPI
    public String getId() {
        return id.getValue();
    }

    @Override
    public String toString() {
        return "BankingLicense{"
            + "startDate=" + startDate.getValue()
            + ", company=" + company.getValue()
            + ", id='" + id.getValue() + '\''
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

        return id.getValue() != null ? id.getValue().equals(that.id.getValue())
            : that.id.getValue() == null;
    }

    @Override
    public int hashCode() {
        return id.getValue() != null ? id.getValue().hashCode() : 0;
    }
}
