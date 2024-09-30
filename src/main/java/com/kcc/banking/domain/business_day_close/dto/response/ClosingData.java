package com.kcc.banking.domain.business_day_close.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@Getter
public class ClosingData {

    private String id;
    private String name;
    private BigDecimal prevCashBalance;
    private BigDecimal totalDeposit;
    private BigDecimal totalWithdrawal;
    private BigDecimal vaultCash;
    private String status;
    private BigDecimal totalDepositOfTrade;
    private BigDecimal totalWithdrawalOfTrade;

    @Builder
    public ClosingData(String id, String name, BigDecimal prevCashBalance, BigDecimal totalDeposit, BigDecimal totalWithdrawal, BigDecimal vaultCash, String status, BigDecimal totalDepositOfTrade, BigDecimal totalWithdrawalOfTrade) {
        this.id = id;
        this.name = name;
        this.prevCashBalance = prevCashBalance;
        this.totalDeposit = totalDeposit;
        this.totalWithdrawal = totalWithdrawal;
        this.vaultCash = vaultCash;
        this.status = status;
        this.totalDepositOfTrade = totalDepositOfTrade;
        this.totalWithdrawalOfTrade = totalWithdrawalOfTrade;
    }
}
