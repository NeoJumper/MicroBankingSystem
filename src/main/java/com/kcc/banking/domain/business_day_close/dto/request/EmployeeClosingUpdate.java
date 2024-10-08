package com.kcc.banking.domain.business_day_close.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
public class EmployeeClosingUpdate {

    private String status;
    private BigDecimal vaultCash;
    private Long modifierId;
    private Long targetEmployeeId;
    private String targetClosingDate;

    @Builder
    public EmployeeClosingUpdate(String status, BigDecimal vaultCash, Long modifierId, Long targetEmployeeId, String targetClosingDate) {
        this.status = status;
        this.vaultCash = vaultCash;
        this.modifierId = modifierId;
        this.targetEmployeeId = targetEmployeeId;
        this.targetClosingDate = targetClosingDate;
    }
}
