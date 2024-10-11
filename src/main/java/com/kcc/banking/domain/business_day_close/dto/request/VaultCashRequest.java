package com.kcc.banking.domain.business_day_close.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
public class VaultCashRequest {
    private BigDecimal vaultCash;

    @Builder
    public VaultCashRequest(BigDecimal vaultCash) {
        this.vaultCash = vaultCash;
    }
}