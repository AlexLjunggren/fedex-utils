package io.ljunggren.fedexUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;

import com.fasterxml.jackson.core.JsonProcessingException;

import io.ljunggren.fedexUtils.authorization.OauthError;
import io.ljunggren.fedexUtils.authorization.OauthResponse;
import io.ljunggren.fedexUtils.tracking.request.TrackingRequest;
import io.ljunggren.fedexUtils.tracking.response.TrackingError;
import io.ljunggren.fedexUtils.tracking.response.TrackingResponse;
import io.ljunggren.jsonUtils.JsonUtils;

public class FedexApi {
    
    private String clientId;
    private String clientSecret;
    private FedexProperties properties;
    
    public static final List<Integer> KNOWN_AUTH_CODES = Arrays.asList(new Integer[] {
            200, 401, 500, 503
    });
    public static final List<Integer> KNOWN_REQUEST_CODES = Arrays.asList(new Integer[] {
            200, 400, 401, 403, 404, 500, 503
    });
    public static final int THROTTLING_CODE = 429;

    public FedexApi(FedexEnvironment environment, String clientId, String clientSecret) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.properties = new FedexProperties(environment);
    }
    
    public OauthResponse authorize() throws IOException {
        return authorize(getHttpClient());
    }
    
    // package default for unit testing
    OauthResponse authorize(CloseableHttpClient httpClient) throws IOException {
        HttpPost post = new HttpPost(properties.getOauthUrl());
        List<NameValuePair> parameters = Arrays.asList(new BasicNameValuePair[] {
                new BasicNameValuePair("grant_type", "client_credentials"),
                new BasicNameValuePair("client_id", clientId),
                new BasicNameValuePair("client_secret", clientSecret)
        });
        try {
            post.setEntity(new UrlEncodedFormEntity(parameters));
            CloseableHttpResponse response = httpClient.execute(post);
            int responseCode = response.getStatusLine().getStatusCode();
            if (KNOWN_AUTH_CODES.contains(responseCode)) {
                String json = getResult(response);
                return parse(json, OauthResponse.class);
            }
            return new OauthResponse().addError(
                    new OauthError("Authorization Error", String.format("Unknown response code %d", responseCode)));
        } catch(Exception e) {
            return new OauthResponse().addError(
                    new OauthError("Authorization Error", e.getMessage()));
        } finally {
            httpClient.close();
        }
    }
    
    public TrackingResponse track(TrackingRequest trackingRequest, String accessToken) throws IOException {
        return track(trackingRequest, accessToken, getHttpClient());
    }
    
    // package private for unit testing
    public TrackingResponse track(TrackingRequest trackingRequest, String accessToken, CloseableHttpClient httpClient) throws IOException {
        HttpPost post = new HttpPost(properties.getTrackingUrl());
        Header[] headers = new Header[] {
                new BasicHeader("content-type", "application/json"),
                new BasicHeader("x-locale", "en_US"),
                new BasicHeader("authorization", String.format("Bearer %s", accessToken))
        };
        try {
            post.setHeaders(headers);
            post.setEntity(new StringEntity(JsonUtils.objectToJson(trackingRequest)));
            CloseableHttpResponse response = httpClient.execute(post);
            int responseCode = response.getStatusLine().getStatusCode();
            String json = getResult(response);
            if (KNOWN_REQUEST_CODES.contains(responseCode)) {
                return parse(json, TrackingResponse.class);
            }
            if (THROTTLING_CODE == responseCode) {
                return new TrackingResponse().addError(
                        new TrackingError("Throttled", null, "Too many requests"));
            }
            return new TrackingResponse().addError(
                    new TrackingError("Tracking Error", null, String.format("Unknown response code %d", responseCode)));
        } catch(Exception e) {
            return new TrackingResponse().addError(
                    new TrackingError("Tracking Error", null, e.getMessage()));
        } finally {
            httpClient.close();
        }
    }
    
    private CloseableHttpClient getHttpClient() {
        return HttpClients.custom()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setCookieSpec(CookieSpecs.STANDARD).build())
                .build();
    }
    
    private <T> T parse(String json, Class<T> clazz) throws Exception {
        try {
            return JsonUtils.jsonToObject(json, clazz);
        } catch (JsonProcessingException e) {
            throw new Exception("Unable to parse response");
        }
    }
    
    private static String getResult(HttpResponse response) throws UnsupportedOperationException, IOException {
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        StringBuilder result = new StringBuilder();
        String line = new String();
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        return result.toString();
    }
    
}
