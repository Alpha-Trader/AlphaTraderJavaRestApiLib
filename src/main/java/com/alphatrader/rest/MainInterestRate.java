package com.alphatrader.rest;

import com.alphatrader.rest.util.LocalDateTimeDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0.0
 */
public class MainInterestRate {
    /**
     * The logger for this class.
     */
    private static final Log log = LogFactory.getLog(MainInterestRate.class);

    /**
     * Gson instance for deserialization.
     */
    private static final Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class,
        new LocalDateTimeDeserializer()).create();

    /**
     * List type for gson deserialization.
     */
    private static final Type listType = new TypeToken<ArrayList<MainInterestRate>>() {
    }.getType();

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
    private final LocalDateTime dateTime = null;

    /**
     * Fetches the latest main interest rate.
     *
     * @return the latest main interest rate
     */
    @Nullable
    private static MainInterestRate getCurrent() {
        MainInterestRate myReturn = null;

        try {
            HttpResponse<JsonNode> response = Http.getInstance().get("/api/maininterrestrate/latest/");

            if (response != null && response.getStatus() == 200) {
                myReturn = gson.fromJson(response.getBody()
                    .getObject()
                    .toString(), MainInterestRate.class);
            }
        }
        catch (UnirestException ue) {
            log.error("Error fetching main interest rate: " + ue.getMessage());
            StringWriter stringWriter = new StringWriter();
            ue.printStackTrace(new PrintWriter(stringWriter));
            log.debug(stringWriter.toString());
        }

        return myReturn;
    }

    /**
     * Fetches all main interest rates.
     *
     * @return all main interest rates
     */
    @NotNull
    private static List<MainInterestRate> getAll() {
        List<MainInterestRate> myReturn = new ArrayList<>();

        try {
            HttpResponse<JsonNode> response = Http.getInstance().get("/api/maininterrestrate/");

            if (response != null && response.getStatus() == 200) {
                myReturn.addAll(gson.fromJson(response.getBody()
                    .getArray()
                    .toString(), listType));
            }
        }
        catch (UnirestException ue) {
            log.error("Error fetching current main interest rate: " + ue.getMessage());
            StringWriter stringWriter = new StringWriter();
            ue.printStackTrace(new PrintWriter(stringWriter));
            log.debug(stringWriter.toString());
        }

        return myReturn;
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
    public LocalDateTime getDateTime() {
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
