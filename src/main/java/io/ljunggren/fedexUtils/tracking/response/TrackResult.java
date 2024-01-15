package io.ljunggren.fedexUtils.tracking.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.ljunggren.fedexUtils.tracking.TrackingNumberInfo;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class TrackResult {

    private TrackingNumberInfo trackingNumberInfo;
    private AdditionalTrackingInfo additionalTrackingInfo;
    private DistanceToDestination distanceToDestination;
    @JsonProperty("consolidationDetail")
    private List<ConsolidationDetail> consolidationDetails;
    private String meterNumber;
    private ReturnDetail returnDetail;
    private ServiceDetail serviceDetail;
    private Location destinationLocation;
    private LatestStatusDetail latestStatusDetail;
    private ServiceCommitMessage serviceCommitMessage;
    private List<InformationNote> informationNotes;
    private TrackingError error;
    private List<SpecialHandling> specialHandlings;
    private List<AvailableImage> availableImages;
    @JsonProperty("deliveryDetails")
    private DeliveryDetail deliveryDetail;
    private List<ScanEvent> scanEvents;
    private List<DateAndTime> dateAndTimes;
    @JsonProperty("packageDetails")
    private PackageDetail packageDetail;
    private String goodsClassificationCode;
    private Location holdAtLocation;
    private List<CustomDeliveryOption> customDeliveryOptions;
    private WindowDetail estimatedDeliveryTimeWindow;
    private List<PieceCount> pieceCounts;
    private Location originLocation;
    private LocationContactAndAddress recipientInformation;
    private WindowDetail standardTransitTimeWindow;
    @JsonProperty("shipmentDetails")
    private ShipmentDetail shipmentDetail;
    private Detail reasonDetail;
    private List<String> availableNotifications;
    private LocationContactAndAddress shipperInformation;
    private Address lastUpdatedDestinationAddress;
    
}
