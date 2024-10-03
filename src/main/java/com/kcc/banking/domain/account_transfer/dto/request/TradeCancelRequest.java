package com.kcc.banking.domain.account_transfer.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class TradeCancelRequest {
    private String tradeNumber;
    private String password;


    @Builder
    public TradeCancelRequest(String tradeNumber, String password) {
        this.tradeNumber = tradeNumber;
        this.password = password;
    }
}
