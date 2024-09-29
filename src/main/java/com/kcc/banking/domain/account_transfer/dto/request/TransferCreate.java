package com.kcc.banking.domain.account_transfer.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class TransferCreate {
    private String withdrawalAccount;
    private String depositAccount;
    private BigDecimal transferAmount;
    private String description;

    @Builder
    public TransferCreate(String withdrawalAccount, String depositAccount, BigDecimal transferAmount ,String description) {
        this.withdrawalAccount = withdrawalAccount;
        this.depositAccount = depositAccount;
        this.transferAmount = transferAmount;
        this.description = description;
    }
}
