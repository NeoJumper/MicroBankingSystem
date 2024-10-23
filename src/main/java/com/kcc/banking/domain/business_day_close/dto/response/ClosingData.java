package com.kcc.banking.domain.business_day_close.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class ClosingData {

    private String id;
    private String name;
    private String roles;
    private BigDecimal prevCashBalance;
    private BigDecimal totalDeposit;
    private BigDecimal totalWithdrawal;
    private BigDecimal vaultCash;
    private String status;


    @Builder
    public ClosingData(String id, String name, String roles, BigDecimal prevCashBalance, BigDecimal totalDeposit, BigDecimal totalWithdrawal, BigDecimal vaultCash, String status) {
        this.id = id;
        this.name = name;
        this.roles = roles;
        this.prevCashBalance = prevCashBalance;
        this.totalDeposit = totalDeposit;
        this.totalWithdrawal = totalWithdrawal;
        this.vaultCash = vaultCash;
        this.status = status;
    }
}
