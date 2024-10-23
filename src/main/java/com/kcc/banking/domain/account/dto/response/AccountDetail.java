package com.kcc.banking.domain.account.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

@NoArgsConstructor
@Getter
public class AccountDetail {
    private String id;
    private Long branchId;
    private Long customerId;
    private Long productId;
    private Long registrantId;
    private Timestamp startDate;
    private BigDecimal preferentialInterestRate;
    private Timestamp expireDate;
    private String password;
    private BigDecimal balance;
    private BigDecimal perTradeLimit;
    private BigDecimal dailyLimit;
    private Timestamp openDate;
    private String status;
    private String tradeNumber;
    private Timestamp registrationDate;
    private Timestamp modificationDate;
    private Long modifierId;
    private Long version;
    // JOIN
    private String customerName;

    @Builder
    public AccountDetail(String id, Long branchId, Long customerId, Long productId, Long registrantId, Timestamp startDate, BigDecimal preferentialInterestRate, Timestamp expireDate, String password, BigDecimal balance, BigDecimal perTradeLimit, BigDecimal dailyLimit, Timestamp openDate, String status, String tradeNumber, Timestamp registrationDate, Timestamp modificationDate, Long modifierId, Long version, String customerName) {
        this.id = id;
        this.branchId = branchId;
        this.customerId = customerId;
        this.productId = productId;
        this.registrantId = registrantId;
        this.startDate = startDate;
        this.preferentialInterestRate = preferentialInterestRate;
        this.expireDate = expireDate;
        this.password = password;
        this.balance = balance;
        this.perTradeLimit = perTradeLimit;
        this.dailyLimit = dailyLimit;
        this.openDate = openDate;
        this.status = status;
        this.tradeNumber = tradeNumber;
        this.registrationDate = registrationDate;
        this.modificationDate = modificationDate;
        this.modifierId = modifierId;
        this.version = version;
        this.customerName = customerName;
    }
}
