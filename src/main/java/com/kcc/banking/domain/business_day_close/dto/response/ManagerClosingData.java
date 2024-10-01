package com.kcc.banking.domain.business_day_close.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@Getter
public class ManagerClosingData {

    private List<ClosingData> closingDataList;
    private BigDecimal prevCashBalanceOfBranch;
    private BigDecimal totalDepositOfBranch;
    private BigDecimal totalWithdrawalOfBranch;
    private BigDecimal vaultCashOfBranch;

    @Builder
    public ManagerClosingData(List<ClosingData> closingDataList, BigDecimal prevCashBalanceOfBranch, BigDecimal totalDepositOfBranch, BigDecimal totalWithdrawalOfBranch, BigDecimal vaultCashOfBranch) {
        this.closingDataList = closingDataList;
        this.prevCashBalanceOfBranch = prevCashBalanceOfBranch;
        this.totalDepositOfBranch = totalDepositOfBranch;
        this.totalWithdrawalOfBranch = totalWithdrawalOfBranch;
        this.vaultCashOfBranch = vaultCashOfBranch;
    }

    public static ManagerClosingData of(List<ClosingData> closingDataList){
        return ManagerClosingData.builder()
                .closingDataList(closingDataList)
                .prevCashBalanceOfBranch(closingDataList.stream().map(ClosingData::getPrevCashBalance).reduce(BigDecimal.ZERO, BigDecimal::add) )
                .totalDepositOfBranch(closingDataList.stream().map(ClosingData::getTotalDeposit).reduce(BigDecimal.ZERO, BigDecimal::add))
                .totalWithdrawalOfBranch(closingDataList.stream().map(ClosingData::getTotalWithdrawal).reduce(BigDecimal.ZERO, BigDecimal::add))
                .vaultCashOfBranch(closingDataList.stream().map(ClosingData::getVaultCash).reduce(BigDecimal.ZERO, BigDecimal::add))
                .build();
    }
}
