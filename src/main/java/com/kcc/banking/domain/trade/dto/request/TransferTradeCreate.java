package com.kcc.banking.domain.trade.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class TransferTradeCreate {
    private String accId;
    private String targetAccId;
    private BigDecimal transferAmount;
    private String description;
    private String accountPassword;

    @Builder
    public TransferTradeCreate(String accId, String targetAccId, BigDecimal transferAmount , String description, String accountPassword) {
        this.accId = accId;
        this.targetAccId = targetAccId;
        this.transferAmount = transferAmount;
        this.description = description;
        this.accountPassword = accountPassword;
    }
}
