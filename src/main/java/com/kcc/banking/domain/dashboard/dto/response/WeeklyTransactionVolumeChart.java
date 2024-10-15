package com.kcc.banking.domain.dashboard.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class WeeklyTransactionVolumeChart {
    private long weekNumber;  // 해당 주를 나타내는 필드
    private long transactionCount;  // 해당 주의 거래량

    @Builder
    public WeeklyTransactionVolumeChart(long weekNumber, long transactionCount) {
        this.weekNumber = weekNumber;
        this.transactionCount = transactionCount;
    }
}

