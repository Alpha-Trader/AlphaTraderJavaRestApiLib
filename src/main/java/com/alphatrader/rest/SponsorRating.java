package com.alphatrader.rest;

/**
 * Represents a sponsor rating in the game.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0.0
 */
public class SponsorRating {
    /**
     * The salary paid for the sponsorship
     */
    private final Double salary = null;

    /**
     * The rating classification.
     */
    private final SponsorRatingLetter value = null;

    /**
     * Represents the sponsor rating classes.
     */
    public enum SponsorRatingLetter {
        A, B, C, D
    }
}
