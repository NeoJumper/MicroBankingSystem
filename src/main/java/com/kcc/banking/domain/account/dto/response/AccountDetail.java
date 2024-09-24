package com.kcc.banking.domain.account.dto.response;

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
    private Long empId;
    private Timestamp startDate;
    private BigDecimal preferentialInterestRate;
    private Timestamp expireDate;
    private String password;
    private BigDecimal balance;
    private Timestamp openDate;
    private String status;
    private String tradeNumber;
    private Timestamp registrationDate;
    private String registrant;
    private Timestamp modificationDate;
    private String modifier;
    private Long version;

    public AccountDetail(String id, Long branchId, Long customerId, Long productId, Long empId, Timestamp startDate, BigDecimal preferentialInterestRate, Timestamp expireDate, String password, BigDecimal balance, Timestamp openDate, String status, String tradeNumber, Timestamp registrationDate, String registrant, Timestamp modificationDate, String modifier, Long version) {
        this.id = id;
        this.branchId = branchId;
        this.customerId = customerId;
        this.productId = productId;
        this.empId = empId;
        this.startDate = startDate;
        this.preferentialInterestRate = preferentialInterestRate;
        this.expireDate = expireDate;
        this.password = password;
        this.balance = balance;
        this.openDate = openDate;
        this.status = status;
        this.tradeNumber = tradeNumber;
        this.registrationDate = registrationDate;
        this.registrant = registrant;
        this.modificationDate = modificationDate;
        this.modifier = modifier;
        this.version = version;
    }
}
