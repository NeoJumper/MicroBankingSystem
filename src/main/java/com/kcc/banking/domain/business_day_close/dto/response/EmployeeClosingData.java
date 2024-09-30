package com.kcc.banking.domain.business_day_close.dto.response;

import com.kcc.banking.domain.trade.dto.response.TradeByCash;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@Getter
public class EmployeeClosingData {

    private ClosingData closingData;
    private List<TradeByCash> tradeByCashList;
    private BigDecimal totalDepositOfTrade;
    private BigDecimal totalWithdrawalOfTrade;

    @Builder
    public EmployeeClosingData(ClosingData closingData,List<TradeByCash> tradeByCashList, BigDecimal totalDepositOfTrade, BigDecimal totalWithdrawalOfTrade) {
        this.closingData = closingData;
        this.tradeByCashList = tradeByCashList;
        this.totalDepositOfTrade = totalDepositOfTrade;
        this.totalWithdrawalOfTrade = totalWithdrawalOfTrade;
    }

    public static EmployeeClosingData of(ClosingData closingData, List<TradeByCash> tradeByCashList)
    {
        return EmployeeClosingData.builder()
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
    }
}
