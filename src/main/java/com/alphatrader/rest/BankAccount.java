package com.alphatrader.rest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jetbrains.annotations.Nullable;

/**
 * Represents a bank account in the game.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0.0
 */
public class BankAccount {
    /**
     * The logger for this class.
     */
    private static final Log log = LogFactory.getLog(BankAccount.class);

    /**
     * The amount of cash stored in this bank account.
     */
    private final Double cash = null;

    /**
     * The unique id of this bank account.
     */
    private final String id = null;

    /**
     * @return the bank account of the logged in user
     */
    @Nullable
    public static BankAccount getUserBankAccount() {
        return Http.getSingleObjectFromApi(BankAccount.class, "/api/bankaccounts");
    }

    /**
     * @return the amount of cash in this bank account
     */
    public Double getCash() {
        return cash;
    }

    /**
     * @return the id of this bank account
     */
    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "BankAccount{"
            + "cash=" + cash
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

        BankAccount that = (BankAccount) o;

        return id != null ? id.equals(that.id) : that.id == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(cash);
        result = (int) (temp ^ (temp >>> 32));
        result = 31 * result + (id != null ? id.hashCode() : 0);
        return result;
    }
}
