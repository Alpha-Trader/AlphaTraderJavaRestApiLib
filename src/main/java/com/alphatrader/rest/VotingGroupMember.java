package com.alphatrader.rest;

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
    private final User groupMember = null;

    /**
     * The number of votes.
     */
    private final Integer numberOfVoices = null;

    /**
     * @return the group member
     */
    public User getGroupMember() {
        return groupMember;
    }

    /**
     * @return the number of votes
     */
    public Integer getNumberOfVoices() {
        return numberOfVoices;
    }

    @Override
    public String toString() {
        return "VotingGroupMember{"
            + "groupMember=" + groupMember
            + ", numberOfVoices=" + numberOfVoices
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

        if (groupMember != null ? !groupMember.equals(that.groupMember) : that.groupMember != null) {
            return false;
        }
        return numberOfVoices != null ? numberOfVoices.equals(that.numberOfVoices)
            : that.numberOfVoices == null;

    }

    @Override
    public int hashCode() {
        int result = groupMember != null ? groupMember.hashCode() : 0;
        result = 31 * result + (numberOfVoices != null ? numberOfVoices.hashCode() : 0);
        return result;
    }
}
