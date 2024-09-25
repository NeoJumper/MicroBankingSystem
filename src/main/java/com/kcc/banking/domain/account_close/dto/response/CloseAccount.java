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
public class CloseAccount {
    private String accountId;
    private String accountStatus;
    private String customerName;
    private long customerId;
    private String productName;
    private Timestamp amountDate;
    private BigDecimal accountPreInterRate;
    private BigDecimal accountBal;
    private BigDecimal productTaxRate;

    @Builder
    public CloseAccount(String accountId, String accountStatus, String customerName, long customerId, String productName, Timestamp amountDate, BigDecimal accountPreInterRate, BigDecimal accountBal, BigDecimal productTaxRate) {
        this.accountId = accountId;
        this.accountStatus = accountStatus;
        this.customerName = customerName;
        this.customerId = customerId;
        this.productName = productName;
        this.amountDate = amountDate;
        this.accountPreInterRate = accountPreInterRate;
        this.accountBal = accountBal;
        this.productTaxRate = productTaxRate;
    }
}
