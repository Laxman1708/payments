package com.chipay.payments.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "crs")
public class CrsProperties {

    private String baseUrl;
    private String vendorId;
    private String channelId;
    private String locationId;
    private String terminalId;
}

