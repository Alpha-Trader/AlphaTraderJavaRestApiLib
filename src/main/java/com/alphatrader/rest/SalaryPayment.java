package com.alphatrader.rest;

import javafx.beans.property.*;
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
    private final ObjectProperty<ZonedDateTime> nextPossiblePaymentDate = new SimpleObjectProperty<>();

    /**
     * The salary amount.
     */
    private final DoubleProperty salaryAmount = new SimpleDoubleProperty();

    /**
     * The date the salary was paid.
     */
    private final ObjectProperty<ZonedDateTime> date = new SimpleObjectProperty<>();

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
    @PublicAPI
    @Nullable
    public static SalaryPayment getById(String paymentId) {
        return Http.getSingleObjectFromApi(SalaryPayment.class, "/api/salarypayments/" + paymentId);
    }

    /**
     * @return the company id
     */
    @PublicAPI
    public String getCompanyId() {
        return companyId.getValue();
    }

    /**
     * @return the date at which the next payment can be made
     */
    @PublicAPI
    public ZonedDateTime getNextPossiblePaymentDate() {
        return nextPossiblePaymentDate.getValue();
    }

    /**
     * @return the salary amount
     */
    @PublicAPI
    public Double getSalaryAmount() {
        return salaryAmount.getValue();
    }

    /**
     * @return the date this salary was paid
     */
    @PublicAPI
    public ZonedDateTime getDate() {
        return date.getValue();
    }

    /**
     * @return the unique id
     */
    @PublicAPI
    public String getId() {
        return id.getValue();
    }

    /**
     * @return the company id property.
     */
    @PublicAPI
    public ReadOnlyStringProperty companyIdProperty() {
        return companyId;
    }

    /**
     * @return the next possible payment date property.
     */
    @PublicAPI
    public ReadOnlyObjectProperty<ZonedDateTime> nextPossiblePaymentDateProperty() {
        return nextPossiblePaymentDate;
    }

    /**
     * @return the salary amount property property.
     */
    @PublicAPI
    public ReadOnlyDoubleProperty salaryAmountProperty() {
        return salaryAmount;
    }

    /**
     * @return the date property property.
     */
    @PublicAPI
    public ReadOnlyObjectProperty<ZonedDateTime> dateProperty() {
        return date;
    }

    /**
     * @return the id property.
     */
    @PublicAPI
    public ReadOnlyStringProperty idProperty() {
        return id;
    }

    @Override
    public String toString() {
        return "SalaryPayment{"
            + "companyId='" + companyId.getValue() + '\''
            + ", nextPossiblePaymentDate=" + nextPossiblePaymentDate.getValue()
            + ", salaryAmount=" + salaryAmount.getValue()
            + ", date=" + date.getValue()
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
