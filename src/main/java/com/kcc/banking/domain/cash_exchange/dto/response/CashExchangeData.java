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

    @Builder
    public CashExchangeData(String id, BigDecimal amount, String exchangeType) {
        this.id = id;
        this.amount = amount;
        this.exchangeType = exchangeType;
    }

}
