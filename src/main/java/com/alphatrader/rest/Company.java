package com.alphatrader.rest;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a company in the game. Contains factory methods for parsing api json answers as well.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0
 */
@SuppressWarnings("ConstantConditions")
public class Company {
    /**
     * The logger for this company.
     */
    private static final Log log = LogFactory.getLog(Company.class);

    /**
     * Gson instance for deserialization.
     */
    private static final Gson gson = new Gson();

    /**
     * List type for gson deserialization.
     */
    private static final Type listType = new TypeToken<ArrayList<Company>>() {
    }.getType();

    /**
     * The unique company identifier.
     */
    private final String id = null;

    /**
     * The company name.
     */
    private final String name = null;

    /**
     * The securities account id.
     */
    private final String securitiesAccountId = null;

    /**
     * The current amount of uncommitted cash.
     */
    private final BankAccount bankAccount = null;

    /**
     * The company's portfolio.
     */
    private Portfolio portfolio;

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
        return name;
    }

    /**
     * @return the securities accounts unique identifier.
     */
    public String getSecuritiesAccountId() {
        return securitiesAccountId;
    }

    /**
     * @return the company's unique id.
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the portfolio of this company. This call will be evaluated lazily to avoid congesting the
     * server.
     *
     * @return the company's portfolio.
     */
    public Portfolio getPortfolio() {
        if (this.portfolio == null) {
            this.portfolio = Portfolio.getCompanyPortfolio(this);
        }
        return this.portfolio;
    }

    /**
     * Sets the company portfolio to the provided one.
     *
     * @param portfolio the new portfolio
     */
    public void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
    }

    @Override
    public String toString() {
        return "Company{"
            + "id='" + id + '\''
            + ", name='" + name + '\''
            + ", securitiesAccountId='" + securitiesAccountId + '\''
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

        if (id != null ? !id.equals(company.id) : company.id != null) {
            return false;
        }
        if (name != null ? !name.equals(company.name) : company.name != null) {
            return false;
        }
        return securitiesAccountId != null ? securitiesAccountId.equals(company.securitiesAccountId)
            : company.securitiesAccountId == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (securitiesAccountId != null ? securitiesAccountId.hashCode() : 0);
        return result;
    }

    public Listing getListing() {
        return listing;
    }
}
