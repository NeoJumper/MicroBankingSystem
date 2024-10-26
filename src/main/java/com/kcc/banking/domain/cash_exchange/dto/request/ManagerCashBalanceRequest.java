package com.kcc.banking.domain.cash_exchange.dto.request;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
public class ManagerCashBalanceRequest {
    private BigDecimal managerCashBalance;

    @Builder
    public ManagerCashBalanceRequest(BigDecimal managerCashBalance) {
        this.managerCashBalance = managerCashBalance;
    }

}
