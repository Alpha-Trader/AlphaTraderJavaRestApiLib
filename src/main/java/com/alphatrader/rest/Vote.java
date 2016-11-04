package com.alphatrader.rest;

import javafx.beans.property.*;

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
    @PublicAPI
    public Answer getType() {
        return type.getValue();
    }

    /**
     * @return the number of voices
     */
    @PublicAPI
    public Long getVoices() {
        return voices.getValue();
    }

    /**
     * @return the voter
     */
    @PublicAPI
    public User getVoter() {
        return voter.getValue();
    }

    /**
     * @return the type property.
     */
    @PublicAPI
    public ReadOnlyObjectProperty<Answer> typeProperty() {
        return type;
    }

    /**
     * @return the voices property.
     */
    @PublicAPI
    public ReadOnlyLongProperty voicesProperty() {
        return voices;
    }

    /**
     * @return the voter property.
     */
    @PublicAPI
    public ReadOnlyObjectProperty<User> voterProperty() {
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

    @SuppressWarnings("SimplifiableIfStatement")
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Vote vote = (Vote) o;

        if (type.getValue() != null ? !type.getValue().equals(vote.type.getValue())
            : vote.type.getValue() != null) {
            return false;
        }
        if (voices.getValue() != null ? !voices.getValue().equals(vote.voices.getValue())
            : vote.voices.getValue() != null) {
            return false;
        }
        return voter.getValue() != null ? voter.getValue().equals(vote.voter.getValue())
            : vote.voter.getValue() == null;
    }

    @Override
    public int hashCode() {
        int result = type.getValue() != null ? type.getValue().hashCode() : 0;
        result = 31 * result + (voices.getValue() != null ? voices.getValue().hashCode() : 0);
        result = 31 * result + (voter.getValue() != null ? voter.getValue().hashCode() : 0);
        return result;
    }
}
