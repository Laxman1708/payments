package com.chipay.payments.controller;

import com.chipay.payments.crs.dto.PaymentRequest;
import com.chipay.payments.crs.dto.RootDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/payments")
@Validated
public class PaymentController {

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
}