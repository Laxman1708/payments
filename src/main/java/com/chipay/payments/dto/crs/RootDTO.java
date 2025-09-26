package com.chipay.payments.dto.crs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RootDTO {
    @JsonProperty("categoryLookupResults")
    private CategoryLookupResultsDTO categoryLookupResults;
}

