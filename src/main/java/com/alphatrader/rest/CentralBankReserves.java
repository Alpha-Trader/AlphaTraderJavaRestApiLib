package com.alphatrader.rest;

import javafx.beans.property.*;
import org.jetbrains.annotations.Nullable;

/**
 * Represents the central bank reserves of a company in the game.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0.0
 */
public class CentralBankReserves {
    /**
     * The unique identifier for the central bank reserves.
     */
    private final StringProperty id = new SimpleStringProperty();

    /**
     * The banking license associated with these reserves.
     */
    private final ObjectProperty<BankingLicense> bankingLicense = new SimpleObjectProperty<>();

    /**
     * The maximum amount of loans allowed for this company.
     */
    private final DoubleProperty maxCentralBankLoans = new SimpleDoubleProperty();

    /**
     * The current amount of cash held in the reserve.
     */
    private final DoubleProperty cashHolding = new SimpleDoubleProperty();

    /**
     * Returns the central bank loans for a certain company.
     *
     * @param companyId the unique identifier of the company
     * @return the central bank loans of the company
     */
    @PublicAPI
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
    @PublicAPI
    @Nullable
    public static CentralBankReserves getByCompany(Company company) {
        return getByCompany(company.getId());
    }

    /**
     * Fetches the central bank reserves with the given id.
     *
     * @param id the central bank reserve's id
     * @return the central bank reserve
     */
    @PublicAPI
    @Nullable
    public static CentralBankReserves getById(String id) {
        return Http.getSingleObjectFromApi(CentralBankReserves.class, "/api/centralbankreserves/" + id);
    }

    /**
     * @return the unique id of these reserves
     */
    @PublicAPI
    public String getId() {
        return id.getValue();
    }

    /**
     * @return the associated banking license
     */
    @PublicAPI
    public BankingLicense getBankingLicense() {
        return bankingLicense.getValue();
    }

    /**
     * @return the maximum of central bank loans
     */
    @PublicAPI
    public Double getMaxCentralBankLoans() {
        return maxCentralBankLoans.getValue();
    }

    /**
     * @return the currently held cash
     */
    @PublicAPI
    public Double getCashHolding() {
        return cashHolding.getValue();
    }

    @Override
    public String toString() {
        return "CentralBankReserves{"
            + "id='" + id.getValue() + '\''
            + ", bankingLicense=" + bankingLicense
            + ", maxCentralBankLoans=" + maxCentralBankLoans.getValue()
            + ", cashHolding=" + cashHolding.getValue()
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

        return id.getValue() != null ? id.getValue().equals(that.id.getValue())
            : that.id.getValue() == null;

    }

    @Override
    public int hashCode() {
        return id.getValue() != null ? id.getValue().hashCode() : 0;
    }
}
