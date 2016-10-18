package com.alphatrader.rest;

/**
 * Represents a companies capabilities in the game.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0.0
 */
@SuppressWarnings("ConstantConditions")
public class CompanyCapabilities {
    /**
     * True, if the company can request a banking license.
     */
    private final Boolean bankReady = null;

    /**
     * True, if the company is a bank.
     */
    private final Boolean bank = null;

    /**
     * The amount of central bank loans taken by this company.
     */
    private final Double takenCentralBankLoans = null;

    /**
     * True, if this company is a designated sponsor.
     */
    private final Boolean designatedSponsor = null;

    /**
     * The amount of reserves this company deposited.
     */
    private final Double reserves = null;

    /**
     * The maximum amount of central bank loans this company can request.
     */
    private final Double maxCentralBankLoans = null;

    /**
     * The company's net cash.
     */
    private final Double netCash = null;

    /**
     * @return true if the company may become a bank
     */
    public Boolean isBankReady() {
        return bankReady;
    }

    /**
     * @return true if the company is a bank
     */
    public Boolean isBank() {
        return bank;
    }

    /**
     * @return the amount of taken central bank loads
     */
    public Double getTakenCentralBankLoans() {
        return takenCentralBankLoans;
    }

    /**
     * @return true, if the company is a designated sponsor
     */
    public Boolean isDesignatedSponsor() {
        return designatedSponsor;
    }

    /**
     * @return the amount of deposited reserves
     */
    public Double getReserves() {
        return reserves;
    }

    /**
     * @return the maximum central bank loans this company can take
     */
    public Double getMaxCentralBankLoans() {
        return maxCentralBankLoans;
    }

    /**
     * @return the company's net cash
     */
    public Double getNetCash() {
        return netCash;
    }

    @Override
    public String toString() {
        return "CompanyCapabilities{"
            + "bankReady=" + bankReady
            + ", bank=" + bank
            + ", takenCentralBankLoans=" + takenCentralBankLoans
            + ", designatedSponsor=" + designatedSponsor
            + ", reserves=" + reserves
            + ", maxCentralBankLoans=" + maxCentralBankLoans
            + ", netCash=" + netCash
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

        CompanyCapabilities that = (CompanyCapabilities) o;

        if (bankReady != null ? !bankReady.equals(that.bankReady) : that.bankReady != null) {
            return false;
        }
        if (bank != null ? !bank.equals(that.bank) : that.bank != null) {
            return false;
        }
        if (takenCentralBankLoans != null ? !takenCentralBankLoans.equals(that.takenCentralBankLoans)
            : that.takenCentralBankLoans != null) {
            return false;
        }
        if (designatedSponsor != null ? !designatedSponsor.equals(that.designatedSponsor)
            : that.designatedSponsor != null) {
            return false;
        }
        if (reserves != null ? !reserves.equals(that.reserves) : that.reserves != null) {
            return false;
        }
        if (maxCentralBankLoans != null ? !maxCentralBankLoans.equals(that.maxCentralBankLoans)
            : that.maxCentralBankLoans != null) {
            return false;
        }
        return netCash != null ? netCash.equals(that.netCash) : that.netCash == null;

    }

    @Override
    public int hashCode() {
        int result = bankReady != null ? bankReady.hashCode() : 0;
        result = 31 * result + (bank != null ? bank.hashCode() : 0);
        result = 31 * result + (takenCentralBankLoans != null ? takenCentralBankLoans.hashCode() : 0);
        result = 31 * result + (designatedSponsor != null ? designatedSponsor.hashCode() : 0);
        result = 31 * result + (reserves != null ? reserves.hashCode() : 0);
        result = 31 * result + (maxCentralBankLoans != null ? maxCentralBankLoans.hashCode() : 0);
        result = 31 * result + (netCash != null ? netCash.hashCode() : 0);
        return result;
    }
}
