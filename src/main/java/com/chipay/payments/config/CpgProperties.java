package com.chipay.payments.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "cpg")
public class CpgProperties {

    private String returnUrl;
    private String cancelUrl;
    private String paymentTokenPurpose;
    private String locationId;
    private String channel;
    private String terminalId;
    private String vendorId;
    private String applicationName;
    private String encryptionKey;
}

