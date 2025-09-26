package com.chipay.payments.controller;

import com.chipay.payments.dto.cpg.HostedPaymentPageDTO;
import com.chipay.payments.dto.cpg.PaymentRequestDTO;
import com.chipay.payments.dto.cpg.PaymentStatusResponseDTO;
import com.chipay.payments.dto.crs.CategoryLookupResultsDTO;
import com.chipay.payments.dto.crs.PaymentRequest;
import com.chipay.payments.dto.crs.RootDTO;
import com.chipay.payments.mapper.PaymentRequestMapper;
import com.chipay.payments.service.ReceivablesService2;
import com.chipay.payments.util.JsonUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/payments")
@Validated
@Slf4j
@RequiredArgsConstructor
public class PaymentController {

    private final ReceivablesService2 receivablesService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> processPayment(@Valid @RequestBody PaymentRequest paymentRequest) {
        Map<String, Object> response = new HashMap<>();
        response.put("amount", paymentRequest.getAmount());
        response.put("currency", paymentRequest.getCurrency());
        response.put("message", "Payment received");
        return ResponseEntity.ok(response);
    }

    //write a controller to accept json of type CategoryLookupResults and print the request object. also write a junit to test proper json format

        @PostMapping("/category-lookup")
        public ResponseEntity<RootDTO> categoryLookup(@RequestBody RootDTO rootDTO) {
        System.out.println(rootDTO);

        System.out.println("categoty desc is: "+rootDTO.getCategoryLookupResults().getCategoryDescription());
        System.out.println("categoty desc for first  one: "+rootDTO.getCategoryLookupResults().getReceivableGroups().get(0).getReceivables().get(0).getAmountDue());
        System.out.println("additional info Violation Site value is: "+rootDTO.getCategoryLookupResults().getReceivableGroups().get(0).getReceivables().get(0).getAdditionalInformation().getViolationSite());
        return ResponseEntity.ok(rootDTO);
    }

    @GetMapping("/{ticketNumber}")
    public ResponseEntity<CategoryLookupResultsDTO> getPaymentResponse(@PathVariable String ticketNumber) {

        CategoryLookupResultsDTO response = receivablesService.getPaymentResponse(ticketNumber);
        log.info("Receivables response for ticket {}: {}", ticketNumber, response);

        if (response != null) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(("/submitPayment"))
    public ResponseEntity<HostedPaymentPageDTO> submitPayment(@Valid @RequestBody RootDTO request) {
//        PaymentRequestDTO paymentRequestDTO = PaymentRequestMapper.mapFromReceivablesResponse(paymentRequest.getCategoryLookupResults());
        PaymentRequestDTO paymentRequest = PaymentRequestMapper.mapFromReceivablesResponse(request.getCategoryLookupResults());
        HostedPaymentPageDTO paymentRequestDTO = receivablesService.submitPayment(paymentRequest);
        return ResponseEntity.ok(paymentRequestDTO);
    }

/*    @PostMapping(("/testCPG"))
    public ResponseEntity<PaymentRequestDTO> testCPG(@Valid @RequestBody RootDTO paymentRequest) {
        PaymentRequestDTO paymentRequestDTO = PaymentRequestMapper.mapFromReceivablesResponse(paymentRequest.getCategoryLookupResults());
        return ResponseEntity.ok(paymentRequestDTO);
    }*/


 /*   @GetMapping("/transactions")
    public ResponseEntity<List<TransactionResponseDTO>> getTransactions(
            @RequestParam String clientTransactionId,
            @RequestParam String transactionType) {

        log.info("Fetching transactions for clientTransactionId={} transactionType={}", clientTransactionId, transactionType);

//        List<TransactionResponseDTO> response = receivablesService.getTransactions(clientTransactionId, transactionType);

        log.info("Transactions response: {}", response);

        return ResponseEntity.ok(response);
    }*/

    @GetMapping("/decrypt")
    public ResponseEntity<PaymentStatusResponseDTO> decryptContent(@RequestParam String encryptedText) {
        String decryptedJson = receivablesService.decrypt(encryptedText);

        PaymentStatusResponseDTO paymentStatusResponseDTO = JsonUtils.convertStringToObject(decryptedJson, PaymentStatusResponseDTO.class);
        return ResponseEntity.ok(paymentStatusResponseDTO);
    }
}