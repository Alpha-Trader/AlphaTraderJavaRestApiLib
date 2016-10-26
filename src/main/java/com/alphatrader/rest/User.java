package com.alphatrader.rest;

import com.alphatrader.rest.util.ApiLibConfig;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
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
public class User {
    /**
     * The logger for this class.
     */
    private static final Log log = LogFactory.getLog(User.class);

    /**
     * The username.
     */
    private final StringProperty username = new SimpleStringProperty();
    /**
     * De passwoord.
     */
    private final StringProperty password = new SimpleStringProperty();

    /**
     * The user's email address.
     */
    private final StringProperty emailAddress = new SimpleStringProperty();

    /**
     * The gravatar hash of the user's profile image.
     */
    private final StringProperty gravatarHash = new SimpleStringProperty();

    /**
     * The unique id.
     */
    private final StringProperty id = new SimpleStringProperty();

    /**
     * The user capabilities.
     */
    private final ObjectProperty<UserCapabilities> userCapabilities = new SimpleObjectProperty<>();

    /**
     * The current session web token to use as login credentials.
     */
    private final StringProperty jwtToken = new SimpleStringProperty();

    /**
     * Default constructor for gson generation.
     */
    public User() {
        // Gson needs a default constructor.
    }

    /**
     * Creates a new user object with the given parameters.
     *
     * @param name     the username
     * @param password the password
     */
    public User(String name, String password) {
        this.username.setValue(name);
        this.password.setValue(password);
    }

    /**
     * Fetches current user from the server.
     *
     * @return the logged in user
     */
    @Nullable
    public static User getLoggedInUser() {
        return getSingleUser("user");
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
                .queryString("username", username.getValueSafe())
                .field("password", password.getValueSafe())
                .asJson();
            JSONObject body = response.getBody().getObject();

            if (body.optInt("code", -1) == 200) {
                jwtToken.setValue(body.getString("message"));
            }
            else {
                log.warn("Login failed.");
            }
        }
        catch (UnirestException e) {
            log.warn("Login error: ");
            log.error(e);
        }
    }

    /**
     * @return the name
     */
    public String getUsername() {
        return username.getValue();
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password.getValue();
    }

    /**
     * @return the token
     */
    public String getToken() {
        return jwtToken.getValue();
    }

    /**
     * @return the unique user id
     */
    public String getId() {
        return id.getValue();
    }

    /**
     * @return the user's email address
     */
    public String getEmailAddress() {
        return emailAddress.getValue();
    }

    /**
     * @return the gravatar hash
     */
    public String getGravatarHash() {
        return gravatarHash.getValue();
    }

    /**
     * @return the user's capabilities
     */
    public UserCapabilities getUserCapabilities() {
        return userCapabilities.getValue();
    }

    @Override
    public String toString() {
        return "User{"
            + "username=" + username.getValue()
            + ", emailAddress=" + emailAddress.getValue()
            + ", gravatarHash=" + gravatarHash.getValue()
            + ", id=" + id.getValue()
            + ", userCapabilities=" + userCapabilities.getValue()
            + ", jwtToken=" + jwtToken.getValue()
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

        return id.getValue() != null ? id.getValue().equals(user.id.getValue())
            : user.id.getValue() == null;

    }

    @Override
    public int hashCode() {
        return id.getValue() != null ? id.getValue().hashCode() : 0;
    }
}
