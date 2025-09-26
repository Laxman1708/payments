package com.chipay.payments.service;

import com.chipay.payments.dto.cpg.HostedPaymentPageDTO;
import com.chipay.payments.dto.cpg.PaymentRequestDTO;
import com.chipay.payments.config.CpgProperties;
import com.chipay.payments.config.CrsProperties;
import com.chipay.payments.dto.crs.CategoryLookupResultsDTO;
import com.chipay.payments.dto.crs.LocationDTO;
import com.chipay.payments.util.DecryptPayload;
import com.chipay.payments.util.TransactionIdGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReceivablesService2 {

    private final RestTemplate restTemplate;
    private final CrsProperties crsProperties;
    private final CpgProperties cpgProperties;

    @Value("${payment.api.hostname}")
    private String hostname;

    @Value("${payment.api.transaction-endpoint}")
    private String transactionEndpoint;

    @Value("${payment.api.username}")
    private String apiUsername;

    @Value("${payment.api.password}")
    private String apiPassword;

    /**
     * Fetch Payment Response based on ticketNumber
     */
    public CategoryLookupResultsDTO getPaymentResponse(String ticketNumber) {
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

        log.info("Fetching receivables for ticket number: {}", ticketNumber);
        log.info("Request URL for ticket details: {}", url);

        // Add Basic Auth headers for Receivables call
        HttpHeaders headers = createBasicAuthHeaders();
        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<CategoryLookupResultsDTO> receivablesResponse =
                restTemplate.exchange(url, HttpMethod.GET, entity, CategoryLookupResultsDTO.class);

        CategoryLookupResultsDTO categoryLookupResultsDTO = receivablesResponse.getBody();
        log.info("CategoryLookupResultsDTO: {}", categoryLookupResultsDTO);

//        PaymentRequestDTO paymentRequestDTO = PaymentRequestMapper.mapFromReceivablesResponse(categoryLookupResultsDTO);
//        String clientTransactionId = TransactionIdGenerator.generateClientTransactionId();
//
//        // Save clientTransactionId in DB (to be implemented)
//        paymentRequestDTO.setClientTransactionId(clientTransactionId);
//        log.info("PaymentRequestDTO: {}", paymentRequestDTO);

        return categoryLookupResultsDTO;
//        return createHostedPayment(paymentRequestDTO);
    }

    /**
     * Create Hosted Payment Page
     */
    public HostedPaymentPageDTO submitPayment(PaymentRequestDTO request) {
        String url = hostname + transactionEndpoint;
        log.info("Calling Payment API at: {}", url);

        // add url and location details

        String clientTransactionId = TransactionIdGenerator.generateClientTransactionId();

        // Save clientTransactionId in DB (to be implemented)
        request.setClientTransactionId(clientTransactionId);
        request.setReturnUrl(cpgProperties.getReturnUrl());
        request.setCancelUrl(cpgProperties.getCancelUrl());
        request.setApplicationName(cpgProperties.getApplicationName());
//        request.setEmail("rakeshreddy.dontireddy@cai.io");
        LocationDTO locationDTO = new LocationDTO(cpgProperties.getLocationId(),
                cpgProperties.getChannel(), cpgProperties.getTerminalId(),
                cpgProperties.getVendorId());
        request.setLocation(locationDTO);
        request.setPaymentTokenPurpose(cpgProperties.getPaymentTokenPurpose());

        // add url and location details

        HttpHeaders headers = createBasicAuthHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        ObjectMapper mapper = new ObjectMapper();
        String jsonBody = null;
        try {
            jsonBody = mapper.writeValueAsString(request);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        log.info("Request Payload: {}", jsonBody);

        HttpEntity<PaymentRequestDTO> entity = new HttpEntity<>(request, headers);

//        ResponseEntity<HostedPaymentPageDTO> response =
//                restTemplate.exchange(url, HttpMethod.POST, entity, HostedPaymentPageDTO.class);

        ResponseEntity<String> response =
                restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

        String encryptedBody = response.getBody();
        log.info("Encrypted API Response: {}", encryptedBody);

        String decryptedJson = DecryptPayload.decrypt(encryptedBody, cpgProperties.getEncryptionKey());
        log.info("Decrypted API Response: {}", decryptedJson);

        HostedPaymentPageDTO hostedPaymentPageDTO = null;
        try {
            hostedPaymentPageDTO = new ObjectMapper().readValue(decryptedJson, HostedPaymentPageDTO.class);
        } catch (Exception e) {
            log.error("Failed to parse decrypted JSON into HostedPaymentPageDTO", e);
            throw new RuntimeException("Decryption/Parsing error", e);
        }
        log.info("Payment API Response: {}", response.getBody());

        return hostedPaymentPageDTO;
    }

    /**
     * Utility to generate Basic Auth headers
     */
    private HttpHeaders createBasicAuthHeaders() {
        String auth = apiUsername + ":" + apiPassword;
        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes(StandardCharsets.UTF_8));

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, "Basic " + encodedAuth);
        return headers;
    }
    public String decrypt(String encryptedText) {
        return DecryptPayload.decrypt(encryptedText, cpgProperties.getEncryptionKey());
    }
}
