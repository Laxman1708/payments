package com.chipay.payments.dto.crs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReceivableGroupDTO {

    @JsonProperty("id")
    private String id;

    @JsonProperty("isRequired")
    private boolean isRequired;

    @JsonProperty("acceptsOverpayment")
    private boolean acceptsOverpayment;

    @JsonProperty("acceptsUnderpayment")
    private boolean acceptsUnderpayment;

    @JsonProperty("receivables")
    private List<ReceivableDTO> receivables;
}
