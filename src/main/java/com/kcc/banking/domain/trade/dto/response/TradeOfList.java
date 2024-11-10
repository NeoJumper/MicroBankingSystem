package com.kcc.banking.domain.trade.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

@NoArgsConstructor
@Getter
public class TradeOfList {

    /*
        거래일시
        대상계좌
        상대계좌
        거래유형
        현금여부
        거래액
        잔액
        상태
     */

    private Timestamp tradeDate;
    private String accId ;
    private String targetAccId;
    private String tradeType;
    private String cashIndicator;
    private BigDecimal amount;
    private BigDecimal balance;
    private String status;
    private Long tradeNumber;

    @Builder
    public TradeOfList(Timestamp tradeDate, String accId, String targetAccId, String tradeType, String cashIndicator, BigDecimal amount, BigDecimal balance, String status, Long tradeNumber) {
        this.tradeDate = tradeDate;
        this.accId = accId;
        this.targetAccId = targetAccId;
        this.tradeType = tradeType;
        this.cashIndicator = cashIndicator;
        this.amount = amount;
        this.balance = balance;
        this.status = status;
        this.tradeNumber = tradeNumber;
    }
}
