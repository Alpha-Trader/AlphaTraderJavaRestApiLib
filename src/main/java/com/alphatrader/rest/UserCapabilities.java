package com.alphatrader.rest;


import javafx.beans.property.*;

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
    private final BooleanProperty level2User = new SimpleBooleanProperty();

    /**
     * The date the level 2 user status is revoked.
     */
    private final ObjectProperty<ZonedDateTime> level2UserEndDate = new SimpleObjectProperty<>();

    /**
     * The user's locale.
     */
    private final StringProperty locale = new SimpleStringProperty();

    /**
     * True, if the user is a partner.
     */
    private final BooleanProperty partner = new SimpleBooleanProperty();

    /**
     * True, if the user is a premium user.
     */
    private final BooleanProperty premium = new SimpleBooleanProperty();

    /**
     * The date, the premium status runs out.
     */
    private final ObjectProperty<ZonedDateTime> premiumEndDate = new SimpleObjectProperty<>();

    /**
     * @return true, if the user is a level 2 user
     */
    @PublicAPI
    public Boolean isLevel2User() {
        return level2User.getValue();
    }

    /**
     * @return the date the level 2 user status runs out
     */
    @PublicAPI
    public ZonedDateTime getLevel2UserEndDate() {
        return level2UserEndDate.getValue();
    }

    /**
     * @return the user's locale
     */
    @PublicAPI
    public String getLocale() {
        return locale.getValue();
    }

    /**
     * @return true, if the user is a partner
     */
    @PublicAPI
    public Boolean isPartner() {
        return partner.getValue();
    }

    /**
     * @return true, if the user is a premium user
     */
    @PublicAPI
    public Boolean isPremium() {
        return premium.getValue();
    }

    /**
     * @return the time the premium status runs out
     */
    @PublicAPI
    public ZonedDateTime getPremiumEndDate() {
        return premiumEndDate.getValue();
    }

    /**
     * @return the level 2 user property.
     */
    @PublicAPI
    public ReadOnlyBooleanProperty level2UserProperty() {
        return level2User;
    }

    /**
     * @return the level 2 user end date property.
     */
    @PublicAPI
    public ReadOnlyObjectProperty<ZonedDateTime> level2UserEndDateProperty() {
        return level2UserEndDate;
    }

    /**
     * @return the locale property.
     */
    @PublicAPI
    public ReadOnlyStringProperty localeProperty() {
        return locale;
    }

    /**
     * @return the partner status property.
     */
    @PublicAPI
    public ReadOnlyBooleanProperty partnerProperty() {
        return partner;
    }

    /**
     * @return the premium property.
     */
    @PublicAPI
    public ReadOnlyBooleanProperty premiumProperty() {
        return premium;
    }

    /**
     * @return the premium end date property.
     */
    @PublicAPI
    public ReadOnlyObjectProperty<ZonedDateTime> premiumEndDateProperty() {
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

    @SuppressWarnings("SimplifiableIfStatement")
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
        if (level2UserEndDate.getValue() != null ? !level2UserEndDate.getValue().equals(
            that.level2UserEndDate.getValue()) : that.level2UserEndDate.getValue() != null) {
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
        return premiumEndDate.getValue() != null ? premiumEndDate.getValue().equals(
            that.premiumEndDate.getValue()) : that.premiumEndDate.getValue() == null;

    }

    @Override
    public int hashCode() {
        int result = level2User.getValue() != null ? level2User.getValue().hashCode() : 0;
        result = 31 * result + (level2UserEndDate.getValue() != null ? level2UserEndDate.getValue()
            .hashCode() : 0);
        result = 31 * result + (locale.getValue() != null ? locale.getValue().hashCode() : 0);
        result = 31 * result + (partner.getValue() != null ? partner.getValue().hashCode() : 0);
        result = 31 * result + (premium.getValue() != null ? premium.getValue().hashCode() : 0);
        result = 31 * result + (premiumEndDate.getValue() != null ? premiumEndDate.getValue().hashCode()
            : 0);
        return result;
    }
}
