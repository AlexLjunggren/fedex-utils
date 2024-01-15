package io.ljunggren.fedexUtils.tracking.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdditionalTrackingInfo {

    private Boolean hasAssociatedShipments;
    private String nickname;
    private List<PackageIdentifier> packageIdentifiers;
    private String shipmentNotes;
    
}
