package com.kcc.banking.domain.branch.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class YearlyTransactionComparisonChart {

    private String year;  // 연도
    private long cashTransactionCount; // 현금 거래 수
    private long transferTransactionCount; // 이체 거래 수
    private long closeTransactionCount; // 해지 거래 수

    @Builder
    public YearlyTransactionComparisonChart(String year, long cashTransactionCount, long transferTransactionCount, long closeTransactionCount) {
        this.year = year;
        this.cashTransactionCount = cashTransactionCount;
        this.transferTransactionCount = transferTransactionCount;
        this.closeTransactionCount = closeTransactionCount;
    }
}
