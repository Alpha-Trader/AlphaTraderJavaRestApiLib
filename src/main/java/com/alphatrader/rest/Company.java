package com.alphatrader.rest;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.net.URL;
import java.util.List;

/**
 * Represents a company in the game. Contains factory methods for parsing api json answers as well.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0
 */
public class Company {
    /**
     * The unique company identifier.
     */
    private final StringProperty id = new SimpleStringProperty();

    /**
     * The company name.
     */
    private final StringProperty name = new SimpleStringProperty();

    /**
     * The securities account id.
     */
    private final StringProperty securitiesAccountId = new SimpleStringProperty();

    /**
     * The current amount of uncommitted cash.
     */
    private final BankAccount bankAccount = null;

    /**
     * The current CEO.
     */
    private final User ceo = null;

    /**
     * The company listing on the market.
     */
    private final Listing listing = null;

    /**
     * The URL of the company logo.
     */
    private final URL logoUrl = null;

    private Portfolio portfolio;

    private CompanyProfile profile;

    /**
     * Fetches all companies currently employing the given user as a CEO.
     *
     * @param user the user who governs the company
     * @return a list of all companies governed by the user
     */
    @NotNull
    public static List<Company> getAllUserCompanies(User user) {
        return getAllUserCompanies(user.getId());
    }

    /**
     * Fetches all companies currently employing the given user as a CEO.
     *
     * @param userId the id of the user who governs the company
     * @return a list of all companies governed by the user
     */
    @NotNull
    public static List<Company> getAllUserCompanies(String userId) {
        return getMultipleCompaniesFromApi("companies/ceo/userid/" + userId);
    }

    /**
     * Fetches all companies currently employing the given user as a CEO.
     *
     * @param username the name of the user who governs the company
     * @return a list of all companies governed by the user
     */
    @NotNull
    public static List<Company> getAllUserCompaniesByUsername(String username) {
        return getMultipleCompaniesFromApi("companies/ceo/username/" + username);
    }

    /**
     * Fetches all companies governed by the logged in user.
     *
     * @return all companies currently employing the logged in user as CEO
     */
    @NotNull
    public static List<Company> getAllUserCompanies() {
        return getMultipleCompaniesFromApi("companies/");
    }

    /**
     * Fetches all companies in the game.
     *
     * @return all companies in the game
     */
    @NotNull
    public static List<Company> getAllCompanies() {
        return getMultipleCompaniesFromApi("companies/all/");
    }

    /**
     * Fetch a company by it's securities account id.
     *
     * @param securitiesAccountId the securities account id
     * @return the company or null if not found
     */
    @Nullable
    public static Company getBySecuritiesAccountId(String securitiesAccountId) {
        return getSingleCompanyFromApi("companies/securitiesaccount/" + securitiesAccountId);
    }

    /**
     * Fetch a company by it's security identifier.
     *
     * @param securityIdentifier the security identifier
     * @return the company or null if not found
     */
    @Nullable
    public static Company getBySecurityIdentifier(String securityIdentifier) {
        return getSingleCompanyFromApi("companies/securityIdentifier/" + securityIdentifier);
    }

    /**
     * Fetch a company by it's unique id.
     *
     * @param id the company id
     * @return the company or null if not found
     */
    @Nullable
    public static Company getById(String id) {
        return getSingleCompanyFromApi("companies/" + id);
    }

    /**
     * Search for companies with matching name parts.
     *
     * @param namePart the search query
     * @return a list of companies matching the search
     */
    @NotNull
    public static List<Company> searchByName(String namePart) {
        return getMultipleCompaniesFromApi("search/companies/" + namePart);
    }

    /**
     * API wrapper function for single company fetching.
     *
     * @param suffix the api endpoint suffix
     * @return the requested company
     */
    @Nullable
    private static Company getSingleCompanyFromApi(String suffix) {
        return Http.getSingleObjectFromApi(Company.class, "/api/" + suffix);
    }

    /**
     * API wrapper function for all company lists.
     *
     * @param suffix the api endpoint suffix
     * @return the requested list of companies
     */
    @NotNull
    private static List<Company> getMultipleCompaniesFromApi(String suffix) {
        return Http.getMultipleObjectFromApi(Company.class, "/api/" + suffix);
    }

    /**
     * @return the name
     */
    public String getName() {
        return name.getValue();
    }

    /**
     * @return the securities accounts unique identifier.
     */
    public String getSecuritiesAccountId() {
        return securitiesAccountId.getValue();
    }

    /**
     * @return the company's unique id.
     */
    public String getId() {
        return id.getValue();
    }

    /**
     * @return the listing of the company
     */
    public Listing getListing() {
        return listing;
    }

    /**
     * @return the company portfolio
     */
    public Portfolio getPortfolio() {
        if (this.portfolio == null) {
            portfolio = Portfolio.getCompanyPortfolio(this);
        }
        return this.portfolio;

    }

    /**
     * @return the company profile
     */
    public CompanyProfile getProfile() {
        if (this.profile == null) {
            this.profile = CompanyProfile.getByCompany(this);
        }
        return this.profile;
    }

    @Override
    public String toString() {
        return "Company{"
            + "id='" + id.getValue() + '\''
            + ", name='" + name.getValue() + '\''
            + ", securitiesAccountId='" + securitiesAccountId.getValue() + '\''
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

        Company company = (Company) o;

        return id.getValue() != null ? id.getValue().equals(company.id.getValue())
            : company.id.getValue() == null;

    }

    @Override
    public int hashCode() {
        return id.getValue() != null ? id.getValue().hashCode() : 0;
    }
}
