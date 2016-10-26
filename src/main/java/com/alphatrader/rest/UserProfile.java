package com.alphatrader.rest;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

/**
 * Represents a user profile in the game.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0.0
 */
public class UserProfile {
    /**
     * All cash transfer logs for this user's interactions.
     */
    private final CashTransferLog[] cashTransferLogs = new CashTransferLog[0];

    /**
     * All employment agreements of this user.
     */
    private final EmploymentAgreement[] employments = new EmploymentAgreement[0];

    /**
     * All polls that concern this user.
     */
    private final Poll[] polls = new Poll[0];

    /**
     * All polls that have been initiated by this user.
     */
    private final Poll[] initiatedPolls = new Poll[0];

    /**
     * All salary payments this user has gotten.
     */
    private final SalaryPayment[] salaryPayments = new SalaryPayment[0];

    /**
     * The user with this profile.
     */
    private final ObjectProperty<User> user = new SimpleObjectProperty<>();

    /**
     * The user's bank account.
     */
    private final ObjectProperty<BankAccount> bankAccount = new SimpleObjectProperty<>();

    /**
     * The user's locale.
     */
    private final StringProperty locale = new SimpleStringProperty();

    /**
     * Returns the profile of the given user.
     *
     * @param user the user to fetch the profile from
     * @return the user's profile
     */
    @Nullable
    public static UserProfile getUserProfile(User user) {
        return getUserProfile(user.getUsername());
    }

    /**
     * Returns the profile of the given user.
     *
     * @param username the username to fetch the profile from
     * @return the user's profile
     */
    @Nullable
    public static UserProfile getUserProfile(String username) {
        return Http.getSingleObjectFromApi(UserProfile.class, "/api/userprofiles/" + username);
    }

    /**
     * @return the user's cash transfer logs
     */
    public List<CashTransferLog> getCashTransferLogs() {
        return Arrays.asList(cashTransferLogs);
    }

    /**
     * @return the user's employments
     */
    public List<EmploymentAgreement> getEmployments() {
        return Arrays.asList(employments);
    }

    /**
     * @return the polls concerning the user
     */
    public List<Poll> getPolls() {
        return Arrays.asList(polls);
    }

    /**
     * @return the polls initiated by the user
     */
    public List<Poll> getInitiatedPolls() {
        return Arrays.asList(initiatedPolls);
    }

    /**
     * @return a list of the user's salary payments
     */
    public List<SalaryPayment> getSalaryPayments() {
        return Arrays.asList(salaryPayments);
    }

    /**
     * @return the user this profile belongs to
     */
    public User getUser() {
        return user.getValue();
    }

    /**
     * @return the user's bank account
     */
    public BankAccount getBankAccount() {
        return bankAccount.getValue();
    }

    /**
     * @return the user's locale
     */
    public String getLocale() {
        return locale.getValue();
    }

    @Override
    public String toString() {
        return "UserProfile{"
            + "cashTransferLogs=" + Arrays.toString(cashTransferLogs)
            + ", employments=" + Arrays.toString(employments)
            + ", polls=" + Arrays.toString(polls)
            + ", initiatedPolls=" + Arrays.toString(initiatedPolls)
            + ", salaryPayments=" + Arrays.toString(salaryPayments)
            + ", user=" + user.getValue()
            + ", bankAccount=" + bankAccount
            + ", locale='" + locale + '\''
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

        UserProfile that = (UserProfile) o;

        return user.getValue() != null ? user.getValue().equals(that.user.getValue())
            : that.user.getValue() == null;

    }

    @Override
    public int hashCode() {
        return user.getValue() != null ? user.getValue().hashCode() : 0;
    }
}
