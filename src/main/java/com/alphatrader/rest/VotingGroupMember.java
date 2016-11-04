package com.alphatrader.rest;

import javafx.beans.property.*;

/**
 * A group member in a poll in the game.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0
 */
public class VotingGroupMember {
    /**
     * The group member.
     */
    private final ObjectProperty<User> groupMember = new SimpleObjectProperty<>();

    /**
     * The number of votes.
     */
    private final LongProperty numberOfVoices = new SimpleLongProperty();

    /**
     * @return the group member
     */
    @PublicAPI
    public User getGroupMember() {
        return groupMember.getValue();
    }

    /**
     * @return the number of votes
     */
    @PublicAPI
    public Long getNumberOfVoices() {
        return numberOfVoices.getValue();
    }

    /**
     * @return the group member property.
     */
    @PublicAPI
    public ReadOnlyObjectProperty<User> groupMemberProperty() {
        return groupMember;
    }

    /**
     * @return the number of voices property.
     */
    @PublicAPI
    public ReadOnlyLongProperty numberOfVoicesProperty() {
        return numberOfVoices;
    }

    @Override
    public String toString() {
        return "VotingGroupMember{"
            + "groupMember=" + groupMember.getValue()
            + ", numberOfVoices=" + numberOfVoices.getValue()
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

        VotingGroupMember that = (VotingGroupMember) o;

        return groupMember.getValue() != null ? groupMember.getValue().equals(that.groupMember.
            getValue()) : that.groupMember.getValue() == null && (numberOfVoices.getValue() != null
            ? numberOfVoices.getValue().equals(that.numberOfVoices.getValue())
            : that.numberOfVoices.getValue() == null);

    }

    @Override
    public int hashCode() {
        int result = groupMember.getValue() != null ? groupMember.getValue().hashCode() : 0;
        result = 31 * result + (numberOfVoices.getValue() != null ? numberOfVoices.getValue().hashCode()
            : 0);
        return result;
    }
}
