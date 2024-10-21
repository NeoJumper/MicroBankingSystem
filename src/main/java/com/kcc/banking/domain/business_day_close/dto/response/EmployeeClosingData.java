package com.kcc.banking.domain.business_day_close.dto.response;

import com.kcc.banking.domain.trade.dto.response.TradeByCash;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class EmployeeClosingData {

    private ClosingData closingData;
    private BigDecimal expectedVaultCash;
    private List<TradeByCash> tradeByCashList;
    private BigDecimal totalDepositOfTrade;
    private BigDecimal totalWithdrawalOfTrade;
    private BigDecimal vaultCashOfTrade;

    @Builder
    public EmployeeClosingData(ClosingData closingData,BigDecimal expectedVaultCash, List<TradeByCash> tradeByCashList, BigDecimal totalDepositOfTrade, BigDecimal totalWithdrawalOfTrade, BigDecimal vaultCashOfTrade) {
        this.closingData = closingData;
        this.expectedVaultCash = expectedVaultCash;
        this.tradeByCashList = tradeByCashList;
        this.totalDepositOfTrade = totalDepositOfTrade;
        this.totalWithdrawalOfTrade = totalWithdrawalOfTrade;
        this.vaultCashOfTrade = vaultCashOfTrade;
    }

    public static EmployeeClosingData of(ClosingData closingData, List<TradeByCash> tradeByCashList)
    {

        EmployeeClosingData employeeClosingData = EmployeeClosingData.builder()
                .closingData(closingData)
                .tradeByCashList(tradeByCashList)
                .totalDepositOfTrade(tradeByCashList.stream()
                        .filter(trade -> "OPEN".equals(trade.getTradeType()) || "DEPOSIT".equals(trade.getTradeType()))
                        .map(TradeByCash::getAmount)
                        .reduce(BigDecimal.ZERO, BigDecimal::add)
                )
                .totalWithdrawalOfTrade(tradeByCashList.stream()
                        .filter(trade -> "WITHDRAWAL".equals(trade.getTradeType()) || "CLOSE".equals(trade.getTradeType()))
                        .map(TradeByCash::getAmount)
                        .reduce(BigDecimal.ZERO, BigDecimal::add)
                ).build();

        BigDecimal vaultCashOfTrade = employeeClosingData.getClosingData().getPrevCashBalance().add(employeeClosingData.getTotalDepositOfTrade()).subtract(employeeClosingData.getTotalWithdrawalOfTrade());
        // empolye_close 테이블에서의 vault 설정
        employeeClosingData.setExpectedVaultCash(closingData.getPrevCashBalance().add(closingData.getTotalDeposit()).subtract(closingData.getTotalWithdrawal()));
        // trade 테이블에서의 vault 설정
        employeeClosingData.setVaultCashOfTrade(vaultCashOfTrade);

        return employeeClosingData;
    }
}
