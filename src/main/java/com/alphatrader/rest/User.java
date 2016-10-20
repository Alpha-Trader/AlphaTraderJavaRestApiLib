package com.alphatrader.rest;

import com.alphatrader.rest.util.ApiLibConfig;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;

import java.util.List;

/**
 * This class stores the login information and session token of the logged-in user.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0
 */
@SuppressWarnings("ConstantConditions")
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
     * The user's email address.
     */
    private final String emailAddress = null;

    /**
     * The gravatar hash of the user's profile image.
     */
    private final String gravatarHash = null;

    /**
     * The unique id.
     */
    private final String id = null;

    /**
     * The user capabilities.
     */
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
    @Nullable
    public static User getLoggedInUser() {
        return getSingleUser("user/");
    }

    /**
     * Fetches the user with the given id.
     *
     * @param userId the user id to look for
     * @return the requested user
     */
    @Nullable
    public static User getById(String userId) {
        return getSingleUser("users/" + userId);
    }

    /**
     * Fetches the user with the given username.
     *
     * @param username the user to look for
     * @return the requested user
     */
    @Nullable
    public static User getByUsername(String username) {
        return getSingleUser("users/username/" + username);
    }

    /**
     * Wrapper for fetching a single user from the API.
     *
     * @param suffix the api suffix
     * @return the requested user
     */
    @Nullable
    private static User getSingleUser(String suffix) {
        return Http.getSingleObjectFromApi(User.class, "/api/" + suffix);
    }

    /**
     * Searches for a user with the specified name part.
     *
     * @param namePart the name part
     * @return a list of matching users
     */
    @NotNull
    public static List<User> searchUser(String namePart) {
        return getMultipleUsers("search/users/" + namePart);
    }

    /**
     * Fetches all users in the game.
     *
     * @return a list of all users
     */
    @NotNull
    public static List<User> getAllUsers() {
        return getMultipleUsers("users");
    }

    /**
     * Wrapper for fetching multiple users from the API.
     *
     * @param suffix the api suffix
     * @return the requested users
     */
    @NotNull
    private static List<User> getMultipleUsers(String suffix) {
        return Http.getMultipleObjectFromApi(User.class, "/api/" + suffix);
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
    public String getUsername() {
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

    /**
     * @return the user's email address
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * @return the gravatar hash
     */
    public String getGravatarHash() {
        return gravatarHash;
    }

    /**
     * @return the user's capabilities
     */
    public UserCapabilities getUserCapabilities() {
        return userCapabilities;
    }

    @Override
    public String toString() {
        return "User{"
            + "username='" + username + '\''
            + ", emailAddress='" + emailAddress + '\''
            + ", gravatarHash='" + gravatarHash + '\''
            + ", id='" + id + '\''
            + ", userCapabilities=" + userCapabilities
            + ", jwtToken='" + jwtToken + '\''
            + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;

        return id != null ? id.equals(user.id) : user.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
