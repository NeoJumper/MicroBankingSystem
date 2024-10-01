package com.kcc.banking.domain.business_day_close.dto.request;

import com.kcc.banking.domain.business_day.dto.request.WorkerData;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;

@NoArgsConstructor
@Setter
@Getter
public class EmployeeClosingCreate {

    private String closingDate;
    private String branchId;
    private String registrantId;
    private String status;
    private BigDecimal prevCashBalance;
    private Long tradeNumber;

    @Builder
    public EmployeeClosingCreate(String closingDate, String registrantId, String branchId, String status, BigDecimal prevCashBalance, Long tradeNumber) {
        this.closingDate = closingDate;
        this.registrantId = registrantId;
        this.branchId = branchId;
        this.status = status;
        this.prevCashBalance = prevCashBalance;
        this.tradeNumber = tradeNumber;
    }
    public static EmployeeClosingCreate of(WorkerData workerData, String branchId, String businessDateToChange, Long tradeNumber){
        return EmployeeClosingCreate.builder()
                .closingDate(businessDateToChange)
                .branchId(branchId)
                .registrantId(workerData.getId())
                .status(workerData.getStatus())
                .prevCashBalance(workerData.getPrevCashBalance())
                .tradeNumber(tradeNumber)
                .build();
    }
}
