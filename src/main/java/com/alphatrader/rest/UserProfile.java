package com.alphatrader.rest;

import javafx.beans.property.*;
import javafx.collections.ObservableList;
import org.jetbrains.annotations.Nullable;

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
    private final ObservableList<CashTransferLog> cashTransferLogs = new SimpleListProperty<>();

    /**
     * All employment agreements of this user.
     */
    private final ObservableList<EmploymentAgreement> employments = new SimpleListProperty<>();

    /**
     * All polls that concern this user.
     */
    private final ObservableList<Poll> polls = new SimpleListProperty<>();

    /**
     * All polls that have been initiated by this user.
     */
    private final ObservableList<Poll> initiatedPolls = new SimpleListProperty<>();

    /**
     * All salary payments this user has gotten.
     */
    private final ObservableList<SalaryPayment> salaryPayments = new SimpleListProperty<>();

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
    @PublicAPI
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
    @PublicAPI
    @Nullable
    public static UserProfile getUserProfile(String username) {
        return Http.getSingleObjectFromApi(UserProfile.class, "/api/userprofiles/" + username);
    }

    /**
     * @return the user's cash transfer logs
     */
    @PublicAPI
    public ObservableList<CashTransferLog> getCashTransferLogs() {
        return cashTransferLogs;
    }

    /**
     * @return the user's employments
     */
    @PublicAPI
    public ObservableList<EmploymentAgreement> getEmployments() {
        return employments;
    }

    /**
     * @return the polls concerning the user
     */
    @PublicAPI
    public ObservableList<Poll> getPolls() {
        return polls;
    }

    /**
     * @return the polls initiated by the user
     */
    @PublicAPI
    public ObservableList<Poll> getInitiatedPolls() {
        return initiatedPolls;
    }

    /**
     * @return a list of the user's salary payments
     */
    public ObservableList<SalaryPayment> getSalaryPayments() {
        return salaryPayments;
    }

    /**
     * @return the user this profile belongs to
     */
    @PublicAPI
    public User getUser() {
        return user.getValue();
    }

    /**
     * @return the user's bank account
     */
    @PublicAPI
    public BankAccount getBankAccount() {
        return bankAccount.getValue();
    }

    /**
     * @return the user's locale
     */
    @PublicAPI
    public String getLocale() {
        return locale.getValue();
    }

    /**
     * @return the user property.
     */
    @PublicAPI
    public ReadOnlyObjectProperty<User> userProperty() {
        return user;
    }

    /**
     * @return the bank account property.
     */
    @PublicAPI
    public ReadOnlyObjectProperty<BankAccount> bankAccountProperty() {
        return bankAccount;
    }

    /**
     * @return the locale property.
     */
    @PublicAPI
    public ReadOnlyStringProperty localeProperty() {
        return locale;
    }

    @Override
    public String toString() {
        return "UserProfile{"
            + "cashTransferLogs=" + cashTransferLogs.toString()
            + ", employments=" + employments.toString()
            + ", polls=" + polls.toString()
            + ", initiatedPolls=" + initiatedPolls.toString()
            + ", salaryPayments=" + salaryPayments.toString()
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
