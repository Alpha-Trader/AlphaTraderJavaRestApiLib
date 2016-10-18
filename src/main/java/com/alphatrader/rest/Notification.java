package com.alphatrader.rest;

import com.alphatrader.rest.util.Http;
import com.alphatrader.rest.util.LocalDateTimeDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author frangelo
 */
public class Notification {
    /**
     * The logger for this class.
     */
    private static final Log log = LogFactory.getLog(Notification.class);

    /**
     * Gson instance for deserialization.
     */
    private static final Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class,
        new LocalDateTimeDeserializer()).create();
    /**
     * List type for gson deserialization.
     */
    private static final Type listType = new TypeToken<ArrayList<Notification>>() {
    }.getType();
    /**
     * The content of the notification.
     */
    private final Message content = null;
    /**
     * The date and time the notification was created.
     */
    private final LocalDateTime date = null;

    /**
     * Fetches all unread events of the user and marks them as read
     *
     * @return all unread events
     */
    public static List<Notification> getUnreadNotifications() {
        List<Notification> myReturn = new ArrayList<>();

        try {
            HttpResponse<JsonNode> response = Http.getInstance().get("/api/notifications/unread/");
            String notifications = response.getBody().getArray().toString();
            myReturn = gson.fromJson(notifications, listType);
        }
        catch (UnirestException ue) {
            log.error("Error fetching notifications: " + ue.getMessage());
            StringWriter stringWriter = new StringWriter();
            ue.printStackTrace(new PrintWriter(stringWriter));
            log.debug(stringWriter.toString());
        }

        return myReturn;
    }

    /**
     * Creates a Notification from the api json answers and marks it as read
     */
    public static Notification createFromJson(String json) {
        return gson.fromJson(json, Notification.class);
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


