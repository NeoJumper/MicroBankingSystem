package com.kcc.banking.domain.account.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter
public class AccountOpen {

    private String id;
    private Long branchId;
    private Long customerId;
    private Long productId;
    private BigDecimal preferentialInterestRate;
    private String password;
    private BigDecimal balance;


    @Builder
    public AccountOpen(String id, Long branchId, Long customerId, Long productId, BigDecimal preferentialInterestRate, String password, BigDecimal balance) {
        this.id = id;
        this.branchId = branchId;
        this.customerId = customerId;
        this.productId = productId;
        this.preferentialInterestRate = preferentialInterestRate;
        this.password = password;
        this.balance = balance;
    }
}
