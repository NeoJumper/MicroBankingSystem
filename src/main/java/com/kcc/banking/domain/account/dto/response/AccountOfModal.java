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
    private Long customerId;
    private String productName;
    private String securityLevel;
    private BigDecimal balance;
    private BigDecimal dailyLimit;
    private BigDecimal perTradeLimit;
    // 적금 계좌 해지
    private String productType;
    private String interestCalculationMethod;

    @Builder
    public AccountOfModal(String accId, String openDate, String status, String customerName, String securityLevel, String productName, BigDecimal balance, BigDecimal dailyLimit, BigDecimal perTradeLimit, Long customerId, String productType, String interestCalculationMethod) {
        this.accId = accId;
        this.openDate = openDate;
        this.status = status;
        this.customerName = customerName;
        this.securityLevel = securityLevel;
        this.productName = productName;
        this.balance = balance;
        this.dailyLimit = dailyLimit;
        this.perTradeLimit = perTradeLimit;
        this.customerId = customerId;
        this.productType = productType;
        this.interestCalculationMethod = interestCalculationMethod;
    }
}
