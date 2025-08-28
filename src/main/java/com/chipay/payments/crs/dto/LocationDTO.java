package com.chipay.payments.crs.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LocationDTO {

    @JsonProperty("locationId")
    private String locationId;

    @JsonProperty("channel")
    private String channel;

    @JsonProperty("terminalId")
    private String terminalId;

    @JsonProperty("vendorId")
    private String vendorId;
}

