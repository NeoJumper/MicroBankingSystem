package com.kcc.banking.domain.account.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
public class AccountOfModal {
    private String accountId;
    private String openDate;
    private String customerName;
    private String productName;
    private BigDecimal balance;

    public AccountOfModal(String accountId, String openDate, String customerName, String productName, BigDecimal balance) {
        this.accountId = accountId;
        this.openDate = openDate;
        this.customerName = customerName;
        this.productName = productName;
        this.balance = balance;
    }
}
