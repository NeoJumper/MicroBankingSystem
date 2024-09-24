package com.kcc.banking.domain.customer.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CustomerSearchDTO {
    private String customerName;
    private String customerNumber;
    private String customerPhone;

    @Builder
    public CustomerSearchDTO(String customerName, String customerNumber, String customerPhone) {
        this.customerName = customerName;
        this.customerNumber = customerNumber;
        this.customerPhone = customerPhone;
    }
}
