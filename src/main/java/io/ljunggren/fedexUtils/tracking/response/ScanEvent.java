package io.ljunggren.fedexUtils.tracking.response;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ScanEvent {

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss")
    private Date date;
    private String derivedStatus;
    private Location scanLocation;
    private String exceptionDescription;
    private String eventDescription;
    private String eventType;
    private String derivedStatusCode;
    private String exceptionCode;
    private DelayDetail delayDetail;
    
}
