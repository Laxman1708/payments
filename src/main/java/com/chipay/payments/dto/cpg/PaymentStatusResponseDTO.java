package com.chipay.payments.dto.cpg;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PaymentStatusResponseDTO {

    @JsonProperty("status")
    private String status;

    @JsonProperty("clientTransactionId")
    private String clientTransactionId;

    @JsonProperty("transactionId")
    private String transactionId;

    @JsonProperty("PaymentMethod")
    private String paymentMethod;

    @JsonProperty("firstSRoutingNumber")
    private String firstSRoutingNumber;

    @JsonProperty("last5AccountNumber")
    private String last5AccountNumber;

    @JsonProperty("totalAmountPaid")
    private Double totalAmountPaid;

    @JsonProperty("receivableAmount")
    private Double receivableAmount;

    @JsonProperty("approvalId")
    private String approvalId;

    @JsonProperty("customerName")
    private String customerName;

    @JsonProperty("email")
    private String email;
}
