package com.alphatrader.rest;

import com.google.gson.Gson;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jetbrains.annotations.Nullable;

/**
 * Represents the central bank reserves of a company in the game.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0.0
 */
@SuppressWarnings("ConstantConditions")
public class CentralBankReserves {
    /**
     * The logger for this class.
     */
    private static final Log log = LogFactory.getLog(CentralBankReserves.class);

    /**
     * Gson instance for deserialization.
     */
    private static final Gson gson = new Gson();

    /**
     * The unique identifier for the central bank reserves.
     */
    private final String id = null;

    /**
     * The banking license associated with these reserves.
     */
    private final BankingLicense bankingLicense = null;

    /**
     * The maximum amount of loans allowed for this company.
     */
    private final Double maxCentralBankLoans = null;

    /**
     * The current amount of cash held in the reserve.
     */
    private final Double cashHolding = null;

    /**
     * Returns the central bank loans for a certain company.
     *
     * @param companyId the unique identifier of the company
     * @return the central bank loans of the company
     */
    @Nullable
    public static CentralBankReserves getByCompany(String companyId) {
        return Http.getSingleObjectFromApi(CentralBankReserves.class,
            "/api/centralbankreserves/?companyId=" + companyId);
    }

    /**
     * Returns the central bank loans for a certain company.
     *
     * @param company the company
     * @return the central bank loans of the company
     */
    @Nullable
    public static CentralBankReserves getByCompany(Company company) {
        return getByCompany(company.getId());
    }

    /**
     * @return the unique id of these reserves
     */
    public String getId() {
        return id;
    }

    /**
     * @return the associated banking license
     */
    public BankingLicense getBankingLicense() {
        return bankingLicense;
    }

    /**
     * @return the maximum of central bank loans
     */
    public Double getMaxCentralBankLoans() {
        return maxCentralBankLoans;
    }

    /**
     * @return the currently held cash
     */
    public Double getCashHolding() {
        return cashHolding;
    }

    @Override
    public String toString() {
        return "CentralBankReserves{"
            + "id='" + id + '\''
            + ", bankingLicense=" + bankingLicense
            + ", maxCentralBankLoans=" + maxCentralBankLoans
            + ", cashHolding=" + cashHolding
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

        CentralBankReserves that = (CentralBankReserves) o;

        return id != null ? id.equals(that.id) : that.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
