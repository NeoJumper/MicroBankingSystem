package com.kcc.banking.domain.trade.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;

@NoArgsConstructor
@Getter
@Setter
public class CloseAccount {
    private String accountId;
    private String accountStatus;
    private String customerName;
    private long customerId;
    private String productName;
    private Timestamp openDate;
    private BigDecimal accountPreInterRate;
    private BigDecimal productInterRate;
    private BigDecimal accountBal;
    private BigDecimal productTaxRate;

    @Builder
    public CloseAccount(String accountId, String accountStatus, String customerName, long customerId, String productName, Timestamp openDate, BigDecimal accountPreInterRate, BigDecimal productInterRate, BigDecimal accountBal, BigDecimal productTaxRate) {
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
    }
}
