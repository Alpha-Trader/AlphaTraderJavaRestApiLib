package com.alphatrader.rest;

import java.time.LocalDateTime;

/**
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0.0
 */
public class Poll {
    private enum AbstentionRule {
        COUNTS_AS_REFUSAL,
        COUNTS_AS_APPROVAL
    }

    private enum Answer {
        YES, NO
    }

    public static class Vote {
        private Answer type;
        private Integer voices;
        private User voter;
    }

    public static class GroupMember {
        private final User groupMember = null;
        private final Integer numberOfVotes = null;
    }

    private final User applicant = null;
    private final AbstentionRule abstentionRule = null;
    private final Double approvalVotesPercentage = null;
    private final Integer totalNumberOfVoices = null;
    private final Vote[] votes = null;
    private final String motion = null;
    private final LocalDateTime startDate = null;
    private final LocalDateTime endDate = null;
    private final GroupMember[] group = null;
    private final Double castVotesPercentage = null;
    private final Integer totalNumberOfCastVotes = null;
    private final User pollInitiation = null;
    private final LocalDateTime resultExpireDate = null;
    private final String id = null;
    private final Answer type = null;
}
