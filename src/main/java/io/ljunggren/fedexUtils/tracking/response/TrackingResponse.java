package io.ljunggren.fedexUtils.tracking.response;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class TrackingResponse {

    private String transactionId;
    private String customerTransactionId;
    private Output output;
    private List<TrackingError> errors;
    
    public TrackingResponse addError(TrackingError error) {
        if (errors == null) {
            errors = new ArrayList<>();
        }
        errors.add(error);
        return this;
    }
    
}
