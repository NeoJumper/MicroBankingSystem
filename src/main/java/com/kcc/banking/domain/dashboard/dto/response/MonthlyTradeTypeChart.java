package com.kcc.banking.domain.dashboard.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MonthlyTradeTypeChart {

    private String transactionType;
    private long transactionCount;

    public MonthlyTradeTypeChart(String transactionType, long transactionCount) {
        this.transactionType = transactionType;
        this.transactionCount = transactionCount;
    }
}
