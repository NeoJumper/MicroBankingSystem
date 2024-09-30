package com.kcc.banking.domain.employee.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class CashBalanceOfBranch {

    private BigDecimal totalCashBalance;

    @Builder
    public CashBalanceOfBranch(BigDecimal totalCashBalance) {
        this.totalCashBalance = totalCashBalance;
    }
}
