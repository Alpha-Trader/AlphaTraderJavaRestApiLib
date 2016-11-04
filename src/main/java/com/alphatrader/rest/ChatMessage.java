package com.alphatrader.rest;

import javafx.beans.property.*;
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
public class ChatMessage {
    /**
     * The date the message was sent.
     */
    private final ObjectProperty<ZonedDateTime> dateSent = new SimpleObjectProperty<>();

    /**
     * The id of the chat the message was sent in.
     */
    private final StringProperty chatId = new SimpleStringProperty();

    /**
     * The user who sent the message.
     */
    private final ObjectProperty<User> sender = new SimpleObjectProperty<>();

    /**
     * Is the message unread?
     */
    private final BooleanProperty read = new SimpleBooleanProperty();

    /**
     * The unique message id.
     */
    private final StringProperty id = new SimpleStringProperty();

    /**
     * The content of the message.
     */
    private final StringProperty content = new SimpleStringProperty();

    /**
     * Fetches all chat messages from the given chat.
     *
     * @param chatId the id of the chat room
     * @return the list of all messages in that room
     */
    @PublicAPI
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
    @PublicAPI
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
    @PublicAPI
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
    @PublicAPI
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
    @PublicAPI
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

        if (!parameters.isEmpty()) {
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
    @PublicAPI
    @NotNull
    public static List<ChatMessage> getChatMessages(Chat chat, ZonedDateTime from, ZonedDateTime to) {
        return getChatMessages(chat.getId(), from, to);
    }

    @PublicAPI
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
    @PublicAPI
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
    @PublicAPI
    @Nullable
    public static ChatMessage getById(String messageId) {
        return Http.getSingleObjectFromApi(ChatMessage.class, "/api/messages/" + messageId);
    }

    /**
     * @return the date the message was sent.
     */
    @PublicAPI
    public ZonedDateTime getDateSent() {
        return dateSent.getValue();
    }

    /**
     * @return the chat id.
     */
    @PublicAPI
    public String getChatId() {
        return chatId.getValue();
    }

    /**
     * @return the sender
     */
    @PublicAPI
    public User getSender() {
        return sender.getValue();
    }

    /**
     * @return true if the message was read.
     */
    @PublicAPI
    public Boolean isRead() {
        return read.getValue();
    }

    /**
     * @return the unique id of the message.
     */
    @PublicAPI
    public String getId() {
        return id.getValue();
    }

    /**
     * @return the message content.
     */
    @PublicAPI
    public String getContent() {
        return content.getValue();
    }

    /**
     * @return the sent date property.
     */
    @PublicAPI
    public ReadOnlyObjectProperty<ZonedDateTime> dateSentProperty() {
        return dateSent;
    }

    /**
     * @return the chat id property.
     */
    @PublicAPI
    public ReadOnlyStringProperty chatIdProperty() {
        return chatId;
    }

    /**
     * @return the sender property.
     */
    @PublicAPI
    public ReadOnlyObjectProperty<User> senderProperty() {
        return sender;
    }

    /**
     * @return the read property.
     */
    @PublicAPI
    public ReadOnlyBooleanProperty readProperty() {
        return read;
    }

    /**
     * @return the id property.
     */
    @PublicAPI
    public ReadOnlyStringProperty idProperty() {
        return id;
    }

    /**
     * @return the content property.
     */
    @PublicAPI
    public ReadOnlyStringProperty contentProperty() {
        return content;
    }

    @Override
    public String toString() {
        return "ChatMessage{"
            + "dateSent=" + dateSent.getValue()
            + ", chatId='" + chatId.getValueSafe() + '\''
//            + ", sender=" + sender.getValue()
            + ", read=" + read.getValue()
            + ", id='" + id.getValueSafe() + '\''
            + ", content='" + content.getValueSafe() + '\''
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

        return chatId.getValue() != null ? chatId.getValue().equals(that.chatId.getValue())
            : that.chatId.getValue() == null && (id.getValue() != null ? id.getValue().equals(
                that.id.getValue()) : that.id.getValue() == null);

    }

    @Override
    public int hashCode() {
        int result = chatId.getValue() != null ? chatId.getValue().hashCode() : 0;
        result = 31 * result + (id.getValue() != null ? id.getValue().hashCode() : 0);
        return result;
    }
}
