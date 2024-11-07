package com.kcc.banking.domain.account.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
public class CloseFixedAccountDetail {

    private BigDecimal amount;
    private int totalTransferCount;

}
