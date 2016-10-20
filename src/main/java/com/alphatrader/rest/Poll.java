package com.alphatrader.rest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * Represents a poll in the game.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0.0
 */
@SuppressWarnings("ConstantConditions")
public class Poll {
    /**
     * The logger for this class.
     */
    private static final Log log = LogFactory.getLog(Poll.class);

    /**
     * The abstention rule.
     */
    private final AbstentionRule abstentionRule = null;
    /**
     * The percentage of approval votes.
     */
    private final Double approvalVotesPercentage = null;
    /**
     * The total number of voices.
     */
    private final Integer totalNumberOfVoices = null;
    /**
     * All votes.
     */
    private final Vote[] votes = null;
    /**
     * The motion text, describing the poll.
     */
    private final String motion = null;
    /**
     * The start date.
     */
    private final ZonedDateTime startDate = null;
    /**
     * The end date.
     */
    private final ZonedDateTime endDate = null;
    /**
     * All group members of this poll.
     */
    private final VotingGroupMember[] group = null;
    /**
     * The percentage of votes already cast.
     */
    private final Double castVotesPercentage = null;
    /**
     * The total number of votes already cast.
     */
    private final Integer totalNumberOfCastVotes = null;
    /**
     * The initiator of the poll.
     */
    private final User pollInitiator = null;
    /**
     * The date the result expires.
     */
    private final ZonedDateTime resultExpireDate = null;
    /**
     * The unique id.
     */
    private final String id = null;
    /**
     * The result of the poll.
     */
    private final Type type = null;

    /**
     * Fetches all polls initiated by the current user.
     *
     * @return all polls initiated by the current user
     */
    @NotNull
    public static List<Poll> getInitiatedPolls() {
        return getMultiplePollsFromApi("initiatedpolls/");
    }

    /**
     * Fetches all polls concerning the logged in user.
     *
     * @return all polls concerning the logged in user
     */
    @NotNull
    public static List<Poll> getPolls() {
        return getMultiplePollsFromApi("polls/");
    }

    /**
     * Wrapper function to fetch multiple polls from the API.
     *
     * @param suffix the api endpoint suffix
     * @return the list of polls requested
     */
    @NotNull
    private static List<Poll> getMultiplePollsFromApi(String suffix) {
        return Http.getMultipleObjectFromApi(Poll.class, "/api/" + suffix);
    }

    /**
     * Fetches the poll from the given id.
     *
     * @param pollId the poll id
     * @return the poll with the given id
     */
    @Nullable
    private static Poll getById(String pollId) {
        return Http.getSingleObjectFromApi(Poll.class, "/api/polls/" + pollId);
    }

    /**
     * @return the abstention rule
     */
    public AbstentionRule getAbstentionRule() {
        return abstentionRule;
    }

    /**
     * @return the percentage of approval votes
     */
    public Double getApprovalVotesPercentage() {
        return approvalVotesPercentage;
    }

    /**
     * @return the total number of voices
     */
    public Integer getTotalNumberOfVoices() {
        return totalNumberOfVoices;
    }

    /**
     * @return the votes
     */
    public List<Vote> getVotes() {
        return Arrays.asList(votes);
    }

    /**
     * @return the motion text
     */
    public String getMotion() {
        return motion;
    }

    /**
     * @return the start date
     */
    public ZonedDateTime getStartDate() {
        return startDate;
    }

    /**
     * @return the end date
     */
    public ZonedDateTime getEndDate() {
        return endDate;
    }

    /**
     * @return the group of users affected by the poll
     */
    public List<VotingGroupMember> getGroup() {
        return Arrays.asList(group);
    }

    /**
     * @return the percentage of votes cast
     */
    public Double getCastVotesPercentage() {
        return castVotesPercentage;
    }

    /**
     * @return the total number of votes cast
     */
    public Integer getTotalNumberOfCastVotes() {
        return totalNumberOfCastVotes;
    }

    /**
     * @return the initiator of the poll
     */
    public User getPollInitiator() {
        return pollInitiator;
    }

    /**
     * @return the expiry date of the result
     */
    public ZonedDateTime getResultExpireDate() {
        return resultExpireDate;
    }

    /**
     * @return the unique id
     */
    public String getId() {
        return id;
    }

    /**
     * @return the current result
     */
    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Poll{"
            + "abstentionRule=" + abstentionRule
            + ", approvalVotesPercentage=" + approvalVotesPercentage
            + ", totalNumberOfVoices=" + totalNumberOfVoices
            + ", votes=" + Arrays.toString(votes)
            + ", motion='" + motion + '\''
            + ", startDate=" + startDate
            + ", endDate=" + endDate
            + ", group=" + Arrays.toString(group)
            + ", castVotesPercentage=" + castVotesPercentage
            + ", totalNumberOfCastVotes=" + totalNumberOfCastVotes
            + ", pollInitiator=" + pollInitiator
            + ", resultExpireDate=" + resultExpireDate
            + ", id='" + id + '\''
            + ", type=" + type
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

        Poll poll = (Poll) o;

        return id != null ? id.equals(poll.id) : poll.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    /**
     * The abstention rule.
     */
    public enum AbstentionRule {
        COUNTS_AS_REFUSAL,
        COUNTS_AS_APPROVAL
    }

    /**
     * The type of poll.
     */
    public enum Type {
        YES_NO
    }
}
