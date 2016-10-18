package com.alphatrader.rest;

import com.alphatrader.rest.util.LocalDateTimeDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a cash transfer message in the game.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0.0
 */
@SuppressWarnings("ConstantConditions")
public class CashTransferLog {
    /**
     * The logger for this class.
     */
    private static final Log log = LogFactory.getLog(CashTransferLog.class);

    /**
     * Gson instance for deserialization.
     */
    private static final Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class,
        new LocalDateTimeDeserializer()).create();

    /**
     * The list type we use.
     */
    private static final Type listType = new TypeToken<ArrayList<CashTransferLog>>() {
    }.getType();

    /**
     * The amount of cash transfered.
     */
    private final Double amount = null;

    /**
     * The bank account id of the receiver.
     */
    private final String receiverBankAccount = null;

    /**
     * The bank account id of the sender.
     */
    private final String senderBankAccount = null;

    /**
     * The date the transfer took place.
     */
    private final LocalDateTime date = null;

    /**
     * The message describing this transfer.
     */
    private final Message message = null;

    /**
     * The id of this transfer.
     */
    private final String id = null;

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
    @NotNull
    public static List<CashTransferLog> getCashTransferLogs(LocalDateTime startDate,
                                                            LocalDateTime endDate,
                                                            String senderBankAccountId,
                                                            String receiverBankAccountId) {
        List<CashTransferLog> myReturn = new ArrayList<>();

        String request = "/api/cashtransferlogs/";
        List<String> options = new ArrayList<>();

        if (startDate != null) {
            options.add("startDate=" + startDate.atZone(ZoneId.systemDefault()).toInstant()
                .toEpochMilli());
        }
        if (endDate != null) {
            options.add("endDate=" + endDate.atZone(ZoneId.systemDefault()).toInstant()
                .toEpochMilli());
        }
        if (senderBankAccountId != null && !senderBankAccountId.equals("")) {
            options.add("senderBankAccountId=" + senderBankAccountId);
        }
        if (receiverBankAccountId != null && !receiverBankAccountId.equals("")) {
            options.add("receiverBankAccountId=" + receiverBankAccountId);
        }

        if (options.size() > 0) {
            request += "?" + options.stream().reduce("", (a, b) -> (a + "&" + b));
        }

        return Http.getMultipleObjectFromApi(CashTransferLog.class, request);
    }

    /**
     * @return the amount that was transferred
     */
    public Double getAmount() {
        return amount;
    }

    /**
     * @return the receiver's bank account id
     */
    public String getReceiverBankAccount() {
        return receiverBankAccount;
    }

    /**
     * @return the sender's bank account id
     */
    public String getSenderBankAccount() {
        return senderBankAccount;
    }

    /**
     * @return the date the transfer took place
     */
    public LocalDateTime getDate() {
        return date;
    }

    /**
     * @return the subject message of the transfer
     */
    public Message getMessage() {
        return message;
    }

    /**
     * @return the transfer id
     */
    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "CashTransferLog{"
            + "amount=" + amount
            + ", receiverBankAccount='" + receiverBankAccount + '\''
            + ", senderBankAccount='" + senderBankAccount + '\''
            + ", date=" + date
            + ", message=" + message
            + ", id='" + id + '\''
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

        return id != null ? id.equals(that.id) : that.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
