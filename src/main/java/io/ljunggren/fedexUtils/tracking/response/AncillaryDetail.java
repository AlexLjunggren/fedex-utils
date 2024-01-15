package io.ljunggren.fedexUtils.tracking.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AncillaryDetail {

    private String reason;
    private String reasonDescription;
    private String action;
    private String actionDescription;
    
}
