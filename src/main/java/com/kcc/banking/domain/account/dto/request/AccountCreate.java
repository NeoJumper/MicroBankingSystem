package com.kcc.banking.domain.account.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;

@NoArgsConstructor
@Getter
@Setter
public class AccountCreate {


    // 계좌번호
    private String id;
    private Long branchId;
    private Long customerId;
    private Long productId;
    private Long registrantId;
    private Timestamp startDate;
    private BigDecimal preferentialInterestRate;
    private BigDecimal perTradeLimit;
    private BigDecimal dailyLimit;
    private String password;
    private BigDecimal balance;
    private Timestamp openDate;
    private String status;
    private String accountType;
    private Long tradeNumber;

    @Builder
    public AccountCreate(String id, Long branchId, Long customerId, Long productId, Long registrantId, Timestamp startDate, BigDecimal preferentialInterestRate, BigDecimal perTradeLimit, BigDecimal dailyLimit, String password, BigDecimal balance, Timestamp openDate, String status, String accountType, Long tradeNumber) {
        this.id = id;
        this.branchId = branchId;
        this.customerId = customerId;
        this.productId = productId;
        this.registrantId = registrantId;
        this.startDate = startDate;
        this.preferentialInterestRate = preferentialInterestRate;
        this.perTradeLimit = perTradeLimit;
        this.dailyLimit = dailyLimit;
        this.password = password;
        this.balance = balance;
        this.openDate = openDate;
        this.status = status;
        this.accountType = accountType;
        this.tradeNumber = tradeNumber;
    }
}
