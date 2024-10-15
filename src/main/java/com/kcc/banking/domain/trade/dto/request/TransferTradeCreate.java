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
    private Long bulkTransferId;
    private BigDecimal transferAmount;
    private String description;
    private String accountPassword;
    private String krw; // 한글 금액 표기
    private String depositor; // 예금주
    private String failureReason;

    @Builder
    public TransferTradeCreate(String accId, String targetAccId, Long bulkTransferId, BigDecimal transferAmount, String description, String accountPassword, String krw, String depositor, String failureReason) {
        this.accId = accId;
        this.targetAccId = targetAccId;
        this.bulkTransferId = bulkTransferId;
        this.transferAmount = transferAmount;
        this.description = description;
        this.accountPassword = accountPassword;
        this.krw = krw;
        this.depositor = depositor;
        this.failureReason = failureReason;
    }
}
