package io.ljunggren.fedexUtils.tracking.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ShipmentDetail {

    private List<Content> contents;
    private Boolean beforePossessionStatus;
    @JsonProperty("weight")
    private List<Weight> weights;
    private String contentPieceCount;
    private List<SplitShipment> splitShipments;
    
}
