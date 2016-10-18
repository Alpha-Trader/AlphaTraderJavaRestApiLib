package com.alphatrader.rest;

import com.alphatrader.rest.util.ApiLibConfig;
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
class Http {
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
    public static Http getInstance() {
        return instance;
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
