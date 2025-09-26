package com.chipay.payments.dto;

import lombok.Data;

@Data
public class ScanResultDTO {
    private boolean clean;
    private String rawResult; // raw ClamAV response
}
