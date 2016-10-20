package com.alphatrader.rest;

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
    private Answer type;

    /**
     * The number of voices.
     */
    private Integer voices;

    /**
     * The voter.
     */
    private User voter;

    /**
     * @return the result
     */
    public Answer getType() {
        return type;
    }

    /**
     * @return the number of voices
     */
    public Integer getVoices() {
        return voices;
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
            + ", voices=" + voices
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
        if (voices != null ? !voices.equals(vote.voices) : vote.voices != null) {
            return false;
        }
        return voter != null ? voter.equals(vote.voter) : vote.voter == null;

    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + (voices != null ? voices.hashCode() : 0);
        result = 31 * result + (voter != null ? voter.hashCode() : 0);
        return result;
    }
}
