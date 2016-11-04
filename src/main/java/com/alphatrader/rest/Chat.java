package com.alphatrader.rest;

import javafx.beans.property.*;
import javafx.collections.ObservableList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * Represents a chat room in the game.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0.0
 */
public class Chat {
    /**
     * The date the chat was created.
     */
    private final ObjectProperty<ZonedDateTime> dateCreated = new SimpleObjectProperty<>();
    /**
     * The last message sent in the chat.
     */
    private final ObjectProperty<ChatMessage> lastMessage = new SimpleObjectProperty<>();
    /**
     * All participants in this chat room.
     */
    private final ObservableList<User> participants = new SimpleListProperty<>();
    /**
     * The readonly flag. If set, only the owner can send messages.
     */
    private final BooleanProperty readonly = new SimpleBooleanProperty();
    /**
     * The name of the chat.
     */
    private final StringProperty chatName = new SimpleStringProperty();
    /**
     * The owner of the chat.
     */
    private final ObjectProperty<User> owner = new SimpleObjectProperty<>();
    /**
     * The unique id of the chat.
     */
    private final StringProperty id = new SimpleStringProperty();

    /**
     * Lists all chats for the logged-in user.
     *
     * @return a list of all chats for this user
     */
    @PublicAPI
    @NotNull
    public static List<Chat> getAllChatsForThisUser() {
        return getMultipleChatsFromApi("");
    }

    /**
     * Lists all chats with unread messages for the logged-in user.
     *
     * @return a list of all chats with unread messages for this user
     */
    @PublicAPI
    @NotNull
    public static List<Chat> getAllUnreadChatsForThisUser() {
        return getMultipleChatsFromApi("/unread");
    }

    /**
     * Wrapper function for fetching multiple Chat objects from the API.
     *
     * @param suffix the api endpoint suffix
     * @return the list of objects
     */
    @PublicAPI
    @NotNull
    private static List<Chat> getMultipleChatsFromApi(String suffix) {
        return Http.getMultipleObjectFromApi(Chat.class, "/api/chats" + suffix);
    }

    /**
     * Fetches a chat by it's id.
     *
     * @param chatId the chat id to look for
     * @return the chat with the given id
     */
    @PublicAPI
    @Nullable
    public static Chat getChatById(String chatId) {
        return Http.getSingleObjectFromApi(Chat.class, "/api/chats/" + chatId);
    }

    /**
     * @return the creation date
     */
    @PublicAPI
    public ZonedDateTime getDateCreated() {
        return dateCreated.getValue();
    }

    /**
     * @return the last message
     */
    @PublicAPI
    public ChatMessage getLastMessage() {
        return lastMessage.getValue();
    }

    /**
     * @return a list of all participants
     */
    @PublicAPI
    public ObservableList<User> getParticipants() {
        return participants;
    }

    /**
     * @return true if the chat is readonly
     */
    @PublicAPI
    public Boolean isReadonly() {
        return readonly.getValue();
    }

    /**
     * @return the name of the chat
     */
    @PublicAPI
    public String getChatName() {
        return chatName.getValue();
    }

    /**
     * @return the owner of the chat
     */
    @PublicAPI
    public User getOwner() {
        return owner.getValue();
    }

    /**
     * @return the unique identifier
     */
    @PublicAPI
    public String getId() {
        return id.getValue();
    }

    @Override
    public String toString() {
        return "Chat{"
            + "dateCreated=" + dateCreated
            + ", lastMessage=" + lastMessage
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

        return id.getValue() != null ? id.getValue().equals(chat.id.getValue())
            : chat.id.getValue() == null;

    }

    @Override
    public int hashCode() {
        return id.getValue() != null ? id.getValue().hashCode() : 0;
    }
}
