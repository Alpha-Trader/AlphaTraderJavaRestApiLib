package com.alphatrader.rest;

import com.alphatrader.rest.util.LocalDateTimeDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
     * Gson instance for deserialization.
     */
    private static final Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class,
        new LocalDateTimeDeserializer()).create();

    /**
     * List type for gson deserialization.
     */
    private static final Type listType = new TypeToken<ArrayList<Poll>>() {
    }.getType();

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
    private final LocalDateTime startDate = null;
    /**
     * The end date.
     */
    private final LocalDateTime endDate = null;
    /**
     * All group members of this poll.
     */
    private final GroupMember[] group = null;
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
    private final LocalDateTime resultExpireDate = null;
    /**
     * The unique id.
     */
    private final String id = null;
    /**
     * The result of the poll.
     */
    private final Answer type = null;

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
        List<Poll> myReturn = new ArrayList<>();

        try {
            HttpResponse<JsonNode> response = Http.getInstance().get("/api/" + suffix);

            if (response != null && response.getStatus() == 200) {
                myReturn.addAll(gson.fromJson(response.getBody()
                    .getArray().toString(), listType));
            }
        }
        catch (UnirestException ue) {
            log.error("Error fetching polls: " + ue.getMessage());
            StringWriter stringWriter = new StringWriter();
            ue.printStackTrace(new PrintWriter(stringWriter));
            log.debug(stringWriter.toString());
        }

        return myReturn;
    }

    /**
     * Fetches the poll from the given id.
     *
     * @param pollId the poll id
     * @return the poll with the given id
     */
    @Nullable
    private static Poll getById(String pollId) {
        Poll myReturn = null;

        try {
            HttpResponse<JsonNode> response = Http.getInstance().get("/api/polls/" + pollId);

            if (response != null && response.getStatus() == 200) {
                myReturn = gson.fromJson(response.getBody().getObject().toString(), Poll.class);
            }
        }
        catch (UnirestException ue) {
            log.error("Error fetching poll: " + ue.getMessage());
            StringWriter stringWriter = new StringWriter();
            ue.printStackTrace(new PrintWriter(stringWriter));
            log.debug(stringWriter.toString());
        }

        return myReturn;
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
    public LocalDateTime getStartDate() {
        return startDate;
    }

    /**
     * @return the end date
     */
    public LocalDateTime getEndDate() {
        return endDate;
    }

    /**
     * @return the group of users affected by the poll
     */
    public List<GroupMember> getGroup() {
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
    public LocalDateTime getResultExpireDate() {
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
    public Answer getType() {
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
     * The answer of the poll.
     */
    public enum Answer {
        YES, NO
    }

    /**
     * A vote in the poll.
     */
    public static class Vote {
        private Answer type;
        private Integer voices;
        private User voter;
    }

    /**
     * A group member in a poll.
     */
    public static class GroupMember {
        private final User groupMember = null;
        private final Integer numberOfVotes = null;
    }
}
