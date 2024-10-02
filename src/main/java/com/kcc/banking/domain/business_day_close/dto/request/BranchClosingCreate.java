package com.kcc.banking.domain.business_day_close.dto.request;

import com.kcc.banking.domain.business_day.dto.request.WorkerData;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@Setter
@Getter
public class BranchClosingCreate {

    private String closingDate;
    private String branchId;
    private String registrantId;
    private String status;
    private BigDecimal prevCashBalance;
    private Long tradeNumber;

    @Builder
    public BranchClosingCreate(String closingDate, String registrantId, String branchId, String status, BigDecimal prevCashBalance, Long tradeNumber) {
        this.closingDate = closingDate;
        this.registrantId = registrantId;
        this.branchId = branchId;
        this.status = status;
        this.prevCashBalance = prevCashBalance;
        this.tradeNumber = tradeNumber;
    }
}
