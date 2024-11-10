package com.kcc.banking.domain.account.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;

@NoArgsConstructor
@Getter
@Setter
public class AccountCloseResult {
    // 계좌
    private String accountId;
    private String accountStatus;
    private Timestamp openDate;
    private BigDecimal accountPreInterRate;
    private BigDecimal accountBal;

    // 고객
    private String customerName;
    private long customerId;

    // 상품
    private String productName;
    private BigDecimal productInterRate;
    private BigDecimal productTaxRate;

    // 총 지급액(원금 + 이자)
    private BigDecimal amountSum;

    @Builder
    public AccountCloseResult(String accountId, String accountStatus, String customerName, long customerId, String productName, Timestamp openDate, BigDecimal accountPreInterRate, BigDecimal productInterRate, BigDecimal accountBal, BigDecimal productTaxRate, BigDecimal amountSum) {
        this.accountId = accountId;
        this.accountStatus = accountStatus;
        this.customerName = customerName;
        this.customerId = customerId;
        this.productName = productName;
        this.openDate = openDate;
        this.accountPreInterRate = accountPreInterRate;
        this.productInterRate = productInterRate;
        this.accountBal = accountBal;
        this.productTaxRate = productTaxRate;
        this.amountSum = amountSum;
    }
}
