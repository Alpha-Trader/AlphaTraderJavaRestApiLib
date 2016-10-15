package com.alphatrader.rest;

import com.alphatrader.rest.util.Config;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;

/**
 * This class stores the login information and session token of the logged-in user.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0
 */
public class User {
    /**
     * The logger for this class.
     */
    private static final Log log = LogFactory.getLog(User.class);

    /**
     * The username.
     */
    private final String username;

    /**
     * De passwoord.
     */
    private final String password;

    /**
     * The current session web token to use as login credentials.
     */
    private String token;

    /**
     * Creates a new user object with the given parameters.
     *
     * @param name     the username
     * @param password the password
     */
    public User(String name, String password) {
        this.username = name;
        this.password = password;
    }

    /**
     * Tries to log in you in and saves the token if successful.
     */
    public void login() {
        //TODO Error handling
        try {
            HttpResponse<JsonNode> response = Unirest
                .post(Config.getInstance().getApiUrl() + "/user/token/")
                .header("accept", "*/*")
                .queryString("username", username)
                .field("password", password)
                .asJson();
            JSONObject body = response.getBody().getObject();

            if (body.optInt("code", -1) == 200) {
                token = body.getString("message");
            }
            else {
                log.warn("Login failed.");
            }
        }
        catch (UnirestException e) {
            log.warn("Login error: " + e.getMessage());
        }
    }

    /**
     * @return the name
     */
    public String getName() {
        return username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @return the token
     */
    public String getToken() {
        return token;
    }
}
