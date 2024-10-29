package com.kcc.banking.domain.account.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

@NoArgsConstructor
@Getter
public class AccountDetailForInterest {
    private String accId;
    private Long branchId;
    private Long productId;
    private Long registrantId;
    private BigDecimal interestRate;
    private BigDecimal preferentialInterestRate;
    private BigDecimal balance;

    // 예금 적금 유형 구분
    private String period;
    // 자율적립/정기적립 유형 구분
    //
    private String productType;
    // 상품 이자 계산 방법
    private String interestCalculationMethod;



    @Builder
    public AccountDetailForInterest(String accId, Long branchId, Long productId, Long registrantId, BigDecimal interestRate, BigDecimal preferentialInterestRate, BigDecimal balance, String period, String productType, String interestCalculationMethod) {
        this.accId = accId;
        this.branchId = branchId;
        this.productId = productId;
        this.registrantId = registrantId;
        this.interestRate = interestRate;
        this.preferentialInterestRate = preferentialInterestRate;
        this.balance = balance;
        this.period = period;
        this.productType = productType;
        this.interestCalculationMethod = interestCalculationMethod;
    }
}
