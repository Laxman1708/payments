package com.chipay.payments.cpg.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class HostedPaymentPageDTO {

    @JsonProperty("sessionId")
    private String sessionId;

    @JsonProperty("hostedPaymentPageRequestResult")
    private boolean hostedPaymentPageRequestResult;

    @JsonProperty("sessionStartDateTime")
    private String sessionStartDateTime;
    // 👉 Can be OffsetDateTime for strict parsing

    @JsonProperty("clientTransactionId")
    private String clientTransactionId;

    @JsonProperty("sessionExpiryDateTime")
    private String sessionExpiryDateTime;
    // 👉 Can be OffsetDateTime too

    @JsonProperty("locationId")
    private String locationId;

    @JsonProperty("returnUrl")
    private String returnUrl;

    @JsonProperty("cancelUrl")
    private String cancelUrl;

    @JsonProperty("hostedPaymentPageUrl")
    private String hostedPaymentPageUrl;
}

