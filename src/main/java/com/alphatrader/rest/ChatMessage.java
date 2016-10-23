package com.alphatrader.rest;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a chat message in the game.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0.0
 */
@SuppressWarnings("ConstantConditions")
public class ChatMessage {
    /**
     * The date the message was sent.
     */
    private final ZonedDateTime dateSent = null;

    /**
     * The id of the chat the message was sent in.
     */
    private final String chatId = null;

    /**
     * The user who sent the message.
     */
    private final User sender = null;

    /**
     * Is the message unread?
     */
    private final Boolean read = null;

    /**
     * The unique message id.
     */
    private final String id = null;

    /**
     * The content of the message.
     */
    private final String content = null;

    /**
     * Fetches all chat messages from the given chat.
     *
     * @param chatId the id of the chat room
     * @return the list of all messages in that room
     */
    @NotNull
    public static List<ChatMessage> getChatMessages(String chatId) {
        return getChatMessages(chatId, null, null);
    }

    /**
     * Fetches all chat messages from the given chat.
     *
     * @param chat the chat room
     * @return the list of all messages in that room
     */
    @NotNull
    public static List<ChatMessage> getChatMessages(Chat chat) {
        return getChatMessages(chat.getId());
    }

    /**
     * Returns all messages from the given chat younger than the provided date.
     *
     * @param chatId the id of the chat room
     * @param from   the lower date boundary
     * @return the list of messages in that room
     */
    @NotNull
    public static List<ChatMessage> getChatMessages(String chatId, ZonedDateTime from) {
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
    public static List<ChatMessage> getChatMessages(Chat chat, ZonedDateTime from) {
        return getChatMessages(chat.getId(), from);
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
    public static List<ChatMessage> getChatMessages(String chatId, ZonedDateTime from, ZonedDateTime to) {
        String suffix = chatId;

        List<String> parameters = new ArrayList<>();
        if (from != null) {
            parameters.add("afterDate=" + from.toInstant().toEpochMilli());
        }
        if (to != null) {
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
    public static List<ChatMessage> getChatMessages(Chat chat, ZonedDateTime from, ZonedDateTime to) {
        return getChatMessages(chat.getId(), from, to);
    }

    @NotNull
    public static List<ChatMessage> getUnreadMessages() {
        return getFromApi("messages/unread");
    }

    /**
     * Wrapper function for the api access since most calls are very similar.
     *
     * @param suffix the api suffix to fetch from
     * @return the list of messages
     */
    @NotNull
    private static List<ChatMessage> getFromApi(String suffix) {
        return Http.getMultipleObjectFromApi(ChatMessage.class, "/api/" + suffix);
    }

    /**
     * Fetches the message with the given id from the API.
     *
     * @param messageId the id of the message to fetch
     * @return the message
     */
    @Nullable
    public static ChatMessage getById(String messageId) {
        return Http.getSingleObjectFromApi(ChatMessage.class, "/api/messages/" + messageId);
    }

    /**
     * @return the date the message was sent.
     */
    public ZonedDateTime getDateSent() {

        return dateSent;
    }

    /**
     * @return the chat id.
     */
    public String getChatId() {
        return chatId;
    }

    /**
     * @return the sender
     */
    public User getSender() {
        return sender;
    }

    /**
     * @return true if the message was read.
     */
    public Boolean isRead() {
        return read;
    }

    /**
     * @return the unique id of the message.
     */
    public String getId() {
        return id;
    }

    /**
     * @return the message content.
     */
    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "ChatMessage{"
            + "dateSent=" + dateSent
            + ", chatId='" + chatId + '\''
            + ", sender=" + sender
            + ", read=" + read
            + ", id='" + id + '\''
            + ", content='" + content + '\''
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

        ChatMessage that = (ChatMessage) o;

        if (chatId != null ? !chatId.equals(that.chatId) : that.chatId != null) {
            return false;
        }
        return id != null ? id.equals(that.id) : that.id == null;

    }

    @Override
    public int hashCode() {
        int result = chatId != null ? chatId.hashCode() : 0;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        return result;
    }
}
