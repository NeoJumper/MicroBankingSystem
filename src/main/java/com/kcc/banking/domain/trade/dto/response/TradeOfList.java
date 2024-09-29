package com.kcc.banking.domain.trade.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class TradeOfList {

    /*

        거래일시

        대상계좌
        상대계좌
        유형
        현금여부
        거래액
        잔액
        담당자
        거래점
        상태
     */

    private String acc_id;

}
