package com.kcc.banking.domain.trade.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@Getter
public class PagingInfoOfTradeList {

    // 페이징 처리한 거래내역 정보들
    List<TradeOfList> tradeList;

    // 한 계좌에 대한 전체 거래 내역 정보들
    List<TradeInfoOfPerAccount> tradeInfoListOfPerAccount;

    //총 입출금 금액
    private BigDecimal totalDeposit;
    private BigDecimal totalWithDraw;

    // 특정 계좌의 거래 내역 총 거래 수
    private int totalCount;
    // 페이징 정보
    private PageDTO pageDTO;


    public PagingInfoOfTradeList(List<TradeOfList> tradeList, int totalCount, PageDTO pageDTO) {
        this.tradeList = tradeList;
        this.totalCount = totalCount;
        this.pageDTO = pageDTO;
    }

    @Builder
    public PagingInfoOfTradeList(List<TradeOfList> tradeList, BigDecimal totalDeposit, BigDecimal totalWithDraw, int totalCount, PageDTO pageDTO, List<TradeInfoOfPerAccount> tradeInfoOfPerAccount) {
        this.tradeList = tradeList;
        this.totalDeposit = totalDeposit;
        this.totalWithDraw = totalWithDraw;

        this.totalCount = totalCount;
        this.pageDTO = pageDTO;
        this.tradeInfoListOfPerAccount = tradeInfoOfPerAccount;
    }



    // 한 계좌에 대한 입금액(개설 입금액, 입금) 출금액(출금액,해지시 출금액)
    public static PagingInfoOfTradeList of(List<TradeOfList> tradeList, PageDTO pageDTO, int totalCount, List<TradeInfoOfPerAccount> tradeInfoOfPerAccount) {
        // 입금 합계 (개설 "OPEN", 입금 "DEPOSIT")
        BigDecimal totalDeposit = tradeInfoOfPerAccount.stream()
                .filter(trade -> "OPEN".equals(trade.getTradeType()) || "DEPOSIT".equals(trade.getTradeType()))
                .map(TradeInfoOfPerAccount::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // 출금 합계 (출금 "WITHDRAWAL", 해지 "CLOSE")
        BigDecimal totalWithDraw = tradeInfoOfPerAccount.stream()
                .filter(trade -> "WITHDRAWAL".equals(trade.getTradeType()) || "CLOSE".equals(trade.getTradeType()))
                .map(TradeInfoOfPerAccount::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return PagingInfoOfTradeList.builder()
                .tradeList(tradeList) // 한계좌당 거래내역 리스트 결과
                .totalDeposit(totalDeposit) // 총입금액
                .totalWithDraw(totalWithDraw) // 총출금액
                .pageDTO(pageDTO)
                .totalCount(totalCount)//페이징 정보
                .build();
    }

}
