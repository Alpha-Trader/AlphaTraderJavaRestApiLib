package com.alphatrader.rest;

import javafx.beans.property.*;
import org.jetbrains.annotations.Nullable;

import java.lang.annotation.Documented;

/**
 * Represents a bank account in the game.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0.0
 */
public class BankAccount {
    /**
     * The amount of cash stored in this bank account.
     */
    private final DoubleProperty cash = new SimpleDoubleProperty();

    /**
     * The unique id of this bank account.
     */
    private final StringProperty id = new SimpleStringProperty();

    /**
     * @return the bank account of the logged in user
     */
    @PublicAPI
    @Nullable
    public static BankAccount getUserBankAccount() {
        return Http.getSingleObjectFromApi(BankAccount.class, "/api/bankaccounts/");
    }

    /**
     * @return the amount of cash in this bank account
     */
    @PublicAPI
    public Double getCash() {
        return cash.getValue();
    }

    /**
     * @return the id of this bank account
     */
    @PublicAPI
    public String getId() {
        return id.getValue();
    }

    /**
     * @return the id property.
     */
    @PublicAPI
    public ReadOnlyStringProperty idProperty() {
        return id;
    }

    /**
     * @return the cash property.
     */
    @PublicAPI
    public ReadOnlyDoubleProperty cashProperty() {
        return cash;
    }

    @Override
    public String toString() {
        return "BankAccount{"
            + "cash=" + cash.getValue()
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

        BankAccount that = (BankAccount) o;

        return id.getValue() != null ? id.getValue().equals(that.id.getValue())
            : that.id.getValue() == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(cash.getValue());
        result = (int) (temp ^ (temp >>> 32));
        result = 31 * result + (id.getValue() != null ? id.getValue().hashCode() : 0);
        return result;
    }
}
