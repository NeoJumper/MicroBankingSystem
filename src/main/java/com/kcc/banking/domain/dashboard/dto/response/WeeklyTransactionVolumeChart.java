package com.kcc.banking.domain.dashboard.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class WeeklyTransactionVolumeChart {
    private String weekOfYear;  // 해당 주를 나타내는 필드 (ex: "2024-W12")
    private int transactionCount;  // 해당 주의 거래량

    @Builder
    public WeeklyTransactionVolumeChart(String weekOfYear, int transactionCount) {
        this.weekOfYear = weekOfYear;
        this.transactionCount = transactionCount;
    }
}

