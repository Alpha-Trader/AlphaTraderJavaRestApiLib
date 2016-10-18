package com.alphatrader.rest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Represents a translateable text message object in the game.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0.0
 */
@SuppressWarnings("ConstantConditions")
public class Message {
    /**
     * The logger for this class.
     */
    private static final Log log = LogFactory.getLog(MainInterestRate.class);

    /**
     * The i18n string.
     */
    private final String message = null;

    /**
     * The substitutions for the i18n string.
     */
    private final String[] substitutions = null;

    /**
     * The substituded message.
     */
    private final String filledString = null;

    /**
     * Fetches all chat messages from the given chat.
     *
     * @param chatId the id of the chat room
     * @return the list of all messages in that room
     */
    @NotNull
    public static List<Message> getChatMessages(String chatId) {
        return getChatMessages(chatId, null, null);
    }

    /**
     * Fetches all chat messages from the given chat.
     *
     * @param chat the chat room
     * @return the list of all messages in that room
     */
    @NotNull
    public static List<Message> getChatMessages(Chat chat) {
        return getChatMessages(chat.getId(), null, null);
    }

    /**
     * Returns all messages from the given chat younger than the provided date.
     *
     * @param chatId the id of the chat room
     * @param from   the lower date boundary
     * @return the list of messages in that room
     */
    @NotNull
    public static List<Message> getChatMessages(String chatId, ZonedDateTime from) {
        return getChatMessages(chatId, from, null);
    }

    /**
     * Returns all messages from the given chat younger than the provided date.
     *
     * @param chat the id of the chat room
     * @param from the lower date boundary
     * @return the list of messages in that room
     */
    @NotNull
    public static List<Message> getChatMessages(Chat chat, ZonedDateTime from) {
        return getChatMessages(chat.getId(), from, null);
    }

    /**
     * Fetches all chat messages from the given chat room in between the two dates.
     *
     * @param chatId the id of the chat room
     * @param from   the lower date boundary
     * @param to     the upper date boundary
     * @return the list of messages
     */
    @NotNull
    public static List<Message> getChatMessages(String chatId, ZonedDateTime from, ZonedDateTime to) {
        String suffix = chatId;

        List<String> parameters = new ArrayList<>();
        if (from != null) {
            parameters.add("afterDate=" + from.toInstant().toEpochMilli());
        }
        if (from != null) {
            parameters.add("beforeDate=" + to.toInstant().toEpochMilli());
        }

        if (parameters.size() > 0) {
            suffix += "?" + String.join("&", parameters);
        }

        return getFromApi("messages/chat/" + suffix);
    }

    /**
     * Fetches all chat messages from the given chat room in between the two dates.
     *
     * @param chat the id of the chat room
     * @param from the lower date boundary
     * @param to   the upper date boundary
     * @return the list of messages
     */
    @NotNull
    public static List<Message> getChatMessages(Chat chat, ZonedDateTime from, ZonedDateTime to) {
        return getChatMessages(chat.getId(), from, to);
    }

    @NotNull
    public static List<Message> getUnreadMessages() {
        return getFromApi("messages/unread/");
    }

    /**
     * Wrapper function for the api access since most calls are very similar.
     *
     * @param suffix the api suffix to fetch from
     * @return the list of messages
     */
    @NotNull
    private static List<Message> getFromApi(String suffix) {
        return Http.getMultipleObjectFromApi(Message.class, "/api/" + suffix);
    }

    /**
     * Fetches the message with the given id from the API.
     *
     * @param messageId the id of the message to fetch
     * @return the message
     */
    @Nullable
    private static Message getById(String messageId) {
        return Http.getSingleObjectFromApi(Message.class, "/api/messages/" + messageId);
    }

    /**
     * @return the i18n string.
     */
    public String getMessage() {
        return message;
    }

    /**
     * @return the substitutions
     */
    public String[] getSubstitutions() {
        return substitutions;
    }

    /**
     * @return the substituted message
     */
    public String getFilledString() {
        return filledString;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Message message1 = (Message) o;

        if (message != null ? !message.equals(message1.message) : message1.message != null) {
            return false;
        }
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(substitutions, message1.substitutions)) {
            return false;
        }
        return filledString != null ? filledString.equals(message1.filledString)
            : message1.filledString == null;

    }

    @Override
    public int hashCode() {
        int result = message != null ? message.hashCode() : 0;
        result = 31 * result + Arrays.hashCode(substitutions);
        result = 31 * result + (filledString != null ? filledString.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Message{"
            + "message='" + message + '\''
            + ", substitutions=" + Arrays.toString(substitutions)
            + ", filledString='" + filledString + '\''
            + '}';
    }
}
