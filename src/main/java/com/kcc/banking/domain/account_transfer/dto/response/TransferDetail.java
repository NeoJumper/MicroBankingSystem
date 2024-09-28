package com.kcc.banking.domain.account_transfer.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class TransferDetail {

    private Long id;
    private Long registrantId;
    private String accId;
    private String targetAccId;
    private Long branchId;
    private Timestamp tradeDate;
    private BigDecimal amount;
    private BigDecimal balance;
    private String tradeType;
    private String status;
    private String cashIndicator;
    private String description;
    private String tradeNumber;

    @Builder
    public TransferDetail(Long id, Long registrantId, String accId, String targetAccId, Long branchId, Timestamp tradeDate, BigDecimal amount, BigDecimal balance, String tradeType, String status, String cashIndicator, String description, String tradeNumber) {
        this.id = id;
        this.registrantId = registrantId;
        this.accId = accId;
        this.targetAccId = targetAccId;
        this.branchId = branchId;
        this.tradeDate = tradeDate;
        this.amount = amount;
        this.balance = balance;
        this.tradeType = tradeType;
        this.status = status;
        this.cashIndicator = cashIndicator;
        this.description = description;
        this.tradeNumber = tradeNumber;
    }
}
