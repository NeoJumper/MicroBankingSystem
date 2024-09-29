package com.kcc.banking.domain.employee.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class CashBalanceOfEmployee {
    private Long id;
    private String name;
    private BigDecimal cashBalance;

    @Builder
    public CashBalanceOfEmployee(Long id, String name, BigDecimal cashBalance) {
        this.id = id;
        this.name = name;
        this.cashBalance = cashBalance;
    }
}
