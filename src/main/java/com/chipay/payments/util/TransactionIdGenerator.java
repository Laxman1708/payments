package com.chipay.payments.util;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class TransactionIdGenerator {

    private static final String PREFIX = "AVP";
    private static final AtomicInteger SEQUENCE = new AtomicInteger(1);

    public static String generateClientTransactionId() {
        String datePart = new SimpleDateFormat("yyyyMMdd").format(new Date());
        int sequence = SEQUENCE.getAndIncrement();

        // Reset sequence if it gets too large (e.g., after 9999)
        if (sequence > 9999) {
            SEQUENCE.set(1);
            sequence = 1;
        }

        // Format: PREFIX + yyyyMMdd + sequence (padded to 4 digits)
        return String.format("%s%s%04d", PREFIX, datePart, sequence);
    }
}
