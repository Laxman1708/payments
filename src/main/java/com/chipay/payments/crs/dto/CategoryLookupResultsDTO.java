package com.chipay.payments.crs.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CategoryLookupResultsDTO {

    @JsonProperty("channelID")
    private String channelID;

    @JsonProperty("vendorID")
    private String vendorID;

    @JsonProperty("terminalID")
    private String terminalID;

    @JsonProperty("locationID")
    private String locationID;

    @JsonProperty("contactInformation")
    private ContactInformationDTO contactInformation;

    @JsonProperty("receivableGroups")
    private List<ReceivableGroupDTO> receivableGroups;

    @JsonProperty("receivableCategory")
    private String receivableCategory;

    @JsonProperty("categoryDescription")
    private String categoryDescription;

    @JsonProperty("lookupKey")
    private String lookupKey;

    @JsonProperty("lookupValue")
    private String lookupValue;

    @JsonProperty("paymentToken")
    private String paymentToken;
}
