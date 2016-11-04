package com.alphatrader.rest;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

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
    private final ObjectProperty<Company> designatedSponsor = new SimpleObjectProperty<>();

    /**
     * The listing being sponsored.
     */
    private final ObjectProperty<Listing> listing = new SimpleObjectProperty<>();

    /**
     * The rating of the sponsor concerning this listing.
     */
    private final ObjectProperty<SponsorRating> sponsorRating = new SimpleObjectProperty<>();

    /**
     * @return the designated sponsor
     */
    @PublicAPI
    public Company getDesignatedSponsor() {
        return designatedSponsor.getValue();
    }

    /**
     * @return the listing
     */
    @PublicAPI
    public Listing getListing() {
        return listing.getValue();
    }

    /**
     * @return the sponsor rating
     */
    @PublicAPI
    public SponsorRating getSponsorRating() {
        return sponsorRating.getValue();
    }

    /**
     * @return the designated sponsor property.
     */
    @PublicAPI
    public ReadOnlyObjectProperty<Company> designatedSponsorProperty() {
        return designatedSponsor;
    }

    /**
     * @return the listing property.
     */
    @PublicAPI
    public ReadOnlyObjectProperty<Listing> listingProperty() {
        return listing;
    }

    /**
     * @return the sponsor rating property.
     */
    @PublicAPI
    public ReadOnlyObjectProperty<SponsorRating> sponsorRatingProperty() {
        return sponsorRating;
    }

    @Override
    public String toString() {
        return "SecuritySponsorship{"
            + "designatedSponsor=" + designatedSponsor.getValue()
            + ", listing=" + listing.getValue()
            + ", sponsorRating=" + sponsorRating.getValue()
            + '}';
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

        SecuritySponsorship that = (SecuritySponsorship) o;

        if (designatedSponsor.getValue() != null ? !designatedSponsor.getValue().equals(
            that.designatedSponsor.getValue()) : that.designatedSponsor.getValue() != null) {
            return false;
        }
        if (listing.getValue() != null ? !listing.getValue().equals(that.listing.getValue())
            : that.listing.getValue() != null) {
            return false;
        }
        return sponsorRating.getValue() != null ? sponsorRating.getValue().equals(
            that.sponsorRating.getValue()) : that.sponsorRating.getValue() == null;

    }

    @Override
    public int hashCode() {
        int result = designatedSponsor.getValue() != null ? designatedSponsor.getValue().hashCode() : 0;
        result = 31 * result + (listing.getValue() != null ? listing.getValue().hashCode() : 0);
        return result;
    }
}
