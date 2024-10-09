package com.kcc.banking.domain.account.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
public class StatusWithTrade {
    private String accId;
    private BigDecimal amount;
    private String status;
    private String description;
    private BigDecimal balance;
    private String tradeType;
}
