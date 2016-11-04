package com.alphatrader.rest;

import javafx.beans.property.*;
import javafx.collections.ObservableList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * Represents a poll in the game.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0.0
 */
public class Poll {
    /**
     * The abstention rule.
     */
    private final ObjectProperty<AbstentionRule> abstentionRule = new SimpleObjectProperty<>();
    /**
     * The percentage of approval votes.
     */
    private final DoubleProperty approvalVotesPercentage = new SimpleDoubleProperty();
    /**
     * The total number of voices.
     */
    private final LongProperty totalNumberOfVoices = new SimpleLongProperty();
    /**
     * All votes.
     */
    private final ObservableList<Vote> votes = new SimpleListProperty<>();
    /**
     * The motion text, describing the poll.
     */
    private final StringProperty motion = new SimpleStringProperty();
    /**
     * The start date.
     */
    private final ObjectProperty<ZonedDateTime> startDate = new SimpleObjectProperty<>();
    /**
     * The end date.
     */
    private final ObjectProperty<ZonedDateTime> endDate = new SimpleObjectProperty<>();
    /**
     * All group members of this poll.
     */
    private final ObservableList<VotingGroupMember> group = new SimpleListProperty<>();
    /**
     * The percentage of votes already cast.
     */
    private final DoubleProperty castVotesPercentage = new SimpleDoubleProperty();
    /**
     * The total number of votes already cast.
     */
    private final LongProperty totalNumberOfCastVotes = new SimpleLongProperty();
    /**
     * The initiator of the poll.
     */
    private final ObjectProperty<User> pollInitiator = new SimpleObjectProperty<>();
    /**
     * The date the result expires.
     */
    private final ObjectProperty<ZonedDateTime> resultExpireDate = new SimpleObjectProperty<>();
    /**
     * The unique id.
     */
    private final StringProperty id = new SimpleStringProperty();
    /**
     * The result of the poll.
     */
    private final ObjectProperty<Type> type = new SimpleObjectProperty<>();

    /**
     * Fetches all polls initiated by the current user.
     *
     * @return all polls initiated by the current user
     */
    @PublicAPI
    @NotNull
    public static List<Poll> getInitiatedPolls() {
        return getMultiplePollsFromApi("initiatedpolls/");
    }

    /**
     * Fetches all polls concerning the logged in user.
     *
     * @return all polls concerning the logged in user
     */
    @PublicAPI
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
    @PublicAPI
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
    @PublicAPI
    @Nullable
    public static Poll getById(String pollId) {
        return Http.getSingleObjectFromApi(Poll.class, "/api/polls/" + pollId);
    }

    /**
     * @return the abstention rule
     */
    @PublicAPI
    public AbstentionRule getAbstentionRule() {
        return abstentionRule.getValue();
    }

    /**
     * @return the percentage of approval votes
     */
    @PublicAPI
    public Double getApprovalVotesPercentage() {
        return approvalVotesPercentage.getValue();
    }

    /**
     * @return the total number of voices
     */
    @PublicAPI
    public Long getTotalNumberOfVoices() {
        return totalNumberOfVoices.getValue();
    }

    /**
     * @return the votes
     */
    @PublicAPI
    public ObservableList<Vote> getVotes() {
        return votes;
    }

    /**
     * @return the motion text
     */
    @PublicAPI
    public String getMotion() {
        return motion.getValue();
    }

    /**
     * @return the start date
     */
    @PublicAPI
    public ZonedDateTime getStartDate() {
        return startDate.getValue();
    }

    /**
     * @return the end date
     */
    @PublicAPI
    public ZonedDateTime getEndDate() {
        return endDate.getValue();
    }

    /**
     * @return the group of users affected by the poll
     */
    @PublicAPI
    public ObservableList<VotingGroupMember> getGroup() {
        return group;
    }

    /**
     * @return the percentage of votes cast
     */
    @PublicAPI
    public Double getCastVotesPercentage() {
        return castVotesPercentage.getValue();
    }

    /**
     * @return the total number of votes cast
     */
    @PublicAPI
    public Long getTotalNumberOfCastVotes() {
        return totalNumberOfCastVotes.getValue();
    }

    /**
     * @return the initiator of the poll
     */
    @PublicAPI
    public User getPollInitiator() {
        return pollInitiator.getValue();
    }

    /**
     * @return the expiry date of the result
     */
    @PublicAPI
    public ZonedDateTime getResultExpireDate() {
        return resultExpireDate.getValue();
    }

    /**
     * @return the unique id
     */
    @PublicAPI
    public String getId() {
        return id.getValue();
    }

    /**
     * @return the current result
     */
    @PublicAPI
    public Type getType() {
        return type.getValue();
    }

    /**
     * @return the abstention rule property.
     */
    @PublicAPI
    public ReadOnlyObjectProperty<AbstentionRule> abstentionRuleProperty() {
        return abstentionRule;
    }

    /**
     * @return the approval votes percentage property.
     */
    @PublicAPI
    public ReadOnlyDoubleProperty approvalVotesPercentageProperty() {
        return approvalVotesPercentage;
    }

    /**
     * @return the total number of voices property.
     */
    @PublicAPI
    public ReadOnlyLongProperty totalNumberOfVoicesProperty() {
        return totalNumberOfVoices;
    }

    /**
     * @return the motion property.
     */
    @PublicAPI
    public ReadOnlyStringProperty motionProperty() {
        return motion;
    }

    /**
     * @return the start date property.
     */
    @PublicAPI
    public ReadOnlyObjectProperty<ZonedDateTime> startDateProperty() {
        return startDate;
    }

    /**
     * @return the end date property.
     */
    @PublicAPI
    public ReadOnlyObjectProperty<ZonedDateTime> endDateProperty() {
        return endDate;
    }

    /**
     * @return the cast votes percentage property.
     */
    @PublicAPI
    public ReadOnlyDoubleProperty castVotesPercentageProperty() {
        return castVotesPercentage;
    }

    /**
     * @return the total number of cast votes property.
     */
    @PublicAPI
    public ReadOnlyLongProperty totalNumberOfCastVotesProperty() {
        return totalNumberOfCastVotes;
    }

    /**
     * @return the poll initiator property.
     */
    @PublicAPI
    public ReadOnlyObjectProperty<User> pollInitiatorProperty() {
        return pollInitiator;
    }

    /**
     * @return the result expire date property.
     */
    @PublicAPI
    public ReadOnlyObjectProperty<ZonedDateTime> resultExpireDateProperty() {
        return resultExpireDate;
    }

    /**
     * @return the id property.
     */
    @PublicAPI
    public ReadOnlyStringProperty idProperty() {
        return id;
    }

    /**
     * @return the type property.
     */
    @PublicAPI
    public ReadOnlyObjectProperty<Type> typeProperty() {
        return type;
    }

    @Override
    public String toString() {
        return "Poll{"
            + "abstentionRule=" + abstentionRule.getValue()
            + ", approvalVotesPercentage=" + approvalVotesPercentage.getValue()
            + ", totalNumberOfVoices=" + totalNumberOfVoices.getValue()
            + ", motion='" + motion.getValue() + '\''
            + ", startDate=" + startDate.getValue()
            + ", endDate=" + endDate.getValue()
            + ", castVotesPercentage=" + castVotesPercentage.getValue()
            + ", totalNumberOfCastVotes=" + totalNumberOfCastVotes.getValue()
            + ", pollInitiator=" + pollInitiator.getValue()
            + ", resultExpireDate=" + resultExpireDate.getValue()
            + ", id='" + id.getValue() + '\''
            + ", type=" + type.getValue()
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

        return id.getValue() != null ? id.getValue().equals(poll.id.getValue())
            : poll.id.getValue() == null;

    }

    @Override
    public int hashCode() {
        return id.getValue() != null ? id.getValue().hashCode() : 0;
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
