package com.kcc.banking.domain.account.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter
public class AccountUpdate {

    private String targetAccId;
    private Long modifierId;
    private BigDecimal balance;
    private BigDecimal perTradeLimit;
    private BigDecimal dailyLimit;
    private String status;
    private String expireDate;


    @Builder
    public AccountUpdate(String targetAccId, Long modifierId, BigDecimal balance, BigDecimal perTradeLimit, BigDecimal dailyLimit, String status, String expireDate) {
        this.targetAccId = targetAccId;
        this.modifierId = modifierId;
        this.balance = balance;
        this.perTradeLimit = perTradeLimit;
        this.dailyLimit = dailyLimit;
        this.status = status;
        this.expireDate = expireDate;
    }
}
