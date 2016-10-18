package com.alphatrader.rest;

import com.alphatrader.rest.util.ApiLibConfig;
import com.alphatrader.rest.util.LocalDateTimeDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
     * Gson instance for deserialization.
     */
    private static final Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class,
        new LocalDateTimeDeserializer()).create();

    /**
     * List type for gson deserialization.
     */
    private static final Type listType = new TypeToken<ArrayList<User>>() {
    }.getType();
    /**
     * The username.
     */
    private final String username;
    /**
     * De passwoord.
     */
    private final String password;
    private final String emailAddress = null;
    private final String gravatarHash = null;
    private final String id = null;
    private final UserCapabilities userCapabilities = null;
    /**
     * The current session web token to use as login credentials.
     */
    private String jwtToken;

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
     * Fetches current user from the server.
     *
     * @return the logged in user
     */
    public static User getLoggedInUser() {
        return getSingleUser("user/");
    }

    /**
     * Fetches the user with the given id.
     *
     * @param userId the user id to look for
     * @return the requested user
     */
    public static User getById(String userId) {
        return getSingleUser("users/" + userId);
    }

    /**
     * Fetches the user with the given username.
     *
     * @param username the user to look for
     * @return the requested user
     */
    public static User getByUsername(String username) {
        return getSingleUser("users/username/" + username);
    }

    /**
     * Wrapper for fetching a single user from the API.
     *
     * @param suffix the api suffix
     * @return the requested user
     */
    private static User getSingleUser(String suffix) {
        User myReturn = null;

        try {
            HttpResponse<JsonNode> response = Http.getInstance().get("/api/" + suffix);

            if (response != null && response.getStatus() == 200) {
                myReturn = gson.fromJson(response.getBody()
                    .getObject()
                    .toString(), User.class);
            }
        }
        catch (UnirestException ue) {
            log.error("Error fetching user: " + ue.getMessage());
            StringWriter stringWriter = new StringWriter();
            ue.printStackTrace(new PrintWriter(stringWriter));
            log.debug(stringWriter.toString());
        }

        return myReturn;
    }

    /**
     * Searches for a user with the specified name part.
     *
     * @param namePart the name part
     * @return a list of matching users
     */
    public static List<User> searchUser(String namePart) {
        return getMultipleUsers("search/users/" + namePart);
    }

    /**
     * Fetches all users in the game.
     *
     * @return a list of all users
     */
    public static List<User> getAllUsers() {
        return getMultipleUsers("users");
    }

    /**
     * Wrapper for fetching multiple users from the API.
     *
     * @param suffix the api suffix
     * @return the requested users
     */
    private static List<User> getMultipleUsers(String suffix) {
        List<User> myReturn = new ArrayList<>();

        try {
            HttpResponse<JsonNode> response = Http.getInstance().get("/api/" + suffix);

            if (response != null && response.getStatus() == 200) {
                myReturn = gson.fromJson(response.getBody()
                    .getArray()
                    .toString(), listType);
            }
        }
        catch (UnirestException ue) {
            log.error("Error fetching users: " + ue.getMessage());
            StringWriter stringWriter = new StringWriter();
            ue.printStackTrace(new PrintWriter(stringWriter));
            log.debug(stringWriter.toString());
        }

        return myReturn;
    }

    /**
     * Tries to log in you in and saves the token if successful.
     */
    public void login() {
        try {
            HttpResponse<JsonNode> response = Unirest
                .post(ApiLibConfig.getInstance().getApiUrl() + "/user/token/")
                .header("accept", "*/*")
                .queryString("username", username)
                .field("password", password)
                .asJson();
            JSONObject body = response.getBody().getObject();

            if (body.optInt("code", -1) == 200) {
                jwtToken = body.getString("message");
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
        return jwtToken;
    }

    /**
     * @return the unique user id
     */
    public String getId() {
        return id;
    }
}
