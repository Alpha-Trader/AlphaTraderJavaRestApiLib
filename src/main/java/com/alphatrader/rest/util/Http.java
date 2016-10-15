package com.alphatrader.rest.util;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * Wrapper to allow testing of all webservice classes.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0.0
 */
public class Http {
    /**
     * Singleton instance
     */
    private static Http instance = new Http();

    public static void setInstance(Http atHttp) {
        instance = atHttp;
    }

    public static Http getInstance() {
        return instance;
    }

    public HttpResponse<JsonNode> get(String url) throws UnirestException {
        return Unirest.get(Config.getInstance().getApiUrl() + url)
            .header("accept", "*/*").header("Authorization", "Bearer " + Config.getInstance().getUser().getToken())
            .header("X-Authorization", "e1d149fb-0b2a-4cf5-9ef7-17749bf9d144").asJson();
    }

    public HttpResponse<JsonNode> post(String url) throws UnirestException {
        return Unirest.post(Config.getInstance().getApiUrl() + url)
            .header("accept", "*/*").header("Authorization", "Bearer " + Config.getInstance().getUser().getToken())
            .header("X-Authorization", "e1d149fb-0b2a-4cf5-9ef7-17749bf9d144").asJson();
    }
}
