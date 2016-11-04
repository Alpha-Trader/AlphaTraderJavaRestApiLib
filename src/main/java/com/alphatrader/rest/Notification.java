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
    @PublicAPI
    @NotNull
    public static List<Notification> getMultipleNotificationsFromApi(String suffix) {
        return Http.getMultipleObjectFromApi(Notification.class, "/api/notifications" + suffix);
    }

    /**
     * @return all unread notifications for the logged in user
     */
    @PublicAPI
    @NotNull
    public static List<Notification> getUnreadNotifications() {
        return getMultipleNotificationsFromApi("/unread/");
    }

    /**
     * @return all notifications for the logged in user
     */
    @PublicAPI
    @NotNull
    public static List<Notification> getNotifications() {
        return getMultipleNotificationsFromApi("");
    }

    /**
     * @return the message
     */
    @PublicAPI
    public Message getMessage() {
        return content.getValue();
    }

    /**
     * @return the date
     */
    @PublicAPI
    public ZonedDateTime getDate() {
        return date.getValue();
    }

    /**
     * @return the notification subject
     */
    @PublicAPI
    public Message getSubject() {
        return subject.getValue();
    }

    /**
     * @return the notification's receiver
     */
    @PublicAPI
    public User getReceiver() {
        return receiver.getValue();
    }

    /**
     * @return true, if the notification was read
     */
    @PublicAPI
    public Boolean isReadByReceiver() {
        return readByReceiver.getValue();
    }

    /**
     * @return the unique id
     */
    @PublicAPI
    public String getId() {
        return id.getValue();
    }

    /**
     * @return the message property.
     */
    @PublicAPI
    public ReadOnlyObjectProperty<Message> messageProperty() {
        return content;
    }

    /**
     * @return the date property.
     */
    @PublicAPI
    public ReadOnlyObjectProperty<ZonedDateTime> dateProperty() {
        return date;
    }

    /**
     * @return the subject property.
     */
    @PublicAPI
    public ReadOnlyObjectProperty<Message> subjectProperty() {
        return subject;
    }

    /**
     * @return the receiver property.
     */
    @PublicAPI
    public ReadOnlyObjectProperty<User> receiverProperty() {
        return receiver;
    }

    /**
     * @return the read by receiver property.
     */
    @PublicAPI
    public ReadOnlyBooleanProperty readByReceiverProperty() {
        return readByReceiver;
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


