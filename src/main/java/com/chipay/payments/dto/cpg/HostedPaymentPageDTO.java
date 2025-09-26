package com.chipay.payments.dto.cpg;

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

    @JsonProperty("clientTransactionId")
    private String clientTransactionId;

    @JsonProperty("sessionExpiryDateTime")
    private String sessionExpiryDateTime;

    @JsonProperty("locationId")
    private String locationId;

    @JsonProperty("returnUrl")
    private String returnUrl;

    @JsonProperty("cancelUrl")
    private String cancelUrl;

    @JsonProperty("hostedPaymentPageUrl")
    private String hostedPaymentPageUrl;
}


