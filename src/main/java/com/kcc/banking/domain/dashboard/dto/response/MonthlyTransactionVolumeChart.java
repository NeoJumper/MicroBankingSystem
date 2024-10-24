package com.kcc.banking.domain.dashboard.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MonthlyTransactionVolumeChart {
    private String monthOfYear;  // 해당 월을 나타내는 필드 (ex: "2024-08")
    private int transactionCount;  // 해당 월의 거래량

    @Builder
    public MonthlyTransactionVolumeChart(String monthOfYear, int transactionCount) {
        this.monthOfYear = monthOfYear;
        this.transactionCount = transactionCount;
    }
}