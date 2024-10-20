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
    private BigDecimal perTradeLimit;
    private BigDecimal dailyLimit;
    private String password;
    private BigDecimal balance;
    private String accountType;
    private String tradeType;


    @Builder
    public AccountOpen(String id, Long branchId, Long customerId, Long productId, BigDecimal preferentialInterestRate, BigDecimal perTradeLimit, BigDecimal dailyLimit, String password, BigDecimal balance, String accountType, String tradeType) {
        this.id = id;
        this.branchId = branchId;
        this.customerId = customerId;
        this.productId = productId;
        this.preferentialInterestRate = preferentialInterestRate;
        this.perTradeLimit = perTradeLimit;
        this.dailyLimit = dailyLimit;
        this.password = password;
        this.balance = balance;
        this.accountType = accountType;
        this.tradeType = tradeType;
    }
}
