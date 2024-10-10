package com.kcc.banking.domain.account.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class AccountCreate {


    // 계좌번호
    private String id;
    private Long branchId;
    private Long customerId;
    private Long productId;
    private Long registrantId;
    private String startDate;
    private float preferentialInterestRate;
    private String password;
    private BigDecimal balance;
    private String openDate;
    private String status;
    private Long tradeNumber;

    @Builder
    public AccountCreate(String id, Long branchId, Long customerId, Long productId, Long registrantId, String startDate, float preferentialInterestRate, String password, BigDecimal balance, String openDate, String status, Long tradeNumber) {
        this.id = id;
        this.branchId = branchId;
        this.customerId = customerId;
        this.productId = productId;
        this.registrantId = registrantId;
        this.startDate = startDate;
        this.preferentialInterestRate = preferentialInterestRate;
        this.password = password;
        this.balance = balance;
        this.openDate = openDate;
        this.status = status;
        this.tradeNumber = tradeNumber;
    }
}
