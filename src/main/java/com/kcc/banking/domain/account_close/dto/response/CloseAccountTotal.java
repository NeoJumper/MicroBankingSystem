package com.kcc.banking.domain.account_close.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;

@NoArgsConstructor
@Getter
@Setter
public class CloseAccountTotal {
    private String accountId;
    private String accountStatus;
    private String customerName;
    private long customerId;
    private String productName;
    private Timestamp amountDate;
    private BigDecimal accountPreInterRate;
    private BigDecimal accountBal;
    private BigDecimal productTaxRate;
    // 총 이율, 총 이자
    private BigDecimal interestRateSum;
    private BigDecimal amountSum;

    @Builder
    public CloseAccountTotal(String accountId, String accountStatus, String customerName, long customerId, String productName, Timestamp amountDate, BigDecimal accountPreInterRate, BigDecimal accountBal, BigDecimal productTaxRate, BigDecimal interestRateSum, BigDecimal amountSum) {
        this.accountId = accountId;
        this.accountStatus = accountStatus;
        this.customerName = customerName;
        this.customerId = customerId;
        this.productName = productName;
        this.amountDate = amountDate;
        this.accountPreInterRate = accountPreInterRate;
        this.accountBal = accountBal;
        this.productTaxRate = productTaxRate;
        this.interestRateSum = interestRateSum;
        this.amountSum = amountSum;
    }
}
