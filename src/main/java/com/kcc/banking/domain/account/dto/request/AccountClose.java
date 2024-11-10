package com.kcc.banking.domain.account.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
public class AccountClose {
    private String accId;
    // = totalAmount, 총 지급액
    private BigDecimal amount;
    private String status;
    private String description;
    private String tradeType;

    @Builder
    public AccountClose(String accId, BigDecimal amount, String status, String description, String tradeType) {
        this.accId = accId;
        this.amount = amount;
        this.status = status;
        this.description = description;
        this.tradeType = tradeType;
    }
}
