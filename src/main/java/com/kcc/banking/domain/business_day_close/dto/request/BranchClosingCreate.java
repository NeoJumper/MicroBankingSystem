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
public class BranchClosingCreate {

    private Timestamp closingDate;
    private String branchId;
    private String status;
    private BigDecimal prevCashBalance;
    private Long tradeNumber;
    private String registrantId;


    @Builder
    public BranchClosingCreate(Timestamp closingDate, String branchId, String status, BigDecimal prevCashBalance, Long tradeNumber, String registrantId) {
        this.closingDate = closingDate;
        this.branchId = branchId;
        this.status = status;
        this.prevCashBalance = prevCashBalance;
        this.tradeNumber = tradeNumber;
        this.registrantId = registrantId;

    }
}
