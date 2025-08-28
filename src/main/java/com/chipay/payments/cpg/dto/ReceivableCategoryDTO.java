package com.chipay.payments.cpg.dto;

import com.chipay.payments.crs.dto.ReceivableDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReceivableCategoryDTO {

    @JsonProperty("receivableCategory")
    private String receivableCategory;

    @JsonProperty("lookupToken")
    private String lookupToken;

    @JsonProperty("receivables")
    private List<ReceivableDTO> receivables;
}

