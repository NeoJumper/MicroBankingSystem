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
    private BigDecimal cashBalanceOfBranch;
    private String businessDateToChange;

    public BusinessDayChange(List<WorkerData> workerDataList, BigDecimal prevCashBalanceOfBranch, BigDecimal cashBalanceOfBranch,String businessDateToChange) {
        this.workerDataList = workerDataList;
        this.prevCashBalanceOfBranch = prevCashBalanceOfBranch;
        this.cashBalanceOfBranch = cashBalanceOfBranch;
        this.businessDateToChange = businessDateToChange;
    }
}
