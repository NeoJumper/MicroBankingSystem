package com.kcc.banking.domain.account.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class SearchProductOfModal {

    private String period;
    private String productName;

    private String sortOrder;
    private String branchId;

    @Builder
    public SearchProductOfModal(String period, String sortOrder, String productName, String branchId) {
        this.period = period;
        this.sortOrder = sortOrder;
        this.productName = productName;
        this.branchId = branchId;
    }
}
