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

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents an event in the game.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0.0
 */
public class Event {
    /**
     * The logger for this class.
     */
    private static final Log log = LogFactory.getLog(Event.class);

    /**
     * Gson instance for deserialization.
     */
    private static final Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class,
        new LocalDateTimeDeserializer()).create();

    /**
     * List type for gson deserialization.
     */
    private static final java.lang.reflect.Type listType = new TypeToken<ArrayList<Event>>() {
    }.getType();

    /**
     * The content of the event.
     */
    private final Object content = null;

    /**
     * The event type.
     */
    private final Type type = null;

    /**
     * The date the event was triggered.
     */
    private final LocalDateTime date = null;

    /**
     * The list of realms.
     */
    private final List<String> realms = null;

    /**
     * @return all non-persistent events in the game
     */
    @NotNull
    public static List<Event> getAllEvents() {
        return getMultipleEventsFromApi("events/");
    }

    /**
     * @param fromDate the lower boundary date
     * @return all non-persistent events in the game from the provided date
     */
    @NotNull
    public static List<Event> getAllEvents(LocalDateTime fromDate) {
        return getMultipleEventsFromApi("events/?afterDate=" + fromDate.atZone(ZoneId.systemDefault())
            .toInstant().toEpochMilli());
    }

    /**
     * @return all non-persistent events for this user
     */
    @NotNull
    public static List<Event> getAllUserEvents() {
        return getMultipleEventsFromApi("events/user/");
    }

    /**
     * @param fromDate the lower boundary date
     * @return all non-persistent events for this user from the provided date
     */
    @NotNull
    public static List<Event> getAllUserEvents(LocalDateTime fromDate) {
        return getMultipleEventsFromApi("events/user/?afterDate=" + fromDate.atZone(ZoneId
            .systemDefault()).toInstant().toEpochMilli());
    }

    /**
     * @param type the type of event to list
     * @return all non-persistent events for this user by type
     */
    @NotNull
    public static List<Event> getEventsByType(Type type) {
        return getMultipleEventsFromApi("search/events/" + type.toString());
    }

    /**
     * @param type     the type of event to list
     * @param fromDate the lower boundary date
     * @return all non-persistent events in the game from the provided date
     */
    @NotNull
    public static List<Event> getEventsByType(Type type, LocalDateTime fromDate) {
        return getMultipleEventsFromApi("search/events/" + type.toString() + "?afterDate=" + fromDate
            .atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
    }

    /**
     * Search for events using the given fulltext.
     *
     * @param fulltext the search string
     * @return the events matching the search string
     */
    @NotNull
    public static List<Event> searchEvents(String fulltext) {
        return getMultipleEventsFromApi("search/events/" + fulltext);
    }

    /**
     * @param fulltext the search string
     * @param fromDate the lower boundary date
     * @return all non-persistent events in the game from the provided date
     */
    @NotNull
    public static List<Event> searchEvents(String fulltext, LocalDateTime fromDate) {
        return getMultipleEventsFromApi("search/events/" + fulltext + "?afterDate=" + fromDate
            .atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
    }

    /**
     * Gets events based on the provided suffix and date.
     *
     * @param suffix the API suffix for the events
     * @return the list of requested events
     */
    @NotNull
    private static List<Event> getMultipleEventsFromApi(String suffix) {
        List<Event> myReturn = new ArrayList<>();

        try {
            HttpResponse<JsonNode> response = Http.getInstance().get("/api/" + suffix);
            if (response != null && response.getStatus() == 200) {
                myReturn.addAll(gson.fromJson(response.getBody().getArray().toString(), listType));
            }
        }
        catch (UnirestException ue) {
            log.error("Error fetching events: " + ue.getMessage());
            StringWriter stringWriter = new StringWriter();
            ue.printStackTrace(new PrintWriter(stringWriter));
            log.debug(stringWriter.toString());
        }

        return myReturn;
    }

    /**
     * @return the contained object
     */
    public Object getContent() {
        return content;
    }

    /**
     * @return the event type
     */
    public Type getType() {
        return type;
    }

    /**
     * @return the event trigger date
     */
    public LocalDateTime getDate() {
        return date;
    }

    /**
     * @return the realms
     */
    public List<String> getRealms() {
        return realms;
    }

    @Override
    public String toString() {
        return "Event{"
            + "content=" + content
            + ", type=" + type
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

        Event event = (Event) o;

        if (content != null ? !content.equals(event.content) : event.content != null) {
            return false;
        }
        if (type != event.type) {
            return false;
        }
        if (date != null ? !date.equals(event.date) : event.date != null) {
            return false;
        }
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return realms.equals(event.realms);
    }

    @Override
    public int hashCode() {
        int result = content != null ? content.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + realms.hashCode();
        return result;
    }

    /**
     * All {@link Event} types.
     */
    public enum Type {
        NEW_USER, NEW_SECURITY, NEW_COMPANY, COMPANY_LIQUIDATED, SECURITY_TRADED, ORDER_ACCEPTED,
        ORDER_DELETED, ORDER_FILLED, LIQUIDATION_POLL_INITIATED, CASH_OUT_POLL_INITIATED,
        EMPLOY_CEO_POLL_INITIATED, SALARY_PAYMENT, CEO_EMPLOYED, CEO_DISMISSED, SYSTEM_STARTED,
        FIXED_INCOME_REPAID
    }
}
