package com.kcc.banking.domain.business_day.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
public class WorkerData {

    private String id;
    private BigDecimal prevCashBalance;
    private String status;


    @Builder
    public WorkerData(String id, BigDecimal prevCashBalance, String status) {
        this.id = id;
        this.prevCashBalance = prevCashBalance;
        this.status = status;
    }
}
