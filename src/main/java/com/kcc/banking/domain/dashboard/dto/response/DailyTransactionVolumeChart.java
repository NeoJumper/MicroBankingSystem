package com.kcc.banking.domain.dashboard.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DailyTransactionVolumeChart {

    private String tradeDate;     // 거래 일자
    private long transactionCount; // 거래 수

    @Builder
    public DailyTransactionVolumeChart(String tradeDate, long transactionCount) {
        this.tradeDate = tradeDate;
        this.transactionCount = transactionCount;
    }
}
