package com.kcc.banking.domain.business_day.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class BusinessDayChange {
    private List<WorkerData> workerDataList;
    private BigDecimal prevCashBalanceOfBranch;
    private String businessDateToChange;

    public BusinessDayChange(List<WorkerData> workerDataList, BigDecimal prevCashBalanceOfBranch, String businessDateToChange) {
        this.workerDataList = workerDataList;
        this.prevCashBalanceOfBranch = prevCashBalanceOfBranch;
        this.businessDateToChange = businessDateToChange;
    }
}
