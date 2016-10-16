package com.alphatrader.rest;

import java.time.LocalDateTime;

/**
 * Represents a listing in the game.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0.0
 */
class Listing {
    enum Type {
        BOND,
        STOCK,
        REPO
    }

    private final LocalDateTime startDate = null;
    private final LocalDateTime endDate = null;
    private final String securityIdentifier = null;
    private final String name = null;
    private final Type type = null;

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public String getSecurityIdentifier() {
        return securityIdentifier;
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Listing listing = (Listing) o;

        if (startDate != null ? !startDate.equals(listing.startDate) : listing.startDate != null) {
            return false;
        }
        if (endDate != null ? !endDate.equals(listing.endDate) : listing.endDate != null) {
            return false;
        }
        if (securityIdentifier != null ? !securityIdentifier.equals(listing.securityIdentifier)
            : listing.securityIdentifier != null) {
            return false;
        }
        if (name != null ? !name.equals(listing.name) : listing.name != null) {
            return false;
        }
        return type == listing.type;

    }

    @Override
    public int hashCode() {
        int result = startDate != null ? startDate.hashCode() : 0;
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (securityIdentifier != null ? securityIdentifier.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}
