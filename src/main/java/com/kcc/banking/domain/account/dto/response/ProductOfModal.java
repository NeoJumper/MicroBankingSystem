package com.kcc.banking.domain.account.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductOfModal {

    private String productId;
    private String productName;
    private String productPeriod;
    private String productInterestRate;
    private String productTaxRate;
    private String branchName;
}
