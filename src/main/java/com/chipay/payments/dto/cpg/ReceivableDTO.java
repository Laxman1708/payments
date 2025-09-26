package com.chipay.payments.dto.cpg;

import com.chipay.payments.dto.crs.AdditionalInformationDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Data
@Getter
@Setter
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

