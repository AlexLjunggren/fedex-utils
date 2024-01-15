package io.ljunggren.fedexUtils.tracking.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrackingRequest {

    private boolean includeDetailedScans;
    @JsonProperty("trackingInfo")
    private List<TrackingInfo> trackingInfos;
    
}
