package com.kcc.banking.domain.business_day_close.dto.request;

import com.kcc.banking.domain.business_day.dto.request.WorkerData;
import com.kcc.banking.domain.employee.dto.request.BusinessDateAndBranchId;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@Setter
@Getter
public class EmployeeClosingCreate {


    private String closingDate; // 마감일
    private String branchId; // 마감 지점
    private String status; // 상태
    private BigDecimal prevCashBalance;
    private Long tradeNumber; // 마감 번호
    private String registrantId; // 담당자

    @Builder
    public EmployeeClosingCreate(String closingDate, String branchId, String status, BigDecimal prevCashBalance, Long tradeNumber, String registrantId) {
        this.closingDate = closingDate;
        this.branchId = branchId;
        this.status = status;
        this.prevCashBalance = prevCashBalance;
        this.tradeNumber = tradeNumber;
        this.registrantId = registrantId;

    }

    public static EmployeeClosingCreate of(WorkerData workerData, String businessDateToChange, BusinessDateAndBranchId businessDateAndBranchId, Long tradeNumber){
        return EmployeeClosingCreate.builder()
                .closingDate(businessDateToChange)
                .branchId(businessDateAndBranchId.getBranchId())
                .status(workerData.getStatus())
                .prevCashBalance(workerData.getPrevCashBalance())
                .tradeNumber(tradeNumber)
                .registrantId(workerData.getId())
                .build();
    }
}
