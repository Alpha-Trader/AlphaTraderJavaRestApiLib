package com.alphatrader.rest;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Arrays;
import java.util.List;

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
    private final String[] substitutions = new String[0];

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
    public List<String> getSubstitutions() {
        return Arrays.asList(substitutions);
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
            + ", substitutions=" + Arrays.toString(substitutions)
            + ", filledString='" + filledString.getValue() + '\''
            + '}';
    }
}
