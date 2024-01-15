package io.ljunggren.fedexUtils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import io.ljunggren.fedexUtils.authorization.OauthResponse;
import io.ljunggren.fedexUtils.factory.TrackingFactory;
import io.ljunggren.fedexUtils.tracking.request.TrackingRequest;
import io.ljunggren.fedexUtils.tracking.response.TrackingResponse;
import io.ljunggren.fedexUtils.utility.FileUtils;

@RunWith(MockitoJUnitRunner.class)
public class FedexApiTest {

    @Mock
    private CloseableHttpClient httpClient;
    
    @Mock
    private CloseableHttpResponse httpResponse;
    
    @Mock
    private HttpEntity httpEntity;
    
    @Mock
    private StatusLine statusLine;
    
    private void setup(String json, int responseCode) throws ClientProtocolException, IOException {
        InputStream content = new ByteArrayInputStream(json.getBytes());
        when(httpClient.execute(any(HttpPost.class))).thenReturn(httpResponse);
        when(httpResponse.getStatusLine()).thenReturn(statusLine);
        when(statusLine.getStatusCode()).thenReturn(responseCode);
        when(httpResponse.getEntity()).thenReturn(httpEntity);
        when(httpEntity.getContent()).thenReturn(content);
    }
    
    @Test
    public void authorizeTest() throws IOException {
        String json = FileUtils.readResourceFileWithAppendedSpace(this.getClass(), "io/ljunggren/fedexUtils/authorization/oauthResponse.json");
        setup(json, 200);
        FedexEnvironment environment = FedexEnvironment.SANDBOX;
        FedexApi fedexApi = new FedexApi(environment, "clientId", "clientSecret");
        OauthResponse oauthResponse = fedexApi.authorize(httpClient);
        String expected = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJzY29wZSI6WyJDWFMiLCJTRUNVUkUiX";
        assertEquals(expected, oauthResponse.getAccessToken());
    }
    
    @Test
    public void authorizeUnknownResponseCodeTest() throws IOException {
        String json = "notRealResponse";
        setup(json, 407);
        FedexEnvironment environment = FedexEnvironment.SANDBOX;
        FedexApi fedexApi = new FedexApi(environment, "clientId", "clientSecret");
        OauthResponse oauthResponse = fedexApi.authorize(httpClient);
        assertEquals(1, oauthResponse.getErrors().size());
        assertEquals("Unknown response code 407", oauthResponse.getErrors().get(0).getMessage());
    }
    
    @Test
    public void authorizeExceptionThrownTest() throws ClientProtocolException, IOException {
        String json = "notRealResponse";
        setup(json, 200);
        FedexEnvironment environment = FedexEnvironment.SANDBOX;
        FedexApi fedexApi = new FedexApi(environment, "clientId", "clientSecret");
        OauthResponse oauthResponse = fedexApi.authorize(httpClient);
        assertEquals(1, oauthResponse.getErrors().size());
        assertEquals("Unable to parse response", oauthResponse.getErrors().get(0).getMessage());
    }
    
    @Test
    public void trackTest() throws ClientProtocolException, IOException {
        TrackingRequest trackingRequest = TrackingFactory.basicRequest("12345");
        String json = FileUtils.readResourceFileWithAppendedSpace(this.getClass(), "io/ljunggren/fedexUtils/tracking/response/trackingResponse.json");
        setup(json, 200);
        FedexEnvironment environment = FedexEnvironment.SANDBOX;
        FedexApi fedexApi = new FedexApi(environment, "clientId", "clientSecret");
        TrackingResponse trackingResponse = fedexApi.track(trackingRequest, "token", httpClient);
        assertNull(trackingResponse.getErrors());
    }
    
    @Test
    public void trackUnknownResponseCodeTest() throws IOException {
        TrackingRequest trackingRequest = TrackingFactory.basicRequest("12345");
        String json = "notRealResponse";
        setup(json, 407);
        FedexEnvironment environment = FedexEnvironment.SANDBOX;
        FedexApi fedexApi = new FedexApi(environment, "clientId", "clientSecret");
        TrackingResponse trackingResponse = fedexApi.track(trackingRequest, "token", httpClient);
        assertEquals(1, trackingResponse.getErrors().size());
        assertEquals("Unknown response code 407", trackingResponse.getErrors().get(0).getMessage());
    }
    
    @Test
    public void trackThrottlingResponseCodeTest() throws IOException {
        TrackingRequest trackingRequest = TrackingFactory.basicRequest("12345");
        String json = "notRealResponse";
        setup(json, 429);
        FedexEnvironment environment = FedexEnvironment.SANDBOX;
        FedexApi fedexApi = new FedexApi(environment, "clientId", "clientSecret");
        TrackingResponse trackingResponse = fedexApi.track(trackingRequest, "token", httpClient);
        assertEquals(1, trackingResponse.getErrors().size());
        assertEquals("Too many requests", trackingResponse.getErrors().get(0).getMessage());
    }
    
    @Test
    public void trackExceptionThrownTest() throws IOException {
        TrackingRequest trackingRequest = TrackingFactory.basicRequest("12345");
        String json = "notRealResponse";
        setup(json, 200);
        FedexEnvironment environment = FedexEnvironment.SANDBOX;
        FedexApi fedexApi = new FedexApi(environment, "clientId", "clientSecret");
        TrackingResponse trackingResponse = fedexApi.track(trackingRequest, "token", httpClient);
        assertEquals(1, trackingResponse.getErrors().size());
        assertEquals("Unable to parse response", trackingResponse.getErrors().get(0).getMessage());
    }

}
