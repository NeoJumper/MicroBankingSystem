package com.kcc.banking.domain.product.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ProductDetail {

    private String id;
    private String name;
    private String interestRate;
    private String taxRate;

    @Builder
    public ProductDetail(String id, String name, String interestRate, String taxRate) {
        this.id = id;
        this.name = name;
        this.interestRate = interestRate;
        this.taxRate = taxRate;
    }
}
