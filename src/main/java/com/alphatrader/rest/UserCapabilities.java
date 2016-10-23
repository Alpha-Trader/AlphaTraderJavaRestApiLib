package com.alphatrader.rest;


import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.ZonedDateTime;

/**
 * A collection of user capabilities.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0.0
 */
public class UserCapabilities {
    /**
     * True, if the user is a level 2 user.
     */
    private final BooleanProperty level2User;

    /**
     * The date the level 2 user status is revoked.
     */
    private final ZonedDateTime level2UserEndDate;

    /**
     * The user's locale.
     */
    private final StringProperty locale;

    /**
     * True, if the user is a partner.
     */
    private final BooleanProperty partner;

    /**
     * True, if the user is a premium user.
     */
    private final BooleanProperty premium;

    /**
     * The date, the premium status runs out.
     */
    private final ZonedDateTime premiumEndDate;

    /**
     * Creates a new default user capabilities object.
     */
    public UserCapabilities() {
        level2User = new SimpleBooleanProperty();
        level2UserEndDate = ZonedDateTime.now();
        locale = new SimpleStringProperty();
        partner = new SimpleBooleanProperty();
        premium = new SimpleBooleanProperty();
        premiumEndDate = ZonedDateTime.now();
    }

    /**
     * @return true, if the user is a level 2 user
     */
    public Boolean isLevel2User() {
        return level2User.getValue();
    }

    /**
     * @return the date the level 2 user status runs out
     */
    public ZonedDateTime getLevel2UserEndDate() {
        return level2UserEndDate;
    }

    /**
     * @return the user's locale
     */
    public String getLocale() {
        return locale.getValue();
    }

    /**
     * @return true, if the user is a partner
     */
    public Boolean isPartner() {
        return partner.getValue();
    }

    /**
     * @return true, if the user is a premium user
     */
    public Boolean isPremium() {
        return premium.getValue();
    }

    /**
     * @return the time the premium status runs out
     */
    public ZonedDateTime getPremiumEndDate() {
        return premiumEndDate;
    }

    @Override
    public String toString() {
        return "UserCapabilities{"
            + "level2User=" + level2User
            + ", level2UserEndDate=" + level2UserEndDate
            + ", locale='" + locale + '\''
            + ", partner=" + partner
            + ", premium=" + premium
            + ", premiumEndDate=" + premiumEndDate
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

        UserCapabilities that = (UserCapabilities) o;

        if (level2User.getValue() != null ? !level2User.getValue().equals(that.level2User.getValue())
            : that.level2User.getValue() != null) {
            return false;
        }
        if (level2UserEndDate != null ? !level2UserEndDate.equals(that.level2UserEndDate)
            : that.level2UserEndDate != null) {
            return false;
        }
        if (locale.getValue() != null ? !locale.getValue().equals(that.locale.getValue())
            : that.locale.getValue() != null) {
            return false;
        }
        if (partner.getValue() != null ? !partner.getValue().equals(that.partner.getValue())
            : that.partner.getValue() != null) {
            return false;
        }
        if (premium.getValue() != null ? !premium.getValue().equals(that.premium.getValue())
            : that.premium.getValue() != null) {
            return false;
        }
        return premiumEndDate != null ? premiumEndDate.equals(that.premiumEndDate)
            : that.premiumEndDate == null;

    }

    @Override
    public int hashCode() {
        int result = level2User.getValue() != null ? level2User.getValue().hashCode() : 0;
        result = 31 * result + (level2UserEndDate != null ? level2UserEndDate.hashCode() : 0);
        result = 31 * result + (locale.getValue() != null ? locale.getValue().hashCode() : 0);
        result = 31 * result + (partner.getValue() != null ? partner.getValue().hashCode() : 0);
        result = 31 * result + (premium.getValue() != null ? premium.getValue().hashCode() : 0);
        result = 31 * result + (premiumEndDate != null ? premiumEndDate.hashCode() : 0);
        return result;
    }
}
