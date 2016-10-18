package com.alphatrader.rest;

import com.alphatrader.rest.util.ApiLibConfig;
import com.alphatrader.rest.util.ZonedDateTimeDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Wrapper to allow testing of all webservice classes.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0.0
 */
class Http {
    /**
     * The logger for this class.
     */
    private static final Log log = LogFactory.getLog(Http.class);

    /**
     * Gson instance for deserialization.
     */
    private static final Gson gson = new GsonBuilder().registerTypeAdapter(ZonedDateTime.class,
        new ZonedDateTimeDeserializer()).create();

    /**
     * Singleton instance
     */
    private static Http instance = new Http();

    /**
     * Replaces the current instance with a different one. Use for testing only.
     *
     * @param atHttp the new instance to use
     */
    static void setInstance(Http atHttp) {
        instance = atHttp;
    }

    /**
     * @return the singleton instance
     */
    @NotNull
    public static Http getInstance() {
        return instance;
    }

    /**
     * Fetches a specific object from the API.
     *
     * @param typeParameterClass the class of the type of object to return
     * @param suffix             the api endpoint suffix
     * @param <Type>             the type of object to return
     * @return the requested object
     */
    @Nullable
    static <Type> Type getSingleObjectFromApi(Class<Type> typeParameterClass, String suffix) {
        Type myReturn = null;

        try {
            HttpResponse<JsonNode> response = Http.getInstance().get(suffix);

            if (response != null && response.getStatus() == 200) {
                myReturn = gson.fromJson(response.getBody()
                    .getObject().toString(), typeParameterClass);
            }
        }
        catch (UnirestException ue) {
            log.error("Error fetching " + typeParameterClass.getSimpleName() + ": " + ue.getMessage());
            StringWriter stringWriter = new StringWriter();
            ue.printStackTrace(new PrintWriter(stringWriter));
            log.debug(stringWriter.toString());
        }

        return myReturn;
    }

    /**
     * Fetches a list of objects from the API.
     *
     * @param typeParameterClass the class of the type of objects to return
     * @param suffix             the api endpoint suffix
     * @param <Type>             the type of object to return
     * @return the requested object
     */
    @NotNull
    static <Type> List<Type> getMultipleObjectFromApi(Class<Type> typeParameterClass, String suffix) {
        List<Type> myReturn = new ArrayList<>();
        java.lang.reflect.Type type = new TypeToken<ArrayList<Type>>() {
        }.getType();

        try {
            HttpResponse<JsonNode> response = Http.getInstance().get(suffix);

            if (response != null && response.getStatus() == 200) {
                myReturn = gson.fromJson(response.getBody()
                    .getArray().toString(), type);
            }
        }
        catch (UnirestException ue) {
            log.error("Error fetching " + typeParameterClass.getSimpleName() + "s: " + ue.getMessage());
            StringWriter stringWriter = new StringWriter();
            ue.printStackTrace(new PrintWriter(stringWriter));
            log.debug(stringWriter.toString());
        }

        return myReturn;
    }

    /**
     * Performs a get request to the REST API.
     *
     * @param url the URL to the API endpoint
     * @return the HttpResponse of the server
     * @throws UnirestException if anything goes wrong with the request
     */
    public HttpResponse<JsonNode> get(String url) throws UnirestException {
        return Unirest.get(ApiLibConfig.getInstance().getApiUrl() + url)
            .header("accept", "*/*").header("Authorization", "Bearer "
                + ApiLibConfig.getInstance().getUser().getToken())
            .header("X-Authorization", ApiLibConfig.getInstance().getPartnerId()).asJson();
    }

    /**
     * Performs a post request to the REST API.
     *
     * @param url the URL to the API endpoint
     * @return the HttpResponse of the server
     * @throws UnirestException if anything goes wrong with the request
     */
    public HttpResponse<JsonNode> post(String url) throws UnirestException {
        return Unirest.post(ApiLibConfig.getInstance().getApiUrl() + url)
            .header("accept", "*/*").header("Authorization", "Bearer "
                + ApiLibConfig.getInstance().getUser().getToken())
            .header("X-Authorization", ApiLibConfig.getInstance().getPartnerId()).asJson();
    }
}
