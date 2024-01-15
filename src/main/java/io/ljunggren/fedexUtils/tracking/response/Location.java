package io.ljunggren.fedexUtils.tracking.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Location {

    private String locationId;
    private LocationContactAndAddress locationContactAndAddress;
    private String locationType;
    
}
