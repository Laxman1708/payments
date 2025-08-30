package com.chipay.payments.mapper;
import com.chipay.payments.cpg.dto.PaymentRequestDTO;
import com.chipay.payments.cpg.dto.ReceivableCategoryDTO;

import com.chipay.payments.cpg.dto.ReceivableDTO;
import com.chipay.payments.crs.dto.CategoryLookupResultsDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PaymentRequestMapper {

    public static PaymentRequestDTO mapFromReceivablesResponse(CategoryLookupResultsDTO response) {
        PaymentRequestDTO request = new PaymentRequestDTO();
        ReceivableCategoryDTO category = new ReceivableCategoryDTO();
        // map lookupToken
        category.setLookupToken(response.getPaymentToken());



        // map receivableCategories from receivableGroups
        System.out.println("Request sent is: "+response);
        System.out.println("receivableCategories sent is: "+response.getReceivableGroups());
        List<ReceivableCategoryDTO> receivableCategories = response.getReceivableGroups()
                .stream()
                .map(group -> {
                    ReceivableCategoryDTO categoryDTO = new ReceivableCategoryDTO();

                    // category name
                    categoryDTO.setReceivableCategory(response.getReceivableCategory());

                    // map receivables
                    List<ReceivableDTO> receivables = group.getReceivables()
                            .stream()
                            .map(receivable -> {
                                ReceivableDTO dto = new ReceivableDTO();
                                dto.setReceivableId(receivable.getId());
                                dto.setReceivableType(receivable.getType());
                                dto.setAmountDue(receivable.getAmountDue());
                                dto.setAdditionalInformation(receivable.getAdditionalInformation()); // copy as-is
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

