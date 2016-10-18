package com.alphatrader.rest;

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
    private final Double salary = null;

    /**
     * The rating classification.
     */
    private final SponsorRatingLetter value = null;

    /**
     * @return the sponsor salary
     */
    public Double getSalary() {
        return salary;
    }

    /**
     * @return the valuation of the sponsor
     */
    public SponsorRatingLetter getValue() {
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

        if (salary != null ? !salary.equals(that.salary) : that.salary != null) {
            return false;
        }
        return value == that.value;

    }

    @Override
    public int hashCode() {
        int result = salary != null ? salary.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }
}
