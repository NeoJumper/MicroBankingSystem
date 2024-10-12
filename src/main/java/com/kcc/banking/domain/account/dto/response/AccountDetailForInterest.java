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


    @Builder
    public AccountDetailForInterest(String accId, Long branchId, Long productId, Long registrantId, BigDecimal interestRate, BigDecimal preferentialInterestRate, BigDecimal balance) {
        this.accId = accId;
        this.branchId = branchId;
        this.productId = productId;
        this.registrantId = registrantId;
        this.interestRate = interestRate;
        this.preferentialInterestRate = preferentialInterestRate;
        this.balance = balance;
    }
}
