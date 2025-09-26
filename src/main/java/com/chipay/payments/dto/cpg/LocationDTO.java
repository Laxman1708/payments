package com.chipay.payments.dto.cpg;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LocationDTO {

    @JsonProperty("locationId")
    private String locationId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("channel")
    private String channel;

    @JsonProperty("terminalId")
    private String terminalId;

    @JsonProperty("vendorId")
    private String vendorId;

    @JsonProperty("applicationId")
    private String applicationId;
}
