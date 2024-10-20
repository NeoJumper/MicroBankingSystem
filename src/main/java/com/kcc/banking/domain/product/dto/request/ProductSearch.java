package com.kcc.banking.domain.product.dto.request;

import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
public class ProductSearch {

    private long branchId;
    private String accountType;
    private String searchOption;
    private String searchValue;

    @Builder
    public ProductSearch(long branchId, String accountType, String searchOption, String searchValue) {
        this.branchId = branchId;
        this.accountType = accountType;
        this.searchOption = searchOption;
        this.searchValue = searchValue;
    }
}
