package com.alphatrader.rest.util;

import com.alphatrader.rest.User;

import java.net.URL;

/**
 * The Config singleton holds all library wide configuration variables.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0.0
 */
public class Config {
    /**
     * The singleton instance.
     */
    private static Config instance = new Config();
    /**
     * The URL of the API.
     */
    private URL apiUrl;
    /**
     * The user to log into the game API.
     */
    private User user;

    /**
     * @return the singleton instance.
     */
    public static Config getInstance() {
        return instance;
    }

    /**
     * @return the API URL.
     */
    public URL getApiUrl() {
        return apiUrl;
    }

    /**
     * Sets the API URL to a new value.
     *
     * @param apiUrl the new API URL
     */
    public void setApiUrl(URL apiUrl) {
        this.apiUrl = apiUrl;
    }

    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets a new user.
     *
     * @param user the new user
     */
    public void setUser(User user) {
        this.user = user;
    }
}
