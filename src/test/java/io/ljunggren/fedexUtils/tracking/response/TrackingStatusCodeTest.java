package io.ljunggren.fedexUtils.tracking.response;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import io.ljunggren.fedexUtils.tracking.response.TrackingStatusCode;

public class TrackingStatusCodeTest {

    @Test
    public void getDescriptionTest() {
        String description = TrackingStatusCode.AA.getDescription();
        assertEquals("At Airport", description);
    }
    
    @Test
    public void lookupTest() {
        TrackingStatusCode scanEventType = TrackingStatusCode.lookup("AA");
        assertEquals(TrackingStatusCode.AA, scanEventType);
    }
    
    @Test
    public void lookupUnknownTest() {
        TrackingStatusCode scanEventType = TrackingStatusCode.lookup("ZZ");
        assertEquals(TrackingStatusCode.UNKNOWN, scanEventType);
        
    }

}
