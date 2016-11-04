package com.alphatrader.rest;

import javafx.beans.property.*;

/**
 * Represents a sponsor rating in the game.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0.0
 */
public class SponsorRating {
    /**
     * The salary paid for the sponsorship
     */
    private final DoubleProperty salary = new SimpleDoubleProperty();

    /**
     * The rating classification.
     */
    private final ObjectProperty<SponsorRatingLetter> value = new SimpleObjectProperty<>();

    /**
     * @return the sponsor salary
     */
    @PublicAPI
    public Double getSalary() {
        return salary.getValue();
    }

    /**
     * @return the valuation of the sponsor
     */
    @PublicAPI
    public SponsorRatingLetter getValue() {
        return value.getValue();
    }

    /**
     * @return the salary property.
     */
    @PublicAPI
    public ReadOnlyDoubleProperty salaryProperty() {
        return salary;
    }

    /**
     * @return the value property.
     */
    @PublicAPI
    public ReadOnlyObjectProperty<SponsorRatingLetter> valueProperty() {
        return value;
    }

    /**
     * Represents the sponsor rating classes.
     */
    public enum SponsorRatingLetter {
        A, B, C, D
    }

    @Override
    public String toString() {
        return "SponsorRating{"
            + "salary=" + salary
            + ", value=" + value
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

        SponsorRating that = (SponsorRating) o;

        return salary.getValue() != null ? salary.getValue().equals(that.salary.getValue()) : that
            .salary.getValue() == null && value.getValue() == that.value.getValue();

    }

    @Override
    public int hashCode() {
        int result = salary.getValue() != null ? salary.getValue().hashCode() : 0;
        result = 31 * result + (value.getValue() != null ? value.getValue().hashCode() : 0);
        return result;
    }
}
