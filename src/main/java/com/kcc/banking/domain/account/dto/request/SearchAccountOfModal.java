package com.kcc.banking.domain.account.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Bean;

@NoArgsConstructor
@Getter
@Setter
public class SearchAccountOfModal {
    String accountNumber;
    String productName;

    @Builder
    public SearchAccountOfModal(String productName, String accountNumber) {
        this.productName = productName;
        this.accountNumber = accountNumber;
    }
}
