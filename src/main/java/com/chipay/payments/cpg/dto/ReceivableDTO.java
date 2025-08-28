package com.chipay.payments.cpg.dto;

import com.chipay.payments.crs.dto.AdditionalInformationDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReceivableDTO {

    @JsonProperty("receivableType")
    private String receivableType;

    @JsonProperty("receivableId")
    private String receivableId;

    @JsonProperty("amountDue")
    private BigDecimal amountDue;

    @JsonProperty("additionalInformation")
    private AdditionalInformationDTO additionalInformation;

    @JsonProperty("amountPaid")
    private BigDecimal amountPaid;
}

