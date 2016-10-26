package com.alphatrader.rest;

import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 * A vote in a poll in the game.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0
 */
public class Vote {
    /**
     * The answer
     */
    private final ObjectProperty<Answer> type = new SimpleObjectProperty<>();

    /**
     * The number of voices.
     */
    private final LongProperty voices = new SimpleLongProperty();

    /**
     * The voter.
     */
    private final ObjectProperty<User> voter = new SimpleObjectProperty<>();

    /**
     * @return the result
     */
    public Answer getType() {
        return type.getValue();
    }

    /**
     * @return the number of voices
     */
    public Long getVoices() {
        return voices.getValue();
    }

    /**
     * @return the voter
     */
    public User getVoter() {
        return voter.getValue();
    }

    @Override
    public String toString() {
        return "Vote{"
            + "type=" + type
            + ", voices=" + voices.getValue()
            + ", voter=" + voter
            + '}';
    }

    /**
     * The answer of the poll.
     */
    public enum Answer {
        YES, NO
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Vote vote = (Vote) o;

        return type.getValue() == vote.type.getValue()
            && (voices.getValue() != null ? voices.getValue().equals(vote.voices.getValue())
            : vote.voices.getValue() == null
            && (voter.getValue() != null ? voter.getValue().equals(vote.voter.getValue())
            : vote.voter.getValue() == null));

    }

    @Override
    public int hashCode() {
        int result = type.getValue() != null ? type.getValue().hashCode() : 0;
        result = 31 * result + (voices.getValue() != null ? voices.getValue().hashCode() : 0);
        result = 31 * result + (voter.getValue() != null ? voter.getValue().hashCode() : 0);
        return result;
    }
}
