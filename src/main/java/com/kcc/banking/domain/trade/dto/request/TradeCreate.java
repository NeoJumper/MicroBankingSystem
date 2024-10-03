package com.kcc.banking.domain.trade.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class TradeCreate {

    private String accId;
    private String tradeType;
    private BigDecimal amount;

    @Builder
    public TradeCreate(String accId, String tradeType, BigDecimal amount) {
        this.accId = accId;
        this.tradeType = tradeType;
        this.amount = amount;
    }
}
