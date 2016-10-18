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

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a chat room in the game.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0.0
 */
public class Chat {
    /**
     * The logger for this class.
     */
    private static final Log log = LogFactory.getLog(Chat.class);

    /**
     * Gson instance for deserialization.
     */
    private static final Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class,
        new LocalDateTimeDeserializer()).create();

    /**
     * List type for gson deserialization.
     */
    private static final Type listType = new TypeToken<ArrayList<Chat>>() {
    }.getType();
    /**
     * The date the chat was created.
     */

    private final LocalDateTime dateCreated = null;
    /**
     * The last message sent in the chat.
     */
    private final ChatMessage lastMessage = null;
    /**
     * All participants in this chat room.
     */
    private final List<User> participants = null;
    /**
     * The readonly flag. If set, only the owner can send messages.
     */
    private final Boolean readonly = null;
    /**
     * The name of the chat.
     */
    private final String chatName = null;
    /**
     * The owner of the chat.
     */
    private final User owner = null;
    /**
     * The unique id of the chat.
     */
    private final String id = null;

    /**
     * Lists all chats for the logged-in user.
     *
     * @return a list of all chats for this user
     */
    public static List<Chat> getAllChatsForThisUser() {
        List<Chat> myReturn = new ArrayList<>();

        try {
            HttpResponse<JsonNode> response = Http.getInstance().get("/api/chats");

            if (response != null && response.getStatus() == 200) {
                myReturn = gson.fromJson(response.getBody().getArray().toString(), listType);
            }
        }
        catch (UnirestException ue) {
            log.error("Error loading chats: " + ue.getMessage());
            StringWriter stringWriter = new StringWriter();
            ue.printStackTrace(new PrintWriter(stringWriter));
            log.debug(stringWriter.toString());
        }

        return myReturn;
    }

    /**
     * Lists all chats with unread messages for the logged-in user.
     *
     * @return a list of all chats with unread messages for this user
     */
    public static List<Chat> getAllUnreadChatsForThisUser() {
        List<Chat> myReturn = new ArrayList<>();

        try {
            HttpResponse<JsonNode> response = Http.getInstance().get("/api/chats/unread");

            if (response != null && response.getStatus() == 200) {
                myReturn = gson.fromJson(response.getBody().getArray().toString(), listType);
            }
        }
        catch (UnirestException ue) {
            log.error("Error loading chats: " + ue.getMessage());
            StringWriter stringWriter = new StringWriter();
            ue.printStackTrace(new PrintWriter(stringWriter));
            log.debug(stringWriter.toString());
        }

        return myReturn;
    }

    /**
     * Fetches a chat by it's id.
     *
     * @param chatId the chat id to look for
     * @return the chat with the given id
     */
    public static Chat getChatById(String chatId) {
        Chat myReturn = null;

        try {
            HttpResponse<JsonNode> response = Http.getInstance().get("/api/chats/" + chatId);

            if (response != null && response.getStatus() == 200) {
                myReturn = gson.fromJson(response.getBody().getObject().toString(), Chat.class);
            }
        }
        catch (UnirestException ue) {
            log.error("Error loading chat: " + ue.getMessage());
            StringWriter stringWriter = new StringWriter();
            ue.printStackTrace(new PrintWriter(stringWriter));
            log.debug(stringWriter.toString());
        }

        return myReturn;
    }

    /**
     * @return the creation date
     */
    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    /**
     * @return the last message
     */
    public ChatMessage getLastMessage() {
        return lastMessage;
    }

    /**
     * @return a list of all participants
     */
    public List<User> getParticipants() {
        return participants;
    }

    /**
     * @return true if the chat is readonly
     */
    public Boolean getReadonly() {
        return readonly;
    }

    /**
     * @return the name of the chat
     */
    public String getChatName() {
        return chatName;
    }

    /**
     * @return the owner of the chat
     */
    public User getOwner() {
        return owner;
    }

    /**
     * @return the unique identifier
     */
    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Chat{"
            + "dateCreated=" + dateCreated
            + ", lastMessage=" + lastMessage
            + ", participants=" + participants
            + ", readonly=" + readonly
            + ", chatName='" + chatName + '\''
            + ", owner=" + owner
            + ", id='" + id + '\''
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

        Chat chat = (Chat) o;

        if (readonly != null ? !readonly.equals(chat.readonly) : chat.readonly != null) {
            return false;
        }
        if (owner != null ? !owner.equals(chat.owner) : chat.owner != null) {
            return false;
        }
        return id != null ? id.equals(chat.id) : chat.id == null;

    }

    @Override
    public int hashCode() {
        int result = readonly != null ? readonly.hashCode() : 0;
        result = 31 * result + (owner != null ? owner.hashCode() : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        return result;
    }
}
