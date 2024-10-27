package com.kcc.banking.domain.cash_exchange.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class CashExchangeCreate {
    private BigDecimal afterManagerCash;
    private BigDecimal afterEmployeeCash;
    private BigDecimal amount;
    private String exchangeType;
    private Long empId;

    private Long registrantId;
    private Long branchId;
    private String exchangeDate;
    private Long id;

    @Builder
    public CashExchangeCreate(BigDecimal afterManagerCash, BigDecimal afterEmployeeCash, BigDecimal amount, String exchangeType, Long registrantId, Long empId, Long branchId, String exchangeDate, Long id) {
        this.afterManagerCash = afterManagerCash;
        this.afterEmployeeCash = afterEmployeeCash;
        this.amount = amount;
        this.exchangeType = exchangeType;
        this.registrantId = registrantId;
        this.empId = empId;
        this.branchId = branchId;
        this.exchangeDate = exchangeDate;
        this.id = id;
    }
}
