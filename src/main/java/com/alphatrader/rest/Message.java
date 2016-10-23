package com.alphatrader.rest;

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
     * The i18n string.
     */
    private final String message = null;

    /**
     * The substitutions for the i18n string.
     */
    private final String[] substitutions = new String[0];

    /**
     * The substituded message.
     */
    private final String filledString = null;

    /**
     * @return the i18n string.
     */
    public String getMessage() {
        return message;
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

        Message message = (Message) o;

        return filledString != null ? filledString.equals(message.filledString)
            : message.filledString == null;

    }

    @Override
    public int hashCode() {
        return filledString != null ? filledString.hashCode() : 0;
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
