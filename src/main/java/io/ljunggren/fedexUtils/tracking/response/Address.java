package io.ljunggren.fedexUtils.tracking.response;

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
public class Address {

    private String classification;
    private Boolean residential;
    private List<String> streetLines;
    private String city;
    private String urbanizationCode;
    private String stateOrProvinceCode;
    private String postalCode;
    private String countryCode;
    @JsonInclude(Include.NON_NULL)
    private String countryName;
    
}
