package com.alphatrader.rest;


import java.time.ZonedDateTime;

/**
 * A collection of user capabilities.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0.0
 */
@SuppressWarnings("ConstantConditions")
public class UserCapabilities {
    /**
     * True, if the user is a level 2 user.
     */
    private final Boolean level2User = null;

    /**
     * The date the level 2 user status is revoked.
     */
    private final ZonedDateTime level2UserEndDate = null;

    /**
     * The user's locale.
     */
    private final String locale = null;

    /**
     * True, if the user is a partner.
     */
    private final Boolean partner = null;

    /**
     * True, if the user is a premium user.
     */
    private final Boolean premium = null;

    /**
     * The date, the premium status runs out.
     */
    private final ZonedDateTime premiumEndDate = null;

    /**
     * @return true, if the user is a level 2 user
     */
    public Boolean isLevel2User() {
        return level2User;
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
        return locale;
    }

    /**
     * @return true, if the user is a partner
     */
    public Boolean isPartner() {
        return partner;
    }

    /**
     * @return true, if the user is a premium user
     */
    public Boolean isPremium() {
        return premium;
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

        if (level2User != null ? !level2User.equals(that.level2User) : that.level2User != null) {
            return false;
        }
        if (level2UserEndDate != null ? !level2UserEndDate.equals(that.level2UserEndDate)
            : that.level2UserEndDate != null) {
            return false;
        }
        if (locale != null ? !locale.equals(that.locale) : that.locale != null) {
            return false;
        }
        if (partner != null ? !partner.equals(that.partner) : that.partner != null) {
            return false;
        }
        if (premium != null ? !premium.equals(that.premium) : that.premium != null) {
            return false;
        }
        return premiumEndDate != null ? premiumEndDate.equals(that.premiumEndDate)
            : that.premiumEndDate == null;

    }

    @Override
    public int hashCode() {
        int result = level2User != null ? level2User.hashCode() : 0;
        result = 31 * result + (level2UserEndDate != null ? level2UserEndDate.hashCode() : 0);
        result = 31 * result + (locale != null ? locale.hashCode() : 0);
        result = 31 * result + (partner != null ? partner.hashCode() : 0);
        result = 31 * result + (premium != null ? premium.hashCode() : 0);
        result = 31 * result + (premiumEndDate != null ? premiumEndDate.hashCode() : 0);
        return result;
    }
}
