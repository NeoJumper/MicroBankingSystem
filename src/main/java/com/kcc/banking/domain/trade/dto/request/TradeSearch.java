package com.kcc.banking.domain.trade.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TradeSearch {

    private String accId;
    private String tradeType;
    private String startDate;
    private String endDate;
    private String sortOrder;

    public TradeSearch(String accId, String tradeType, String startDate, String endDate, String sortOrder) {
        this.accId = accId;
        this.tradeType = tradeType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.sortOrder = sortOrder;
    }
}
