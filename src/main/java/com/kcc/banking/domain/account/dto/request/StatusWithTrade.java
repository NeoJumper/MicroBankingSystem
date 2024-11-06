package com.kcc.banking.domain.account.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
public class StatusWithTrade {
    private String accId;
    private BigDecimal amount;
    private String status;
    private String description;
    private String tradeType;
    private String closeType;


    @Builder
    public StatusWithTrade(String accId, BigDecimal amount, String status, String description, String tradeType, String closeType) {
        this.accId = accId;
        this.amount = amount;
        this.status = status;
        this.description = description;
        this.tradeType = tradeType;
        this.closeType = closeType;
    }
}
