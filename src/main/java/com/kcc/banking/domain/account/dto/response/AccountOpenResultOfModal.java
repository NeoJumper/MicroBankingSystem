package com.kcc.banking.domain.account.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@NoArgsConstructor
public class AccountOpenResultOfModal {

    private String accId;
    private String customerName;
    private int customerId;
    private String phoneNumber;

    private String productName;
    private Date startDate;
    private BigDecimal balance;
    private String branchName;
    private String registrantName;

    //총이율
    private BigDecimal totalInterestRate;

    public AccountOpenResultOfModal(String accId, String customerName, int customerId, String phoneNumber, String productName, Date startDate, BigDecimal balance, String branchName, String registrantName, BigDecimal totalInterestRate) {
        this.accId = accId;
        this.customerName = customerName;
        this.customerId = customerId;
        this.phoneNumber = phoneNumber;
        this.productName = productName;
        this.startDate = startDate;
        this.balance = balance;
        this.branchName = branchName;
        this.registrantName = registrantName;
        this.totalInterestRate = totalInterestRate;
    }
}
