package com.kcc.banking.domain.cash_exchange.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class CashExchangeCloseData {
    private List<CashExchangeResultData> cashExchangeList;
    private BigDecimal lastManagerCash;

    @Builder
    public CashExchangeCloseData(List<CashExchangeResultData> cashExchangeList, BigDecimal lastManagerCash) {
        this.cashExchangeList = cashExchangeList;
        this.lastManagerCash = lastManagerCash;
    }

}
