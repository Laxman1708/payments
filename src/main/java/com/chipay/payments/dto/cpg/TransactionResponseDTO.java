package com.chipay.payments.dto.cpg;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionResponseDTO {

    @JsonProperty("transactionId")
    private String transactionId;

    @JsonProperty("transactionDateTime")
    private String transactionDateTime;

    @JsonProperty("transactionType")
    private String transactionType;

    @JsonProperty("clientTransactionId")
    private String clientTransactionId;

    @JsonProperty("location")
    private LocationDTO location;

    @JsonProperty("receivableCategories")
    private List<ReceivableCategoryDTO> receivableCategories;

    @JsonProperty("payment")
    private PaymentDTO payment;

    @JsonProperty("sourceHostedPayments")
    private Boolean sourceHostedPayments;
}
