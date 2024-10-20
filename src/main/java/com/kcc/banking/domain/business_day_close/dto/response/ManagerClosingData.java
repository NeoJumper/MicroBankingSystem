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
public class ManagerClosingData {

    private List<ClosingData> closingDataList;
    private BigDecimal prevCashBalanceOfBranch;
    private BigDecimal totalDepositOfBranch;
    private BigDecimal totalWithdrawalOfBranch;
    private BigDecimal vaultCashOfBranch;
    private String branchName;

    @Builder
    public ManagerClosingData(List<ClosingData> closingDataList, BigDecimal prevCashBalanceOfBranch, BigDecimal totalDepositOfBranch, BigDecimal totalWithdrawalOfBranch, BigDecimal vaultCashOfBranch, String branchName) {
        this.closingDataList = closingDataList;
        this.prevCashBalanceOfBranch = prevCashBalanceOfBranch;
        this.totalDepositOfBranch = totalDepositOfBranch;
        this.totalWithdrawalOfBranch = totalWithdrawalOfBranch;
        this.vaultCashOfBranch = vaultCashOfBranch;
        this.branchName = branchName;
    }

    public static ManagerClosingData of(List<ClosingData> closingDataList, BigDecimal branchClosingVaultCash, String branchName){
        ManagerClosingData managerClosingData = ManagerClosingData.builder()
                .closingDataList(closingDataList)
                .prevCashBalanceOfBranch(closingDataList.stream().map(ClosingData::getPrevCashBalance).reduce(BigDecimal.ZERO, BigDecimal::add))
                .totalDepositOfBranch(closingDataList.stream().map(ClosingData::getTotalDeposit).reduce(BigDecimal.ZERO, BigDecimal::add))
                .totalWithdrawalOfBranch(closingDataList.stream().map(ClosingData::getTotalWithdrawal).reduce(BigDecimal.ZERO, BigDecimal::add))
                .branchName(branchName)
                .build();

        // branchClosingVaultCash == null 이라면 아직 OPEN 중인 상태
        if(branchClosingVaultCash == null) {
            // 직전 영업일 현금 마감액 + 오늘자 현금 입금액 - 오늘자 현금 출금액
            BigDecimal expectedBranchClosingVaultCash = managerClosingData.prevCashBalanceOfBranch.add(managerClosingData.totalDepositOfBranch).subtract(managerClosingData.totalWithdrawalOfBranch);
            managerClosingData.setVaultCashOfBranch(expectedBranchClosingVaultCash);
        }
        
        return managerClosingData;
    }
}
