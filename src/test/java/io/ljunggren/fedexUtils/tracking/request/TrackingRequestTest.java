package io.ljunggren.fedexUtils.tracking.request;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

import io.ljunggren.fedexUtils.utility.FileUtils;
import io.ljunggren.jsonUtils.JsonUtils;

public class TrackingRequestTest {

    @Test
    public void deserializeTest() throws IOException {
        String json = FileUtils.readResourceFileWithAppendedSpace(this.getClass(), "io/ljunggren/fedexUtils/tracking/request/trackingRequest.json");
        TrackingRequest request = JsonUtils.jsonToObject(json, TrackingRequest.class);
        assertNotNull(request);
    }

    @Test
    public void serializeTest() throws IOException {
        String json = FileUtils.readResourceFileWithAppendedSpace(this.getClass(), "io/ljunggren/fedexUtils/tracking/request/trackingRequest.json");
        TrackingRequest request = JsonUtils.jsonToObject(json, TrackingRequest.class);
        String serializedRequest = JsonUtils.objectToJson(request);
        assertTrue(JsonUtils.areEqual(json, serializedRequest));
    }

}
