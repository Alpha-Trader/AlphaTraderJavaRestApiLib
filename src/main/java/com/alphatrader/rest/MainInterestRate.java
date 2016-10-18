package com.alphatrader.rest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
     * The logger for this class.
     */
    private static final Log log = LogFactory.getLog(MainInterestRate.class);

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
    private final ZonedDateTime dateTime = null;

    /**
     * Fetches the latest main interest rate.
     *
     * @return the latest main interest rate
     */
    @Nullable
    private static MainInterestRate getCurrent() {
        return Http.getSingleObjectFromApi(MainInterestRate.class, "/api/maininterrestrate/latest/");
    }

    /**
     * Fetches all main interest rates.
     *
     * @return all main interest rates
     */
    @NotNull
    private static List<MainInterestRate> getAll() {
        return Http.getMultipleObjectFromApi(MainInterestRate.class, "/api/maininterrestrate/");
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
    public ZonedDateTime getDateTime() {
        return dateTime;
    }

    @Override
    public String toString() {
        return "MainInterestRate{"
            + "id='" + id + '\''
            + ", value=" + value
            + ", dateTime=" + dateTime
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

        if (id != null ? !id.equals(that.id) : that.id != null) {
            return false;
        }
        if (value != null ? !value.equals(that.value) : that.value != null) {
            return false;
        }
        return dateTime != null ? dateTime.equals(that.dateTime) : that.dateTime == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (dateTime != null ? dateTime.hashCode() : 0);
        return result;
    }
}
