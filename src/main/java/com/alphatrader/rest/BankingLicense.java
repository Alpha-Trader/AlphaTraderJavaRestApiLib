package com.alphatrader.rest;

import org.jetbrains.annotations.Nullable;

import java.time.ZonedDateTime;

/**
 * Represents a banking licence in the game.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0.0
 */
@SuppressWarnings("ConstantConditions")
public class BankingLicense {
    /**
     * The date the license has been issued.
     */
    private final ZonedDateTime startDate = null;

    /**
     * The company this license enables banking functions for.
     */
    private final Company company = null;

    /**
     * The id of this banking license.
     */
    private final String id = null;

    /**
     * Fetches the banking license for the specified company from the API.
     *
     * @param companyId the company id to look for a banking license
     * @return the banking license or null if it doesn't exist.
     */
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
    public ZonedDateTime getStartDate() {
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

        return id != null ? id.equals(that.id) : that.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
