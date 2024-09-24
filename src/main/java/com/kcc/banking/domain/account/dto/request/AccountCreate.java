package com.kcc.banking.domain.account.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class AccountCreate {



    private int  id;
    private int branchId;
    private int customerId;
    private int productId;
    private int empId;
    private Date startDate;
    private Date expireDate;
    private float preferentialInterestRate;
    private String password;
    private int balance;
    private Date openDate;
    private String status;


    private String tradeNumber;


    @Builder
    public AccountCreate(int id, int branchId, int customerId, int productId, int empId, Date startDate, Date expireDate, float preferentialInterestRate, String password, int balance, Date openDate, String status, String tradeNumber) {
        this.id = id;
        this.branchId = branchId;
        this.customerId = customerId;
        this.productId = productId;
        this.empId = empId;
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
