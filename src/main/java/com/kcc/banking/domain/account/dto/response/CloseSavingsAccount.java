package com.kcc.banking.domain.account.dto.response;

import com.kcc.banking.domain.auto_transfer.dto.response.AutoTransferClose;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class CloseSavingsAccount {
    private String accountId;
    private String accountStatus;
    private String customerName;
    private long customerId;
    private String productName;
    private BigDecimal accountInterestRate;
    private BigDecimal productInterestRate;
    private BigDecimal accountBalance;
    private BigDecimal productTaxRate;

    @Builder
    public CloseSavingsAccount(String accountId, String accountStatus, String customerName, long customerId, String productName, BigDecimal accountInterestRate, BigDecimal productInterestRate, BigDecimal accountBalance, BigDecimal productTaxRate ,AutoTransferClose autoTransferClose) {
        this.accountId = accountId;
        this.accountStatus = accountStatus;
        this.customerName = customerName;
        this.customerId = customerId;
        this.productName = productName;
        this.accountInterestRate = accountInterestRate;
        this.productInterestRate = productInterestRate;
        this.accountBalance = accountBalance;
        this.productTaxRate = productTaxRate;
    }
}
