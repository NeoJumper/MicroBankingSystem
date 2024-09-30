package com.kcc.banking.domain.business_day_close.dto.response;

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
}
