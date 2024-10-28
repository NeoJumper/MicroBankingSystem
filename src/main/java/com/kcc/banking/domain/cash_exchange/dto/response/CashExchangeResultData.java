package com.kcc.banking.domain.cash_exchange.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter
public class CashExchangeResultData {

    private String id;
    private String registrant_id;
    private String empId;
    private String branchId;
    private BigDecimal amount;
    private BigDecimal empCashBalance;
    private BigDecimal managerCashBalance;
    private String exchangeType;

    private String empName;
    private String regName;
    private String branchName;

    @Builder
    public CashExchangeResultData(String id, BigDecimal amount, String exchangeType, String empId, String empName, String branchId, String registrant_id, String regName, BigDecimal empCashBalance, BigDecimal managerCashBalance, String branchName) {
        this.id = id;
        this.amount = amount;
        this.exchangeType = exchangeType;
        this.empId = empId;
        this.empName = empName;
        this.branchId = branchId;
        this.registrant_id = registrant_id;
        this.regName = regName;
        this.empCashBalance = empCashBalance;
        this.managerCashBalance = managerCashBalance;
        this.branchName = branchName;
    }
}
