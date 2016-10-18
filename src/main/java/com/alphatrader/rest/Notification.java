package com.alphatrader.rest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jetbrains.annotations.NotNull;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * Represents a notification in the game.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0
 */
@SuppressWarnings("ConstantConditions")
public class Notification {
    /**
     * The logger for this class.
     */
    private static final Log log = LogFactory.getLog(Notification.class);

    /**
     * The content of the notification.
     */
    private final Message content = null;
    /**
     * The date and time the notification was created.
     */
    private final ZonedDateTime date = null;

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
        return getMultipleNotificationsFromApi("/unread");
    }

    /**
     * @return all notifications for the logged in user
     */
    @NotNull
    public static List<Notification> getNotifications() {
        return getMultipleNotificationsFromApi("");
    }

    /**
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

    @Override
    public String toString() {
        return "Notification{"
            + "content='" + content.getFilledString() + '\''
            + ", date=" + date
            + '}';
    }

    /**
     * @return the Message
     */
    public String getMessage() {
        return content.getFilledString();
    }
}


