package com.chipay.payments.service;

import com.chipay.payments.cpg.dto.HostedPaymentPageDTO;
import com.chipay.payments.cpg.dto.PaymentRequestDTO;
import com.chipay.payments.crs.config.CrsProperties;
import com.chipay.payments.crs.dto.CategoryLookupResultsDTO;
import com.chipay.payments.mapper.PaymentRequestMapper;
import com.chipay.payments.util.TransactionIdGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReceivablesService {

    private final RestTemplate restTemplate;
    private final CrsProperties crsProperties;

    /*public CategoryLookupResultsDTO getReceivablesByTicketNumber(String ticketNumber) {
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

        System.out.println("Request URL: " + url); // Debugging line to print the URL
        log.info("Fetching receivables for ticket number: {}", ticketNumber);
        log.info("Request URL for ticket details:", url);

        return restTemplate.getForObject(url, CategoryLookupResultsDTO.class);
    }*/

    public HostedPaymentPageDTO getPaymentResponse(String ticketNumber) {
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

        System.out.println("Request URL: " + url); // Debugging line to print the URL
        log.info("Fetching receivables for ticket number: {}", ticketNumber);
        log.info("Request URL for ticket details:", url);

        CategoryLookupResultsDTO categoryLookupResultsDTO = restTemplate.getForObject(url, CategoryLookupResultsDTO.class);
        log.info("CategoryLookupResultsDTO: {}", categoryLookupResultsDTO);

        PaymentRequestDTO paymentRequestDTO = PaymentRequestMapper.mapFromReceivablesResponse(categoryLookupResultsDTO);
        String clientTransactionId = TransactionIdGenerator.generateClientTransactionId();

        // To be implemented to save clientTransactionId in DB to be information about this txn
        paymentRequestDTO.setClientTransactionId(clientTransactionId);
        log.info("PaymentRequestDTO: {}", paymentRequestDTO);

        HostedPaymentPageDTO hostedPaymentPageDTO = createHostedPayment( paymentRequestDTO);
        return hostedPaymentPageDTO;
    }


    @Value("${payment.api.hostname}")
    private String hostname;

    @Value("${payment.api.transaction-endpoint}")
    private String transactionEndpoint;
    public HostedPaymentPageDTO createHostedPayment(PaymentRequestDTO request) {
        String url = hostname + transactionEndpoint;
        log.info("Calling Payment API at: {}", url);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<PaymentRequestDTO> entity = new HttpEntity<>(request, headers);

        ResponseEntity<HostedPaymentPageDTO> response =
                restTemplate.exchange(url, HttpMethod.POST, entity, HostedPaymentPageDTO.class);

        log.info("Payment API Response: {}", response.getBody());

        return response.getBody();
    }
}
