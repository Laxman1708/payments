package com.chipay.payments.dto.cpg;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentResponseDTO {

    @JsonProperty("transactionType")
    private String transactionType;

    @JsonProperty("clientTransactionId")
    private String clientTransactionId;

    @JsonProperty("returnUrl")
    private String returnUrl;

    @JsonProperty("cancelUrl")
    private String cancelUrl;

    @JsonProperty("paymentTokenPurpose")
    private String paymentTokenPurpose;

    @JsonProperty("email")
    private String email;

    @JsonProperty("logoImageUrl")
    private String logoImageUrl;

    @JsonProperty("applicationName")
    private String applicationName;

    @JsonProperty("tagline")
    private String tagline;

    @JsonProperty("receivableCategories")
    private Object receivableCategories;
    // If you have ReceivableCategoryDTO defined already, replace Object with List<ReceivableCategoryDTO>

    @JsonProperty("location")
    private Object location;
    // If you have LocationDTO defined, replace Object with LocationDTO
}

