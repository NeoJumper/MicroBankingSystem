package com.kcc.banking.domain.business_day_close.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;

@NoArgsConstructor
@Getter
@Setter
public class EmployeeClosingUpdate {

    private String status;
    private BigDecimal totalDeposit;
    private BigDecimal totalWithdrawal;
    private BigDecimal vaultCash;
    private Long modifierId;
    private Long targetEmployeeId;
    private Timestamp targetClosingDate;


    @Builder
    public EmployeeClosingUpdate(String status, BigDecimal totalDeposit, BigDecimal totalWithdrawal, BigDecimal vaultCash, Long modifierId, Long targetEmployeeId, Timestamp targetClosingDate) {
        this.status = status;
        this.totalDeposit = totalDeposit;
        this.totalWithdrawal = totalWithdrawal;
        this.vaultCash = vaultCash;
        this.modifierId = modifierId;
        this.targetEmployeeId = targetEmployeeId;
        this.targetClosingDate = targetClosingDate;
    }
}
