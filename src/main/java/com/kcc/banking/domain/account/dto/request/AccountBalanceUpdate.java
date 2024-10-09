package com.kcc.banking.domain.account.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
public class AccountBalanceUpdate {

    private String targetAccId;
    private Long modifierId;
    private BigDecimal balance;


    @Builder
    public AccountBalanceUpdate(String targetAccId,Long modifierId, BigDecimal balance) {
        this.modifierId = modifierId;
        this.balance = balance;
        this.targetAccId = targetAccId;
    }
}
