package com.kcc.banking.domain.account.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
public class AccountClose {

    private String accountId;
    private BigDecimal amount;

    @Builder
    public AccountClose(String accountId, BigDecimal amount) {
        this.accountId = accountId;
        this.amount = amount;
    }

}
