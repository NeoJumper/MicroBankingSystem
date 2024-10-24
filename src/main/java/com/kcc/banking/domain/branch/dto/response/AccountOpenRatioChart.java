package com.kcc.banking.domain.branch.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AccountOpenRatioChart {

    private String tradeMonth;  // 'YYYY-MM' 형식의 월
    private long openCount; // 해당 월의 계좌 개설 수
    private long totalOpenCount; // 전체 계좌 개설 수

    @Builder
    public AccountOpenRatioChart(String tradeMonth, long openCount, long totalOpenCount) {
        this.tradeMonth = tradeMonth;
        this.openCount = openCount;
        this.totalOpenCount = totalOpenCount;
    }
}
