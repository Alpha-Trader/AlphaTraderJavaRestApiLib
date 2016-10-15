package com.alphatrader.rest;

import com.alphatrader.rest.util.Config;
import com.alphatrader.rest.util.Http;
import com.alphatrader.rest.util.LocalDateTimeDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author frangelo
 */
public class Notification {
    /**
     * Content wrapper class used for gson deserialization.
     *
     * @author Christopher Guckes
     * @version 1.0
     */
    private static final class Content {
        String filledString = "";
    }

    /**
     * Gson instance for deserialization.
     */
    private static final Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer()).create();

    /**
     * List type for gson deserialization.
     */
    private static final Type listType = new TypeToken<ArrayList<Notification>>(){}.getType();

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
        } catch (UnirestException e) {
            System.err.println("Error fetching notifications: " + e.getMessage());
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
    public static void markAllAsRead() {
        try {
            Unirest.put(Config.getInstance().getApiUrl() + "/api/notifications/read/")
                .header("accept", "*/*").header("Authorization", "Bearer " + Config.getInstance().getUser().getToken())
                .header("X-Authorization", "e1d149fb-0b2a-4cf5-9ef7-17749bf9d144").asJson();
        } catch (UnirestException ue) {
            System.err.println("Error marking notifications as read.");
            ue.printStackTrace();
        }
    }

    /**
     * The content of the notification.
     */
    private final Content content = new Content();

    /**
     * The date and time the notification was created.
     */
    private LocalDateTime date;

    /**
     * Creates a unread Notification with the given parameters
     *
     * @param date the date this notification was created
     * @param message the content.
     */
    public Notification(LocalDateTime date, String message) {
        this.date = date;
        this.content.filledString = message;
    }

    @Override
    public String toString() {
        return "Notification{" +
            "content='" + content.filledString + '\'' +
            ", date=" + date +
            '}';
    }

    /**
     * @return the Message
     */
    public String getMessage() {
        return content.filledString;
    }

}


