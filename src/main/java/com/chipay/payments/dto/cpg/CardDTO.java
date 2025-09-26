package com.chipay.payments.dto.cpg;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CardDTO {

    @JsonProperty("totalAmountPaid")
    private Double totalAmountPaid;

    @JsonProperty("receivableAmountPaid")
    private Double receivableAmountPaid;

    @JsonProperty("serviceFeeAmountPaid")
    private Double serviceFeeAmountPaid;

    @JsonProperty("cardType")
    private String cardType;

    @JsonProperty("maskedCardNumber")
    private String maskedCardNumber;

    @JsonProperty("expiryDate")
    private String expiryDate;

    @JsonProperty("cardHolderName")
    private String cardHolderName;

    @JsonProperty("approvalID")
    private String approvalID;

    @JsonProperty("serviceFeeApprovalID")
    private String serviceFeeApprovalID;

    @JsonProperty("tokenize")
    private Boolean tokenize;

    @JsonProperty("paymentMethod")
    private String paymentMethod;
}

