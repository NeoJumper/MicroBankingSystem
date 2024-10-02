package com.kcc.banking.domain.trade.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@Getter
public class TradeCashOfPerAccount {

    List<TradeOfList> tradeList;
    //총 입출금 금액
    private BigDecimal totalDeposit;
    private BigDecimal totalWithDraw;


    @Builder
    public TradeCashOfPerAccount(List<TradeOfList> tradeList, BigDecimal totalDeposit, BigDecimal totalWithDraw) {
        this.tradeList = tradeList;
        this.totalDeposit = totalDeposit;
        this.totalWithDraw = totalWithDraw;
    }

    // 한 계좌에 대한 입금액(개설 입금액, 입금) 출금액(출금액,해지시 출금액)
    public static TradeCashOfPerAccount of(List<TradeOfList> tradeList) {
        // 입금 합계 (개설 "OPEN", 입금 "DEPOSIT")
        BigDecimal totalDeposit = tradeList.stream()
                .filter(trade -> "OPEN".equals(trade.getTradeType()) || "DEPOSIT".equals(trade.getTradeType()))
                .map(TradeOfList::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // 출금 합계 (출금 "WITHDRAWAL", 해지 "CLOSE")
        BigDecimal totalWithDraw = tradeList.stream()
                .filter(trade -> "WITHDRAWAL".equals(trade.getTradeType()) || "CLOSE".equals(trade.getTradeType()))
                .map(TradeOfList::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return TradeCashOfPerAccount.builder()
                .tradeList(tradeList)
                .totalDeposit(totalDeposit)
                .totalWithDraw(totalWithDraw)
                .build();
    }

}
