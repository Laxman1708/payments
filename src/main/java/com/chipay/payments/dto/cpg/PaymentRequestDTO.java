package com.chipay.payments.dto.cpg;

import com.chipay.payments.dto.crs.LocationDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentRequestDTO {

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
    private List<ReceivableCategoryDTO> receivableCategories;

    @JsonProperty("location")
    private LocationDTO location;
}


