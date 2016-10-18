package com.alphatrader.rest;


import java.time.LocalDateTime;

/**
 * A collection of user capabilities.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0.0
 */
public class UserCapabilities {
    private final Boolean level2User = null;
    private final LocalDateTime level2UserEndDate = null;
    private final String locale = null;
    private final Boolean partner = null;
    private final String partnerId = null;
    private final Boolean premium = null;
    private final LocalDateTime premiumEndDate = null;

    public Boolean isLevel2User() {
        return level2User;
    }

    public LocalDateTime getLevel2UserEndDate() {
        return level2UserEndDate;
    }

    public String getLocale() {
        return locale;
    }

    public Boolean isPartner() {
        return partner;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public Boolean isPremium() {
        return premium;
    }

    public LocalDateTime getPremiumEndDate() {
        return premiumEndDate;
    }
}
