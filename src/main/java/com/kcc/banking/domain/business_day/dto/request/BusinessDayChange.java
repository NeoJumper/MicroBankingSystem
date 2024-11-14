package com.kcc.banking.domain.business_day.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class BusinessDayChange {
    private List<WorkerData> workerDataList;
    private BigDecimal prevCashBalanceOfBranch;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date businessDateToChange;

    public BusinessDayChange(List<WorkerData> workerDataList, BigDecimal prevCashBalanceOfBranch, Date businessDateToChange) {
        this.workerDataList = workerDataList;
        this.prevCashBalanceOfBranch = prevCashBalanceOfBranch;
        this.businessDateToChange = businessDateToChange;
    }
}
