package com.kcc.banking.domain.trade.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class TradeUpdate {

    private Long id;
    private Long registrantId;
    private String accId;
    private String targetAccId;
    private Long bulkTransferId;
    private Long branchId;
    private String tradeDate;
    private BigDecimal amount;
    private BigDecimal balance;
    private String tradeType;
    private String status;
    private String cashIndicator;
    private String description;
    private Long tradeNumber;
    private String failureReason;
    private String modifierId;

    @Builder
    public TradeUpdate(Long id, Long registrantId, String accId, String targetAccId, Long bulkTransferId, Long branchId, String tradeDate, BigDecimal amount, BigDecimal balance, String tradeType, String status, String cashIndicator, String description, Long tradeNumber, String failureReason, String modifierId) {
        this.id = id;
        this.registrantId = registrantId;
        this.accId = accId;
        this.targetAccId = targetAccId;
        this.bulkTransferId = bulkTransferId;
        this.branchId = branchId;
        this.tradeDate = tradeDate;
        this.amount = amount;
        this.balance = balance;
        this.tradeType = tradeType;
        this.status = status;
        this.cashIndicator = cashIndicator;
        this.description = description;
        this.tradeNumber = tradeNumber;
        this.failureReason = failureReason;
        this.modifierId = modifierId;
    }
}
