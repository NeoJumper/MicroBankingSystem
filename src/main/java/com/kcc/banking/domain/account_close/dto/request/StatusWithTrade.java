package com.kcc.banking.domain.account_close.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
public class StatusWithTrade {
    private String accId;
    private BigDecimal amount;
    private String status;
}
