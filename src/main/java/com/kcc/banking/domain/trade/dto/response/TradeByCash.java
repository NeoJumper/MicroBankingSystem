package com.kcc.banking.domain.trade.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;


@NoArgsConstructor
@Getter
@Setter
public class TradeByCash {
    private Timestamp tradeDate;      // 거래 날짜
    private String accId;             // 계좌 ID
    private String tradeType;         // 거래 유형 (입금/출금)
    private BigDecimal amount;        // 거래 금액
    private String registrantName;    // 등록자 이름
    private String branchName;        // 지점 이름


    @Builder
    public TradeByCash(Timestamp tradeDate, String accId, String tradeType, BigDecimal amount, String registrantName, String branchName) {
        this.tradeDate = tradeDate;
        this.accId = accId;
        this.tradeType = tradeType;
        this.amount = amount;
        this.registrantName = registrantName;
        this.branchName = branchName;
    }
}
