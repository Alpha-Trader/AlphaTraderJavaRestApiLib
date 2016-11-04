package com.alphatrader.rest;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Lists all users who are registered as partners.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0.0
 */
public final class Partner {
    /**
     * Avoid utility class instantiation.
     */
    private Partner() { }

    /**
     * Fetches all registered partners.
     *
     * @return all partners
     */
    @PublicAPI
    @NotNull
    public static List<User> getAllPartners() {
        return Http.getMultipleObjectFromApi(User.class, "/api/partners/");
    }
}
