package com.alphatrader.rest;

import javafx.beans.property.*;
import org.jetbrains.annotations.NotNull;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * Represents a notification in the game.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0
 */
public class Notification {
   /**
     * The content of the notification.
     */
    private final ObjectProperty<Message> content = new SimpleObjectProperty<>();

    /**
     * The date and time the notification was created.
     */
    private final ObjectProperty<ZonedDateTime> date = new SimpleObjectProperty<>();

    /**
     * The subject of the notification.
     */
    private final ObjectProperty<Message> subject = new SimpleObjectProperty<>();

    /**
     * The notification receiver.
     */
    private final ObjectProperty<User> receiver = new SimpleObjectProperty<>();

    /**
     * True, if the receiver has read the message.
     */
    private final BooleanProperty readByReceiver = new SimpleBooleanProperty();

    /**
     * The unique notification id.
     */
    private final StringProperty id = new SimpleStringProperty();

    /**
     * Fetches all unread events of the user and marks them as read
     *
     * @param suffix the api endpoint suffix
     * @return all unread events
     */
    @NotNull
    public static List<Notification> getMultipleNotificationsFromApi(String suffix) {
        return Http.getMultipleObjectFromApi(Notification.class, "/api/notifications" + suffix);
    }

    /**
     * @return all unread notifications for the logged in user
     */
    @NotNull
    public static List<Notification> getUnreadNotifications() {
        return getMultipleNotificationsFromApi("/unread/");
    }

    /**
     * @return all notifications for the logged in user
     */
    @NotNull
    public static List<Notification> getNotifications() {
        return getMultipleNotificationsFromApi("");
    }

    /*
     * Marks all notifications as read.
     */
    /*public static void markAllAsRead() {
        try {
            Unirest.put(ApiLibConfig.getInstance().getApiUrl() + "/api/notifications/read/")
                .header("accept", "*/
    /*").header("Authorization", "Bearer " + ApiLibConfig.getInstance()
                .getUser().getToken())
                .header("X-Authorization", "e1d149fb-0b2a-4cf5-9ef7-17749bf9d144").asJson();
        }
        catch (UnirestException ue) {
            log.error("Error fetching marking notifications as read: " + ue.getMessage());
            StringWriter stringWriter = new StringWriter();
            ue.printStackTrace(new PrintWriter(stringWriter));
            log.debug(stringWriter.toString());
        }
    } */

    /**
     * @return the message
     */
    public Message getMessage() {
        return content.getValue();
    }

    /**
     * @return the date
     */
    public ZonedDateTime getDate() {
        return date.getValue();
    }

    /**
     * @return the notification subject
     */
    public Message getSubject() {
        return subject.getValue();
    }

    /**
     * @return the notification's receiver
     */
    public User getReceiver() {
        return receiver.getValue();
    }

    /**
     * @return true, if the notification was read
     */
    public Boolean isReadByReceiver() {
        return readByReceiver.getValue();
    }

    /**
     * @return the unique id
     */
    public String getId() {
        return id.getValue();
    }

    @Override
    public String toString() {
        return "Notification{"
            + "content='" + content.getValue().getFilledString() + '\''
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

        Notification that = (Notification) o;

        return id.getValue() != null ? id.getValue().equals(that.id.getValue())
            : that.id.getValue() == null;

    }

    @Override
    public int hashCode() {
        return id.getValue() != null ? id.getValue().hashCode() : 0;
    }
}


