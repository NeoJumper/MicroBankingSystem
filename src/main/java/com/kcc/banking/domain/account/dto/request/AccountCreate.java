package com.kcc.banking.domain.account.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class AccountCreate {


    // 계좌번호
    private String id;
    private int branchId;
    private int customerId;
    private int productId;
    private Long registrantId;
    private Timestamp startDate;
    private Timestamp expireDate;
    private float preferentialInterestRate;
    private String password;
    private int balance;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp openDate;
    private String status;

    // 입력된 고객의 관리지점명 가져오기
    private String branchName;
    // 입력된 행원의 이름 가져오기
    private String empName;

    private String tradeNumber;


    @Builder
    public AccountCreate(String id, int branchId, int customerId, int productId, Long registrantId, Timestamp startDate, Timestamp expireDate, float preferentialInterestRate, String password, int balance, Timestamp openDate,  String tradeNumber) {
        this.id = id;
        this.branchId = branchId;
        this.customerId = customerId;
        this.productId = productId;
        this.registrantId = registrantId;
        this.startDate = startDate;
        this.expireDate = expireDate;
        this.preferentialInterestRate = preferentialInterestRate;
        this.password = password;
        this.balance = balance;
        this.openDate = openDate;
        this.status = "OPN";
        this.tradeNumber = tradeNumber;
    }


}
