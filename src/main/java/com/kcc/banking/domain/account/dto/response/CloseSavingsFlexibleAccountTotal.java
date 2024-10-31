package com.kcc.banking.domain.account.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
public class CloseSavingsFlexibleAccountTotal {

    // 계좌
    private String id;
    private String openDate;
    private BigDecimal balance;
    private String status;
    private BigDecimal preferentialInterestRate;

    // 고객
    private Long customerId;
    private String customerName;
    private String securityLevel;
    
    // 상품
    private String productName;
    private String productType;
    private String interestCalculationMethod;
    private String period;
    private BigDecimal interestRate;

    @Builder
    public CloseSavingsFlexibleAccountTotal(String id, String openDate, BigDecimal balance, String status, String securityLevel, Long customerId, String customerName, String productName, String productType, String interestCalculationMethod, String period, BigDecimal interestRate, BigDecimal preferentialInterestRate) {
        this.id = id;
        this.openDate = openDate;
        this.balance = balance;
        this.status = status;
        this.securityLevel = securityLevel;
        this.customerId = customerId;
        this.customerName = customerName;
        this.productName = productName;
        this.productType = productType;
        this.interestCalculationMethod = interestCalculationMethod;
        this.period = period;
        this.interestRate = interestRate;
        this.preferentialInterestRate = preferentialInterestRate;
    }
}
