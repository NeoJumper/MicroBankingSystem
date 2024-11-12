package com.kcc.banking.domain.dashboard.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DailyEmployeeTransactionVolumeChart {
    private String tradeDate;
    private int transactionCount;

    public DailyEmployeeTransactionVolumeChart(String tradeDate, int transactionCount) {
        this.tradeDate = tradeDate;
        this.transactionCount = transactionCount;
    }
}
