package com.kcc.banking.domain.account.dto.response;

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
    private float interestRate;
    private BigDecimal balance;





    public AccountDetailForInterest(String accId, Long branchId, Long productId, Long registrantId,  float interestRate, BigDecimal balance) {
        this.accId = accId;
        this.branchId = branchId;
        this.productId = productId;
        this.registrantId = registrantId;
        this.interestRate = interestRate;
        this.balance = balance;


    }
}
