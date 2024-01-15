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
public class DeliveryDetail {

    private String receivedByName;
    private String destinationServiceArea;
    private String destinationServiceAreaDescription;
    private String locationDescription;
    private Address actualDeliveryAddress;
    private Boolean deliveryToday;
    private String locationType;
    private String signedByName;
    private String officeOrderDeliveryMethod;
    private String deliveryAttempts;
    private List<DeliveryOptionEligibilityDetail> deliveryOptionEligibilityDetails;
    
}
