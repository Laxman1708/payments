package com.chipay.payments.mapper;

import com.chipay.payments.dto.cpg.PaymentRequestDTO;
import com.chipay.payments.dto.cpg.ReceivableCategoryDTO;
import com.chipay.payments.dto.cpg.ReceivableDTO;
import com.chipay.payments.dto.crs.CategoryLookupResultsDTO;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PaymentRequestMapper {

    public static PaymentRequestDTO mapFromReceivablesResponse(CategoryLookupResultsDTO response) {
        PaymentRequestDTO request = new PaymentRequestDTO();

        // 🔹 Map receivableCategories from receivableGroups
        List<ReceivableCategoryDTO> receivableCategories = response.getReceivableGroups()
                .stream()
                .map(group -> {
                    ReceivableCategoryDTO categoryDTO = new ReceivableCategoryDTO();

                    // Lookup token (from response)
                    categoryDTO.setLookupToken(response.getPaymentToken());

                    // Receivable category name
                    categoryDTO.setReceivableCategory(response.getReceivableCategory());

                    // Map receivables inside each group
                    List<ReceivableDTO> receivables = group.getReceivables()
                            .stream()
                            .map(receivable -> {
                                ReceivableDTO dto = new ReceivableDTO();
                                dto.setReceivableId(receivable.getId());
                                dto.setReceivableType(receivable.getType());
                                dto.setAmountDue(receivable.getAmountDue());

                                // 🔹 Map extra fields
                                dto.setAmountPaid(new BigDecimal(20.00)); // ensure getter available
//                                dto.setTransactionType(receivable.getTransactionType());
//                                dto.setEmail(receivable.getEmail());

                                // Additional Information (copy full object)
                                dto.setAdditionalInformation(receivable.getAdditionalInformation());

                                return dto;
                            })
                            .collect(Collectors.toList());

                    categoryDTO.setReceivables(receivables);

                    return categoryDTO;
                })
                .collect(Collectors.toList());

        request.setReceivableCategories(receivableCategories);

        return request;
    }
}
