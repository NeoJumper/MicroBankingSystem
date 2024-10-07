package com.kcc.banking.domain.account.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class SearchAccountOfModal {
    private String accId;
    private String productName;
    private String status;

    @Builder
    public SearchAccountOfModal(String accId, String productName, String status) {
        this.accId = accId;
        this.productName = productName;
        this.status = status;
    }
}
