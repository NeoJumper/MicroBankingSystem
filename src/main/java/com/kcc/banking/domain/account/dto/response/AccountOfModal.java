package com.kcc.banking.domain.account.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
public class AccountOfModal {
    private String accId;
    private String openDate;
    private String status;
    private String customerName;
    private String productName;
    private BigDecimal balance;
    private BigDecimal dailyLimit;
    private BigDecimal perTradeLimit;

    @Builder
    public AccountOfModal(String accId, String openDate, String status, String customerName, String productName, BigDecimal balance, BigDecimal dailyLimit, BigDecimal perTradeLimit) {
        this.accId = accId;
        this.openDate = openDate;
        this.status = status;
        this.customerName = customerName;
        this.productName = productName;
        this.balance = balance;
        this.dailyLimit = dailyLimit;
        this.perTradeLimit = perTradeLimit;
    }
}
