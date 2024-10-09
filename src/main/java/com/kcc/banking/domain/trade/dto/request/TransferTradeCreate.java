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
    private String withdrawalAccount; // 출금 계좌
    private String depositAccount;
    private BigDecimal transferAmount;
    private String description;
    private String withdrawalAccountPassword;

    @Builder
    public TransferTradeCreate(String withdrawalAccount, String depositAccount, BigDecimal transferAmount , String description, String withdrawalAccountPassword) {
        this.withdrawalAccount = withdrawalAccount;
        this.depositAccount = depositAccount;
        this.transferAmount = transferAmount;
        this.description = description;
        this.withdrawalAccountPassword = withdrawalAccountPassword;
    }
}
