package com.alphatrader.rest;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * Represents the main interest rate in the game.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0.0
 */
@SuppressWarnings("ConstantConditions")
public class MainInterestRate {
    /**
     * The unique id of this main interest rate.
     */
    private final String id = null;

    /**
     * The interest rate of this main interest rate.
     */
    private final Double value = null;

    /**
     * The date this main interest rate was put into place.
     */
    private final ZonedDateTime date = null;

    /**
     * Fetches the latest main interest rate.
     *
     * @return the latest main interest rate
     */
    @Nullable
    public static MainInterestRate getCurrent() {
        return Http.getSingleObjectFromApi(MainInterestRate.class, "/api/maininterestrate/latest/");
    }

    /**
     * Fetches all main interest rates.
     *
     * @return all main interest rates
     */
    @NotNull
    public static List<MainInterestRate> getAll() {
        return Http.getMultipleObjectFromApi(MainInterestRate.class, "/api/maininterestrate/");
    }

    /**
     * @return the unique id
     */
    public String getId() {
        return id;
    }

    /**
     * @return the interest rate
     */
    public Double getValue() {
        return value;
    }

    /**
     * @return the start date
     */
    public ZonedDateTime getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "MainInterestRate{"
            + "id='" + id + '\''
            + ", value=" + value
            + ", date=" + date
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

        MainInterestRate that = (MainInterestRate) o;

        return id != null ? id.equals(that.id) : that.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
