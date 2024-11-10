package com.kcc.banking.domain.trade.dto.request;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class TradeCancelRequest {
    private Long tradeNumber;
    private String password;


    @Builder
    public TradeCancelRequest(Long tradeNumber, String password) {
        this.tradeNumber = tradeNumber;
        this.password = password;
    }
}
