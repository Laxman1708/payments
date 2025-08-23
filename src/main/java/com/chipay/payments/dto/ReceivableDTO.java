package com.chipay.payments.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReceivableDTO {

    @JsonProperty("capsCode")
    private String capsCode;

    @JsonProperty("additionalInformation")
    private Map<String, String> additionalInformation;

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
