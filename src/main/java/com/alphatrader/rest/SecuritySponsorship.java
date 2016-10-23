package com.alphatrader.rest;

/**
 * Represents a security sponsorship in the game.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0.0
 */
public class SecuritySponsorship {
    /**
     * The company which is sponsoring the listing.
     */
    private final Company designatedSponsor = null;

    /**
     * The listing being sponsored.
     */
    private final Listing listing = null;

    /**
     * The rating of the sponsor concerning this listing.
     */
    private final SponsorRating sponsorRating = null;

    /**
     * @return the designated sponsor
     */
    public Company getDesignatedSponsor() {
        return designatedSponsor;
    }

    /**
     * @return the listing
     */
    public Listing getListing() {
        return listing;
    }

    /**
     * @return the sponsor rating
     */
    public SponsorRating getSponsorRating() {
        return sponsorRating;
    }

    @Override
    public String toString() {
        return "SecuritySponsorship{"
            + "designatedSponsor=" + designatedSponsor
            + ", listing=" + listing
            + ", sponsorRating=" + sponsorRating
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

        SecuritySponsorship that = (SecuritySponsorship) o;

        if (designatedSponsor != null ? !designatedSponsor.equals(that.designatedSponsor)
            : that.designatedSponsor != null) {
            return false;
        }
        return listing != null ? listing.equals(that.listing) : that.listing == null;

    }

    @Override
    public int hashCode() {
        int result = designatedSponsor != null ? designatedSponsor.hashCode() : 0;
        result = 31 * result + (listing != null ? listing.hashCode() : 0);
        return result;
    }
}
