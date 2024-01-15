package io.ljunggren.fedexUtils.authorization;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

import io.ljunggren.fedexUtils.authorization.OauthResponse;
import io.ljunggren.fedexUtils.utility.FileUtils;
import io.ljunggren.jsonUtils.JsonUtils;

public class OauthResponseTest {

    @Test
    public void deserializeTest() throws IOException {
        String json = FileUtils.readResourceFileWithAppendedSpace(this.getClass(), "io/ljunggren/fedexUtils/authorization/oauthResponse.json");
        OauthResponse response = JsonUtils.jsonToObject(json, OauthResponse.class);
        assertNotNull(response);
    }
    
    @Test
    public void serializeTest() throws IOException {
        String json = FileUtils.readResourceFileWithAppendedSpace(this.getClass(), "io/ljunggren/fedexUtils/authorization/oauthResponse.json");
        OauthResponse response = JsonUtils.jsonToObject(json, OauthResponse.class);
        String serializeResponse = JsonUtils.objectToJson(response);
        assertTrue(JsonUtils.areEqual(json, serializeResponse));
    }

    @Test
    public void deserializeWithErrorTest() throws IOException {
        String json = FileUtils.readResourceFileWithAppendedSpace(this.getClass(), "io/ljunggren/fedexUtils/authorization/oauthErrorResponse.json");
        OauthResponse response = JsonUtils.jsonToObject(json, OauthResponse.class);
        assertNotNull(response);
    }
    
    @Test
    public void serializeWithErrorTest() throws IOException {
        String json = FileUtils.readResourceFileWithAppendedSpace(this.getClass(), "io/ljunggren/fedexUtils/authorization/oauthErrorResponse.json");
        OauthResponse response = JsonUtils.jsonToObject(json, OauthResponse.class);
        String serializeResponse = JsonUtils.objectToJson(response);
        assertTrue(JsonUtils.areEqual(json, serializeResponse));
    }

}
