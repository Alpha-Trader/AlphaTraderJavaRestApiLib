package com.alphatrader.rest;

import java.util.Arrays;

/**
 * Represents a translateable text message object in the game.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0.0
 */
class Message {
    private final String message = null;
    private final String[] substitutions = null;
    private final String filledString = null;

    public String getMessage() {
        return message;
    }

    public String[] getSubstitutions() {
        return substitutions;
    }

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
