package com.kcc.banking.domain.account.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class SearchAccountOfModal {
    String accId;
    String productName;

    @Builder
    public SearchAccountOfModal(String accId, String productName) {
        this.accId = accId;
        this.productName = productName;
    }
}
