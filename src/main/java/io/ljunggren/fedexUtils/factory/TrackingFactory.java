package io.ljunggren.fedexUtils.factory;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import io.ljunggren.fedexUtils.tracking.TrackingNumberInfo;
import io.ljunggren.fedexUtils.tracking.request.TrackingInfo;
import io.ljunggren.fedexUtils.tracking.request.TrackingRequest;

public class TrackingFactory {

    public static TrackingRequest basicRequest(String... trackingNumbers) {
        List<TrackingInfo> trackingInfos = Stream.of(trackingNumbers)
                .map(trackingNumber -> new TrackingInfo(
                        new TrackingNumberInfo(trackingNumber)))
                .collect(Collectors.toList());
        return new TrackingRequest(true, trackingInfos);
    }
    
}
