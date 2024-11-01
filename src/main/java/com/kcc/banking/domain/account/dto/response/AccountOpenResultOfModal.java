package com.kcc.banking.domain.account.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class AccountOpenResultOfModal {

    private String accId;
    private String accountType;
    private String password;
    private String customerName;
    private String securityLevel;
    private int customerId;
    private String phoneNumber;

    private String productName;
    private String startDate;
    private BigDecimal balance;
    private String branchName;
    private String registrantName;

    private BigDecimal perTradeLimit;
    private BigDecimal dailyLimit;

    private BigDecimal preferentialInterestRate;
    private BigDecimal interestRate;
    // 총 이율
    private BigDecimal totalInterestRate;

    // 총 이체 출금액
    private BigDecimal transferAmountOfToday;


    @Builder
    public AccountOpenResultOfModal(String accId,String accountType, String password, String customerName,String securityLevel, int customerId, String phoneNumber, String productName, String startDate, BigDecimal balance, String branchName, String registrantName, BigDecimal perTradeLimit, BigDecimal dailyLimit, BigDecimal preferentialInterestRate, BigDecimal interestRate, BigDecimal totalInterestRate, BigDecimal transferAmountOfToday) {
        this.accId = accId;
        this.accountType = accountType;
        this.password = password;
        this.customerName = customerName;
        this.customerId = customerId;
        this.phoneNumber = phoneNumber;
        this.securityLevel = securityLevel;
        this.productName = productName;
        this.startDate = startDate;
        this.balance = balance;
        this.branchName = branchName;
        this.registrantName = registrantName;
        this.perTradeLimit = perTradeLimit;
        this.dailyLimit = dailyLimit;
        this.preferentialInterestRate = preferentialInterestRate;
        this.interestRate = interestRate;
        this.totalInterestRate = totalInterestRate;
        this.transferAmountOfToday = transferAmountOfToday;
    }
}
