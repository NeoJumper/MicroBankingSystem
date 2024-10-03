package com.kcc.banking.domain.trade.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;


@NoArgsConstructor
@Getter
@Setter
public class TradeInfoOfPerAccount {

    private String accId ;
    private String tradeType;
    private BigDecimal amount;
    private String status;


}
