package io.ljunggren.fedexUtils.tracking.response;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.junit.Test;

import io.ljunggren.fedexUtils.utility.FileUtils;
import io.ljunggren.jsonUtils.JsonUtils;

public class TrackingResponseTest {
    
    @Test
    public void parseTest() throws IOException {
        String json = FileUtils.readResourceFileWithAppendedSpace(this.getClass(), "io/ljunggren/fedexUtils/tracking/response/trackingResponse.json");
        TrackingResponse trackingResponse = JsonUtils.jsonToObject(json, TrackingResponse.class);
        assertNotNull(trackingResponse);
    }

}
