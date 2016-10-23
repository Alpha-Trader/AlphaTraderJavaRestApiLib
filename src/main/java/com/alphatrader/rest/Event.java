package com.alphatrader.rest;

import org.jetbrains.annotations.NotNull;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * Represents an event in the game.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0.0
 */
@SuppressWarnings("ConstantConditions")
public class Event {
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
    private final ZonedDateTime date = null;

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
    public static List<Event> getAllEvents(ZonedDateTime fromDate) {
        return getMultipleEventsFromApi("events/?afterDate=" + fromDate.toInstant().toEpochMilli());
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
    public static List<Event> getAllUserEvents(ZonedDateTime fromDate) {
        return getMultipleEventsFromApi("events/user/?afterDate=" + fromDate.toInstant().toEpochMilli());
    }

    /* TEMPORARILY DISABLED UNTIL UNDERSTOOD BETTER

    / **
     * @param type the type of event to list
     * @return all non-persistent events for this user by type
     * /
    @NotNull
    public static List<Event> getEventsByType(Type type) {
        return getMultipleEventsFromApi("search/events/" + type.toString());
    }

    / **
     * @param type     the type of event to list
     * @param fromDate the lower boundary date
     * @return all non-persistent events in the game from the provided date
     * /
    @NotNull
    public static List<Event> getEventsByType(Type type, ZonedDateTime fromDate) {
        return getMultipleEventsFromApi("search/events/" + type.toString() + "?afterDate=" + fromDate
            .toInstant().toEpochMilli());
    }

    / **
     * Search for events using the given fulltext.
     *
     * @param fulltext the search string
     * @return the events matching the search string
     * /
    @NotNull
    public static List<Event> searchEvents(String fulltext) {
        return getMultipleEventsFromApi("search/events/" + fulltext);
    }

    / **
     * @param fulltext the search string
     * @param fromDate the lower boundary date
     * @return all non-persistent events in the game from the provided date
     * /
    @NotNull
    public static List<Event> searchEvents(String fulltext, ZonedDateTime fromDate) {
        return getMultipleEventsFromApi("search/events/" + fulltext + "?afterDate=" + fromDate
            .toInstant().toEpochMilli());
    }

    */

    /**
     * Gets events based on the provided suffix and date.
     *
     * @param suffix the API suffix for the events
     * @return the list of requested events
     */
    @NotNull
    private static List<Event> getMultipleEventsFromApi(String suffix) {
        return Http.getMultipleObjectFromApi(Event.class, "/api/" + suffix);
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
    public ZonedDateTime getDate() {
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
