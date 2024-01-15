package io.ljunggren.fedexUtils.factory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;

import io.ljunggren.fedexUtils.factory.TrackingFactory;
import io.ljunggren.fedexUtils.tracking.request.TrackingRequest;

public class TrackingFactoryTest {

    @Test
    public void instantiationTest() {
        assertNotNull(new TrackingFactory());
    }
    
    @Test
    public void basicRequestTest() throws JsonProcessingException {
        String[] tackingNumbers = new String[] {"449044304137821", "149331877648230", "020207021381215"};
        TrackingRequest request = TrackingFactory.basicRequest(tackingNumbers);
        assertTrue(request.isIncludeDetailedScans());
        assertEquals(3, request.getTrackingInfos().size());
    }

}
