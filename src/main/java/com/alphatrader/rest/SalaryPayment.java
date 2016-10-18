package com.alphatrader.rest;

import com.alphatrader.rest.util.LocalDateTimeDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jetbrains.annotations.Nullable;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;

/**
 * Represents a salary payment in the game.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0.0
 */
public class SalaryPayment {
    /**
     * The logger for this class.
     */
    private static final Log log = LogFactory.getLog(SecurityOrderLog.class);

    /**
     * Gson instance for deserialization.
     */
    private static final Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class,
        new LocalDateTimeDeserializer()).create();

    /**
     * The company id.
     */
    private final String companyId = null;

    /**
     * The next possible payment date.
     */
    private final LocalDateTime nextPossiblePaymentDate = null;

    /**
     * The salary amount.
     */
    private final Double salaryAmount = null;

    /**
     * The date the salary was paid.
     */
    private final LocalDateTime date = null;

    /**
     * The unique id of this payment.
     */
    private final String id = null;

    /**
     * Fetches the salary payment with the given id.
     *
     * @param paymentId the identifier to look for
     * @return the salary payment or null if not found
     */
    @Nullable
    public static SalaryPayment getById(String paymentId) {
        SalaryPayment myReturn = null;

        try {
            HttpResponse<JsonNode> response = Http.getInstance().get("/api/salarypayments/"
                + paymentId);
            String listing = response.getBody().getObject().toString();
            myReturn = gson.fromJson(listing, SalaryPayment.class);
        }
        catch (UnirestException ue) {
            log.error("Error fetching payment: " + ue.getMessage());
            StringWriter stringWriter = new StringWriter();
            ue.printStackTrace(new PrintWriter(stringWriter));
            log.debug(stringWriter.toString());
        }
        return myReturn;
    }

    /**
     * @return the company id
     */
    public String getCompanyId() {
        return companyId;
    }

    /**
     * @return the date at which the next payment can be made
     */
    public LocalDateTime getNextPossiblePaymentDate() {
        return nextPossiblePaymentDate;
    }

    /**
     * @return the salary amount
     */
    public Double getSalaryAmount() {
        return salaryAmount;
    }

    /**
     * @return the date this salary was paid
     */
    public LocalDateTime getDate() {
        return date;
    }

    /**
     * @return the unique id
     */
    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "SalaryPayment{"
            + "companyId='" + companyId + '\''
            + ", nextPossiblePaymentDate=" + nextPossiblePaymentDate
            + ", salaryAmount=" + salaryAmount
            + ", date=" + date
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

        SalaryPayment that = (SalaryPayment) o;

        return id != null ? id.equals(that.id) : that.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
