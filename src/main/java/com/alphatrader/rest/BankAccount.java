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
     * The gson wrapper for this class.
     */
    private static final Gson gson = new Gson();

    /**
     * The amount of cash stored in this bank account.
     */
    private final double cash;

    /**
     * The unique id of this bank account.
     */
    private final String id;

    /**
     * Creates a new bank account with the given parameters.
     *
     * @param id   the unique id of this account
     * @param cash the amount of cash in this account
     */
    public BankAccount(String id, double cash) {
        this.id = id;
        this.cash = cash;
    }

    /**
     * @return the bank account of the logged in user
     */
    @Nullable
    public static BankAccount getUserBankAccount() {
        BankAccount myReturn = null;
        try {
            HttpResponse<JsonNode> response = Http.getInstance().get("/api/bankaccounts");

            if (response != null && response.getStatus() == 200) {
                myReturn = gson.fromJson(
                    response.getBody()
                        .getObject()
                        .toString(),
                    BankAccount.class);
            }
        }
        catch (UnirestException ue) {
            log.error("Error loading bank account: " + ue.getMessage());
            StringWriter stringWriter = new StringWriter();
            ue.printStackTrace(new PrintWriter(stringWriter));
            log.debug(stringWriter.toString());
        }

        return myReturn;
    }

    /**
     * @return the amount of cash in this bank account
     */
    public double getCash() {
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

        if (Double.compare(that.cash, cash) != 0) {
            return false;
        }
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
