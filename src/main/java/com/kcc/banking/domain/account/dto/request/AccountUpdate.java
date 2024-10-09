package com.kcc.banking.domain.account.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
public class AccountUpdate {

    private String targetAccId;
    private Long modifierId;
    private BigDecimal balance;
    private String status;


    @Builder
    public AccountUpdate(String targetAccId, Long modifierId, BigDecimal balance, String status) {
        this.modifierId = modifierId;
        this.balance = balance;
        this.targetAccId = targetAccId;
        this.status = status;
    }
}
