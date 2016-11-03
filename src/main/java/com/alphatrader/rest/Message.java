package com.alphatrader.rest;

import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

/**
 * Represents a translateable text message object in the game.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0.0
 */
public class Message {
    /**
     * The i18n string.
     */
    private final StringProperty message = new SimpleStringProperty();

    /**
     * The substitutions for the i18n string.
     */
    private final ObservableList<String> substitutions = new SimpleListProperty<>();

    /**
     * The substituded message.
     */
    private final StringProperty filledString = new SimpleStringProperty();

    /**
     * @return the i18n string.
     */
    public String getMessage() {
        return message.getValue();
    }

    /**
     * @return the substitutions
     */
    public ObservableList<String> getSubstitutions() {
        return substitutions;
    }

    /**
     * @return the substituted message
     */
    public String getFilledString() {
        return filledString.getValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Message message = (Message) o;

        return filledString.getValue() != null ? filledString.getValue().equals(message.filledString
            .getValue()) : message.filledString.getValue() == null;

    }

    @Override
    public int hashCode() {
        return filledString.getValue() != null ? filledString.getValue().hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Message{"
            + "message='" + message.getValue() + '\''
            + ", substitutions=" + substitutions.toString()
            + ", filledString='" + filledString.getValue() + '\''
            + '}';
    }
}
