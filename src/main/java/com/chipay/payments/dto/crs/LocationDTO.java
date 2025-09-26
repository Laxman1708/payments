package com.chipay.payments.dto.crs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
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

