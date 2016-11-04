package com.alphatrader.rest;

import javafx.beans.property.*;
import org.jetbrains.annotations.NotNull;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a cash transfer message in the game.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0.0
 */
public class CashTransferLog {
    /**
     * The amount of cash transfered.
     */
    private final DoubleProperty amount = new SimpleDoubleProperty();

    /**
     * The bank account id of the receiver.
     */
    private final StringProperty receiverBankAccount = new SimpleStringProperty();

    /**
     * The bank account id of the sender.
     */
    private final StringProperty senderBankAccount = new SimpleStringProperty();

    /**
     * The date the transfer took place.
     */
    private final ObjectProperty<ZonedDateTime> date = new SimpleObjectProperty<>();

    /**
     * The message describing this transfer.
     */
    private final ObjectProperty<Message> message = new SimpleObjectProperty<>();

    /**
     * The id of this transfer.
     */
    private final StringProperty id = new SimpleStringProperty();

    /**
     * Returns all cash transfer logs that fit the provided filters. All filters are optional. They are
     * ignored if they are null or an empty string.
     *
     * @param startDate             the lower date boundary to fetch logs from
     * @param endDate               the upper date boundary to fetch logs from
     * @param senderBankAccountId   the id of the sender's bank account
     * @param receiverBankAccountId the id of the receiver's bank account
     * @return a list of all cash transfers matching the given criteria
     */
    @PublicAPI
    @NotNull
    public static List<CashTransferLog> getCashTransferLogs(ZonedDateTime startDate,
                                                            ZonedDateTime endDate,
                                                            String senderBankAccountId,
                                                            String receiverBankAccountId) {
        String request = "/api/cashtransferlogs/";
        List<String> options = new ArrayList<>();

        if (startDate != null) {
            options.add("startDate=" + startDate.toInstant().toEpochMilli());
        }
        if (endDate != null) {
            options.add("endDate=" + endDate.toInstant().toEpochMilli());
        }
        if (senderBankAccountId != null && !"".equals(senderBankAccountId)) {
            options.add("senderBankAccountId=" + senderBankAccountId);
        }
        if (receiverBankAccountId != null && !"".equals(receiverBankAccountId)) {
            options.add("receiverBankAccountId=" + receiverBankAccountId);
        }

        if (!options.isEmpty()) {
            request += "?" + String.join("&", options);
        }

        return Http.getMultipleObjectFromApi(CashTransferLog.class, request);
    }

    /**
     * @return the amount that was transferred
     */
    @PublicAPI
    public Double getAmount() {
        return amount.getValue();
    }

    /**
     * @return the receiver's bank account id
     */
    @PublicAPI
    public String getReceiverBankAccount() {
        return receiverBankAccount.getValue();
    }

    /**
     * @return the sender's bank account id
     */
    @PublicAPI
    public String getSenderBankAccount() {
        return senderBankAccount.getValue();
    }

    /**
     * @return the date the transfer took place
     */
    @PublicAPI
    public ZonedDateTime getDate() {
        return date.getValue();
    }

    /**
     * @return the subject message of the transfer
     */
    @PublicAPI
    public Message getMessage() {
        return message.getValue();
    }

    /**
     * @return the transfer id
     */
    @PublicAPI
    public String getId() {
        return id.getValue();
    }

    @Override
    public String toString() {
        return "CashTransferLog{"
            + "amount=" + amount.getValue()
            + ", receiverBankAccount='" + receiverBankAccount.getValue() + '\''
            + ", senderBankAccount='" + senderBankAccount.getValue() + '\''
            + ", date=" + date.getValue()
            + ", message=" + message.getValue()
            + ", id='" + id.getValue() + '\''
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

        CashTransferLog that = (CashTransferLog) o;

        return id.getValue() != null ? id.getValue().equals(that.id.getValue())
            : that.id.getValue() == null;

    }

    @Override
    public int hashCode() {
        return id.getValue() != null ? id.getValue().hashCode() : 0;
    }
}
