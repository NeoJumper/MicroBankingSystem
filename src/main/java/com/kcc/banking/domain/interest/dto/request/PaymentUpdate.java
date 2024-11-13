package com.kcc.banking.domain.interest.dto.request;

import lombok.Builder;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class PaymentUpdate {
    private String accId;
    private long branchId;
    private long modifierId;
    private Timestamp payDate;

    // 자유 적금 중도 해지 계산 후 변경되는 이율 및 금액
    private Timestamp creationDate;
    private BigDecimal balance;
    private BigDecimal interestRate;
    private BigDecimal preferentialInterestRate;
    private BigDecimal amount;

    @Builder
    public PaymentUpdate(String accId, long branchId, long modifierId, Timestamp payDate, Timestamp creationDate, BigDecimal balance, BigDecimal interestRate, BigDecimal preferentialInterestRate, BigDecimal amount) {
        this.accId = accId;
        this.branchId = branchId;
        this.modifierId = modifierId;
        this.payDate = payDate;
        this.creationDate = creationDate;
        this.balance = balance;
        this.interestRate = interestRate;
        this.preferentialInterestRate = preferentialInterestRate;
        this.amount = amount;
    }
}
