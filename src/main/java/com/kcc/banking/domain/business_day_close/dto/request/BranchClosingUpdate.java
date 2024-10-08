package com.kcc.banking.domain.business_day_close.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
public class BranchClosingUpdate {

    private String status;
    private BigDecimal vaultCash;
    private Long modifierId;
    private Long targetBranchId;
    private String targetClosingDate;

    @Builder
    public BranchClosingUpdate(String status, BigDecimal vaultCash, Long modifierId, Long targetBranchId, String targetClosingDate) {
        this.status = status;
        this.vaultCash = vaultCash;
        this.modifierId = modifierId;
        this.targetBranchId = targetBranchId;
        this.targetClosingDate = targetClosingDate;
    }
}
