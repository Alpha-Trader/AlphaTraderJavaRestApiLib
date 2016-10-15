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
    private static Config instance = new Config();

    public static Config getInstance() {
        return instance;
    }

    private User user;
    private URL apiUrl;

    public URL getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(URL apiUrl) {
        this.apiUrl = apiUrl;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
