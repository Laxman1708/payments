package com.chipay.payments.dto.crs;

import lombok.Data;

@Data
public class PaymentRequest {

    private Double amount;
    private String currency;
    private String message;
}
