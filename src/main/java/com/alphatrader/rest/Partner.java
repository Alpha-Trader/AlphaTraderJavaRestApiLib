package com.alphatrader.rest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
     * The logger for this class. Use this to write messages to the console.
     */
    private static final Log log = LogFactory.getLog(Partner.class);

    /**
     * Avoid utility class instantiation.
     */
    private Partner() { }

    /**
     * Fetches all registered partners.
     *
     * @return all partners
     */
    @NotNull
    public static List<User> getAllPartners() {
        return Http.getMultipleObjectFromApi(User.class, "/api/partners/");
    }
}
