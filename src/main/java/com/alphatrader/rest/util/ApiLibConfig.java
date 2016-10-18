package com.alphatrader.rest.util;

import com.alphatrader.rest.User;

import java.net.URL;

/**
 * The ApiLibConfig singleton holds all library wide configuration variables.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0.0
 */
public class ApiLibConfig {
    /**
     * The singleton instance.
     */
    private static final ApiLibConfig instance = new ApiLibConfig();
    /**
     * The URL of the API.
     */
    private URL apiUrl;
    /**
     * The user to log into the game API.
     */
    private User user;
    /**
     * The partner id of the developer.
     */
    private String partnerId;

    /**
     * @return the singleton instance.
     */
    public static ApiLibConfig getInstance() {
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

    /**
     * @return the partner id.
     */
    public String getPartnerId() {
        return partnerId;
    }

    /**
     * Sets a new partner id.
     *
     * @param partnerId the new partner id
     */
    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }
}
