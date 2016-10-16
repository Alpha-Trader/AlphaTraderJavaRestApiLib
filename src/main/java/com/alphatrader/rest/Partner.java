package com.alphatrader.rest;

import com.alphatrader.rest.util.Http;
import com.alphatrader.rest.util.LocalDateTimeDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jetbrains.annotations.NotNull;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Lists all users who are registered as partners.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0.0
 */
public class Partner {
    /**
     * The logger for this class. Use this to write messages to the console.
     */
    private static final Log log = LogFactory.getLog(Partner.class);

    /**
     * Gson instance for deserialization.
     */
    private static final Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class,
        new LocalDateTimeDeserializer()).create();

    /**
     * List type for gson deserialization.
     */
    private static final Type listType = new TypeToken<ArrayList<Partner>>() { }.getType();

    /**
     * Fetches all registered partners.
     *
     * @return all partners
     */
    @NotNull
    public static List<User> getAllPartners() {
        List<User> myReturn = new ArrayList<>();

        try {
            HttpResponse<JsonNode> response = Http.getInstance().get("/api/partners/");

            if (response != null && response.getStatus() == 200) {
                myReturn = gson.fromJson(response.getBody()
                    .getArray()
                    .toString(), listType);
            }
        }
        catch (UnirestException ue) {
            log.error("Error fetching partners: " + ue.getMessage());
            StringWriter stringWriter = new StringWriter();
            ue.printStackTrace(new PrintWriter(stringWriter));
            log.debug(stringWriter.toString());
        }

        return myReturn;
    }
}
