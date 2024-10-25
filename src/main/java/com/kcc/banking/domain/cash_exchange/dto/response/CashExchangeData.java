package com.kcc.banking.domain.cash_exchange.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter
public class CashExchangeData {

    private String id;
    private BigDecimal amount;
    private String exchangeType;
    private String empId;
    private String name;
    private BigDecimal empCashBalance;
    private BigDecimal managerCashBalance;

    @Builder
    public CashExchangeData(String id, BigDecimal amount, String exchangeType, String empId, String name, BigDecimal empCashBalance, BigDecimal managerCashBalance) {
        this.id = id;
        this.amount = amount;
        this.exchangeType = exchangeType;
        this.empId = empId;
        this.name = name;
        this.empCashBalance = empCashBalance;
        this.managerCashBalance = managerCashBalance;
    }

}
