package io.ljunggren.fedexUtils.tracking.request;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.ljunggren.fedexUtils.tracking.TrackingNumberInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class TrackingInfo {

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date shipDateBegin;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date shipDateEnd;
    private TrackingNumberInfo trackingNumberInfo;
    
    public TrackingInfo(TrackingNumberInfo trackingNumberInfo) {
        this.trackingNumberInfo = trackingNumberInfo;
    }
    
}
