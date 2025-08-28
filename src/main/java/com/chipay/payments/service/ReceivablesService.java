package com.chipay.payments.service;

import com.chipay.payments.crs.config.CrsProperties;
import com.chipay.payments.crs.dto.CategoryLookupResultsDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
public class ReceivablesService {

    private final RestTemplate restTemplate;
    private final CrsProperties crsProperties;

    public CategoryLookupResultsDTO getReceivablesByTicketNumber(String ticketNumber) {
        String url = UriComponentsBuilder.fromHttpUrl(crsProperties.getBaseUrl())
                .path("/revenue-accounting/v1/receivables")
                .queryParam("vendorID", crsProperties.getVendorId())
                .queryParam("channelID", crsProperties.getChannelId())
                .queryParam("locationID", crsProperties.getLocationId())
                .queryParam("terminalID", crsProperties.getTerminalId())
                .queryParam("receivableCategory", "PARKING")
                .queryParam("lookupKey", "ticketNumber")
                .queryParam("lookupValue", ticketNumber)
                .toUriString();

        return restTemplate.getForObject(url, CategoryLookupResultsDTO.class);
    }
}
