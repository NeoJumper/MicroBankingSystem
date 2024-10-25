package com.kcc.banking.domain.business_day_close.dto.response;

import com.kcc.banking.domain.cash_exchange.dto.response.CashExchangeData;
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

    // employee_close 테이블에서의 총 입금액, 총 출금액
    private ClosingData closingData;
    /*
     employee_close 테이블에서 이전 마감액 + 총 입금액 - 총 출금액
     거래내역의 총 합 + 인수도거래 총 합 = employe_close 테이블에서의 금일 마감 총액
     위 식이 TRUE 일 때 마감이 이루어져야 함
    */
    private BigDecimal expectedVaultCash;
    // trade 테이블에서의 거래 내역
    private List<TradeByCash> tradeByCashList;
    // cash_exchange에서의 거래 내역
    private List<CashExchangeData> cashExchangeDataList;
    //    거래내역에서의 총 입금, 총 출금, 총 합
    private BigDecimal totalDepositOfTrade;
    private BigDecimal totalWithdrawalOfTrade;
    private BigDecimal vaultCashOfTrade;
    //    인수도거래에서의 총 입금과 총 출금, 총 합
    private BigDecimal totalDepositOfCashExchange;
    private BigDecimal totalWithdrawalOfCashExchange;
    private BigDecimal vaultCashOfCashExchange;

    @Builder
    public EmployeeClosingData(ClosingData closingData, BigDecimal expectedVaultCash, List<TradeByCash> tradeByCashList, List<CashExchangeData> cashExchangeDataList, BigDecimal totalDepositOfTrade, BigDecimal totalWithdrawalOfTrade, BigDecimal vaultCashOfTrade, BigDecimal totalDepositOfCashExchange, BigDecimal totalWithdrawalOfCashExchange, BigDecimal vaultCashOfCashExchange) {
        this.closingData = closingData;
        this.expectedVaultCash = expectedVaultCash;
        this.tradeByCashList = tradeByCashList;
        this.cashExchangeDataList = cashExchangeDataList;
        this.totalDepositOfTrade = totalDepositOfTrade;
        this.totalWithdrawalOfTrade = totalWithdrawalOfTrade;
        this.vaultCashOfTrade = vaultCashOfTrade;
        this.totalDepositOfCashExchange = totalDepositOfCashExchange;
        this.totalWithdrawalOfCashExchange = totalWithdrawalOfCashExchange;
        this.vaultCashOfCashExchange = vaultCashOfCashExchange;
    }

    public static EmployeeClosingData of(ClosingData closingData, List<TradeByCash> tradeByCashList, List<CashExchangeData> cashExchangeList) {

        EmployeeClosingData employeeClosingData = EmployeeClosingData.builder()
                .closingData(closingData)
                .tradeByCashList(tradeByCashList)
                .cashExchangeDataList(cashExchangeList)
                .totalDepositOfTrade(tradeByCashList.stream()
                        .filter(trade -> "OPEN".equals(trade.getTradeType()) || "DEPOSIT".equals(trade.getTradeType()))
                        .map(TradeByCash::getAmount)
                        .reduce(BigDecimal.ZERO, BigDecimal::add)
                )
                .totalWithdrawalOfTrade(tradeByCashList.stream()
                        .filter(trade -> "WITHDRAWAL".equals(trade.getTradeType()) || "CLOSE".equals(trade.getTradeType()))
                        .map(TradeByCash::getAmount)
                        .reduce(BigDecimal.ZERO, BigDecimal::add)
                )
                .totalDepositOfCashExchange(cashExchangeList.stream()
                        .filter(cashExchangeData -> "HANDOVER".equals(cashExchangeData.getExchangeType()))
                        .map(CashExchangeData::getAmount)
                        .reduce(BigDecimal.ZERO, BigDecimal::add)
                )
                .totalWithdrawalOfCashExchange(cashExchangeList.stream()
                        .filter(cashExchangeData -> "RECEIPT".equals(cashExchangeData.getExchangeType()))
                        .map(CashExchangeData::getAmount)
                        .reduce(BigDecimal.ZERO, BigDecimal::add)
                )
                .build();

        // empolye_close 테이블에서의 vault 설정
        employeeClosingData.setExpectedVaultCash(closingData.getPrevCashBalance().add(closingData.getTotalDeposit()).subtract(closingData.getTotalWithdrawal()));

        // trade 테이블에서의 vault 설정
        BigDecimal vaultCashOfTrade = employeeClosingData.getClosingData().getPrevCashBalance().add(employeeClosingData.getTotalDepositOfTrade()).subtract(employeeClosingData.getTotalWithdrawalOfTrade());
        employeeClosingData.setVaultCashOfTrade(vaultCashOfTrade);

        // cash_exchange에서의 vault 설정
        BigDecimal vaultCashOfCashExchange = employeeClosingData.getTotalDepositOfCashExchange().subtract(employeeClosingData.getTotalWithdrawalOfCashExchange());
        employeeClosingData.setVaultCashOfCashExchange(vaultCashOfCashExchange);

        return employeeClosingData;
    }
}
