package com.alphatrader.rest;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.jetbrains.annotations.Nullable;

import java.time.ZonedDateTime;

/**
 * Represents a salary payment in the game.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0.0
 */
public class SalaryPayment {
    /**
     * The company id.
     */
    private final StringProperty companyId = new SimpleStringProperty();

    /**
     * The next possible payment date.
     */
    private final ZonedDateTime nextPossiblePaymentDate = null;

    /**
     * The salary amount.
     */
    private final DoubleProperty salaryAmount = new SimpleDoubleProperty();

    /**
     * The date the salary was paid.
     */
    private final ZonedDateTime date = null;

    /**
     * The unique id of this payment.
     */
    private final StringProperty id = new SimpleStringProperty();

    /**
     * Fetches the salary payment with the given id. (Payment id is returned when executing payment post
     * request)
     *
     * @param paymentId the identifier to look for
     * @return the salary payment or null if not found
     */
    @Nullable
    public static SalaryPayment getById(String paymentId) {
        return Http.getSingleObjectFromApi(SalaryPayment.class, "/api/salarypayments/" + paymentId);
    }

    /**
     * @return the company id
     */
    public String getCompanyId() {
        return companyId.getValue();
    }

    /**
     * @return the date at which the next payment can be made
     */
    public ZonedDateTime getNextPossiblePaymentDate() {
        return nextPossiblePaymentDate;
    }

    /**
     * @return the salary amount
     */
    public Double getSalaryAmount() {
        return salaryAmount.getValue();
    }

    /**
     * @return the date this salary was paid
     */
    public ZonedDateTime getDate() {
        return date;
    }

    /**
     * @return the unique id
     */
    public String getId() {
        return id.getValue();
    }

    @Override
    public String toString() {
        return "SalaryPayment{"
            + "companyId='" + companyId.getValue() + '\''
            + ", nextPossiblePaymentDate=" + nextPossiblePaymentDate
            + ", salaryAmount=" + salaryAmount.getValue()
            + ", date=" + date
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

        SalaryPayment that = (SalaryPayment) o;

        return id.getValue() != null ? id.getValue().equals(that.id.getValue())
            : that.id.getValue() == null;

    }

    @Override
    public int hashCode() {
        return id.getValue() != null ? id.getValue().hashCode() : 0;
    }
}
