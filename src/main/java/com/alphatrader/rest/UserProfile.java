package com.alphatrader.rest;

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
    private final User user = null;

    /**
     * The user's bank account.
     */
    private final BankAccount bankAccount = null;

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
        return Arrays.asList(cashTransferLogs != null ? cashTransferLogs : new CashTransferLog[0]);
    }

    /**
     * @return the user's employments
     */
    public List<EmploymentAgreement> getEmployments() {
        return Arrays.asList(employments != null ? employments : new EmploymentAgreement[0]);
    }

    /**
     * @return the polls concerning the user
     */
    public List<Poll> getPolls() {
        return Arrays.asList(polls != null ? polls : new Poll[0]);
    }

    /**
     * @return the polls initiated by the user
     */
    public List<Poll> getInitiatedPolls() {
        return Arrays.asList(initiatedPolls != null ? initiatedPolls : new Poll[0]);
    }

    /**
     * @return a list of the user's salary payments
     */
    public List<SalaryPayment> getSalaryPayments() {
        return Arrays.asList(salaryPayments != null ? salaryPayments : new SalaryPayment[0]);
    }

    /**
     * @return the user this profile belongs to
     */
    public User getUser() {
        return user;
    }

    /**
     * @return the user's bank account
     */
    public BankAccount getBankAccount() {
        return bankAccount;
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
            + ", user=" + user
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

        return user != null ? user.equals(that.user) : that.user == null;

    }

    @Override
    public int hashCode() {
        return user != null ? user.hashCode() : 0;
    }
}
