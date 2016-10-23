package com.alphatrader.rest;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;

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
    private final Answer type = null;

    /**
     * The number of voices.
     */
    private final LongProperty voices = new SimpleLongProperty();

    /**
     * The voter.
     */
    private final User voter = null;

    /**
     * @return the result
     */
    public Answer getType() {
        return type;
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
        return voter;
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

        if (type != vote.type) {
            return false;
        }
        if (voices.getValue() != null ? !voices.getValue().equals(vote.voices.getValue())
            : vote.voices.getValue() != null) {
            return false;
        }
        return voter != null ? voter.equals(vote.voter) : vote.voter == null;

    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + (voices.getValue() != null ? voices.getValue().hashCode() : 0);
        result = 31 * result + (voter != null ? voter.hashCode() : 0);
        return result;
    }
}
