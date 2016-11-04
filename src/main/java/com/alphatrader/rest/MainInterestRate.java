package com.alphatrader.rest;

import javafx.beans.property.*;
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
public class MainInterestRate {
    /**
     * The unique id of this main interest rate.
     */
    private final StringProperty id = new SimpleStringProperty();

    /**
     * The interest rate of this main interest rate.
     */
    private final DoubleProperty value = new SimpleDoubleProperty();

    /**
     * The date this main interest rate was put into place.
     */
    private final ObjectProperty<ZonedDateTime> date = new SimpleObjectProperty<>();

    /**
     * Fetches the latest main interest rate.
     *
     * @return the latest main interest rate
     */
    @PublicAPI
    @Nullable
    public static MainInterestRate getCurrent() {
        return Http.getSingleObjectFromApi(MainInterestRate.class, "/api/maininterestrate/latest/");
    }

    /**
     * Fetches all main interest rates.
     *
     * @return all main interest rates
     */
    @PublicAPI
    @NotNull
    public static List<MainInterestRate> getAll() {
        return Http.getMultipleObjectFromApi(MainInterestRate.class, "/api/maininterestrate/");
    }

    /**
     * @return the unique id
     */
    @PublicAPI
    public String getId() {
        return id.getValue();
    }

    /**
     * @return the interest rate
     */
    @PublicAPI
    public Double getValue() {
        return value.getValue();
    }

    /**
     * @return the start date
     */
    @PublicAPI
    public ZonedDateTime getDate() {
        return date.getValue();
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

        return id.getValue() != null ? id.getValue().equals(that.id.getValue())
            : that.id.getValue() == null;

    }

    @Override
    public int hashCode() {
        return id.getValue() != null ? id.getValue().hashCode() : 0;
    }
}
