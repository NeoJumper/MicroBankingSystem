package com.kcc.banking.domain.dashboard.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DailyTransactionTypeChart {

    private String transactionDate;
    private String transactionType;
    private long transactionCount;

    @Builder
    public DailyTransactionTypeChart(String transactionDate, String transactionType, long transactionCount) {
        this.transactionDate = transactionDate;
        this.transactionType = transactionType;
        this.transactionCount = transactionCount;
    }
}
