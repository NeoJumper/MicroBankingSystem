package com.kcc.banking.domain.cash_exchange.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
public class EmployeeDataResponse {

    private Long id;
    private String name;
    private BigDecimal currentBalance;

    @Builder
    public EmployeeDataResponse(Long id, String name, BigDecimal currentBalance) {
        this.id = id;
        this.name = name;
        this.currentBalance = currentBalance;
    }
}
