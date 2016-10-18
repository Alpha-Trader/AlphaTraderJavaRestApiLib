package com.alphatrader.rest;

import com.alphatrader.rest.util.LocalDateTimeDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for fetching lists of security price histories.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0.0
 */
public final class SecurityPrice {
    /**
     * The logger for this class.
     */
    private static final Log log = LogFactory.getLog(LastPrice.class);

    /**
     * Gson instance for deserialization.
     */
    private static final Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class,
        new LocalDateTimeDeserializer()).create();

    /**
     * List type for gson deserialization.
     */
    private static final java.lang.reflect.Type listType = new TypeToken<ArrayList<LastPrice>>() {
    }.getType();

    private SecurityPrice() {
    }

    /**
     * Fetches all prices for the given company.
     *
     * @param securityIdentifier the securityIdentifier
     * @return the list of prices
     */
    public static List<LastPrice> getSecurityPrices(String securityIdentifier) {
        return getFromApi("?securityIdentifier=" + securityIdentifier);
    }

    /**
     * Fetches all prices for the given company.
     *
     * @param securityIdentifier the securityIdentifier
     * @param startDate          the lower date boundary
     * @return the list of prices
     */
    public static List<LastPrice> getSecurityPrices(String securityIdentifier, LocalDateTime startDate) {
        return getFromApi("?securityIdentifier=" + securityIdentifier + "&startDate" + startDate
            .atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
    }

    /**
     * Fetches all prices for the given company.
     *
     * @param securityIdentifier the securityIdentifier
     * @param startDate          the lower date boundary
     * @param endDate            the upper date boundary
     * @return the list of prices
     */
    public static List<LastPrice> getSecurityPrices(String securityIdentifier, LocalDateTime startDate,
                                                    LocalDateTime endDate) {
        return getFromApi("?securityIdentifier=" + securityIdentifier + "&startDate" + startDate
            .atZone(ZoneId.systemDefault()).toInstant().toEpochMilli() + "&endDate" + endDate
            .atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
    }

    /**
     * Wrapper class for api access.
     *
     * @param suffix the api suffix
     * @return the requested list of prices.
     */
    private static List<LastPrice> getFromApi(String suffix) {
        List<LastPrice> myReturn = new ArrayList<>();

        try {
            HttpResponse<JsonNode> response = Http.getInstance().get("/api/securityPrices/" + suffix);
            String orders = response.getBody().getArray().toString();
            myReturn = gson.fromJson(orders, listType);
        }
        catch (UnirestException ue) {
            log.error("Error fetching security prices: " + ue.getMessage());
            StringWriter stringWriter = new StringWriter();
            ue.printStackTrace(new PrintWriter(stringWriter));
            log.debug(stringWriter.toString());
        }

        return myReturn;
    }
}
