package com.alphatrader.rest;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jetbrains.annotations.Nullable;

import java.io.PrintWriter;
import java.io.StringWriter;

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
        CentralBankReserves myReturn = null;

        try {
            HttpResponse<JsonNode> response = Http.getInstance().get(
                "/api/centralbankreserves/?companyId=" + companyId);
            if (response != null && response.getStatus() == 200) {
                myReturn = gson.fromJson(response.getBody().getObject().toString(),
                    CentralBankReserves.class);
            }
        }
        catch (UnirestException ue) {
            log.error("Error loading central bank reserves: " + ue.getMessage());
            StringWriter stringWriter = new StringWriter();
            ue.printStackTrace(new PrintWriter(stringWriter));
            log.debug(stringWriter.toString());
        }

        return myReturn;
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

        if (id != null ? !id.equals(that.id) : that.id != null) {
            return false;
        }
        if (bankingLicense != null ? !bankingLicense.equals(that.bankingLicense)
            : that.bankingLicense != null) {
            return false;
        }
        if (maxCentralBankLoans != null ? !maxCentralBankLoans.equals(that.maxCentralBankLoans)
            : that.maxCentralBankLoans != null) {
            return false;
        }
        return cashHolding != null ? cashHolding.equals(that.cashHolding) : that.cashHolding == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (bankingLicense != null ? bankingLicense.hashCode() : 0);
        result = 31 * result + (maxCentralBankLoans != null ? maxCentralBankLoans.hashCode() : 0);
        result = 31 * result + (cashHolding != null ? cashHolding.hashCode() : 0);
        return result;
    }
}
