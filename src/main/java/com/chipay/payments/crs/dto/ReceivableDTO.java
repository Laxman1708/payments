package com.chipay.payments.crs.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReceivableDTO {

    @JsonProperty("capsCode")
    private String capsCode;

    @JsonProperty("additionalInformation")
    private AdditionalInformationDTO additionalInformation;

    @JsonProperty("id")
    private String id;

    @JsonProperty("type")
    private String type;

    @JsonProperty("description")
    private String description;

    @JsonProperty("amountDue")
    private double amountDue;

    @JsonProperty("allocationOrder")
    private String allocationOrder;
}