package com.alphatrader.rest;

import java.time.LocalDateTime;

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
    private final LocalDateTime dateSent = null;

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
     * @return the date the message was sent.
     */
    public LocalDateTime getDateSent() {

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
