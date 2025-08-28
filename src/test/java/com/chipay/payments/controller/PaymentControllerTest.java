package com.chipay.payments.controller;

import com.chipay.payments.crs.dto.PaymentRequest;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PaymentControllerTest {

    @Test
    void testProcessPayment() {
        com.chipay.payments.controller.PaymentController controller = new com.chipay.payments.controller.PaymentController();
        PaymentRequest request = new PaymentRequest();
        request.setAmount(100.0);
        request.setCurrency("USD");

        ResponseEntity<Map<String, Object>> responseEntity = controller.processPayment(request);
        Map<String, Object> response = responseEntity.getBody();

        assertNotNull(response);
        assertEquals(100.0, response.get("amount"));
        assertEquals("USD", response.get("currency"));
        assertEquals("Payment received", response.get("message"));
    }
}