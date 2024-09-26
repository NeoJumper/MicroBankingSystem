package com.kcc.banking.domain.customer.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CustomerSearchDTO {
    private String customerId;
    private String customerName;
    private String customerPhone;

    @Builder
    public CustomerSearchDTO(String customerName, String customerId, String customerPhone) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
    }
}
