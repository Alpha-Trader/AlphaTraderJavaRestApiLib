package com.alphatrader.rest;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.http.HttpResponseFactory;
import org.apache.http.HttpVersion;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.DefaultHttpResponseFactory;
import org.apache.http.message.BasicStatusLine;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test helper that allows us to quickly mock the API itself.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0.0
 */
public class HttpResponder {
    private static Gson gson = new Gson();
    private static HttpResponder instance;

    public static HttpResponder getInstance() {
        if (instance == null) {
            instance = new HttpResponder();
        }

        return instance;
    }

    /**
     * A list of all get api endpoints along with sample responses to test the reaction.
     */
    private Map<String, ApiResponse> getResponses = new HashMap<>();

    private HttpResponder() {
        this.fillMap();
    }

    /**
     * Fills the map
     */
    private void fillMap() {
        try {
            File getResponseFile = new File(getClass().getClassLoader().getResource("get_responses.json")
                .getFile());
            FileReader fileReader = new FileReader(getResponseFile);
            getResponses = new Gson().fromJson(new BufferedReader(fileReader),
                new TypeToken<HashMap<String, ApiResponse>>() {
                }.getType());
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }
    }

    public Http getMock() {
        HttpResponseFactory factory = new DefaultHttpResponseFactory();
        Http mockHttp = mock(Http.class);

        for (Map.Entry<String, ApiResponse> entry : getResponses.entrySet()) {
            try {
                String requestUrl = entry.getKey();
                String json = entry.getValue().content.toString();
                int status = entry.getValue().status;

                org.apache.http.HttpResponse response = factory.newHttpResponse(
                    new BasicStatusLine(HttpVersion.HTTP_1_1, status, ""), null);
                response.setEntity(new StringEntity(json));
                HttpResponse<String> httpResponse = new HttpResponse<>(response, String.class);
                when(mockHttp.get(eq(requestUrl))).thenReturn(httpResponse);
            }
            catch (UnirestException | UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        return mockHttp;
    }

    public String getJsonForRequest(String key) {
        if(!getResponses.containsKey(key)) {
            fail("No response for '" + key + "' found");
        }
        return getResponses.get(key).content.toString();
    }

    private static class ApiResponse {
        public int status = 0;
        public JsonObject content = null;

        @Override
        public String toString() {
            return "ApiResponse{"
                + "status=" + status
                + ", content='" + content + '\''
                + '}';
        }
    }
}
