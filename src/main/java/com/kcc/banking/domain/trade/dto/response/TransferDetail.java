package com.kcc.banking.domain.trade.dto.response;

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
    private String tradeDate;
    private BigDecimal amount;
    private BigDecimal balance;
    private String tradeType;
    private String status;
    private String cashIndicator;
    private String description;
    private Long tradeNumber;
    private Timestamp registrationDate;
    private Timestamp modificationDate;
    private Long modifierId;
    private Long version;

    // Additional
    private String customerName;
    private String targetCustomerName;

    @Builder
    public TransferDetail(Long id, Long registrantId, String accId, String targetAccId, Long branchId, String tradeDate, BigDecimal amount, BigDecimal balance, String tradeType, String status, String cashIndicator, String description, Long tradeNumber, Timestamp registrationDate, Timestamp modificationDate, Long modifierId, Long version, String customerName, String targetCustomerName) {
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
        this.registrationDate = registrationDate;
        this.modificationDate = modificationDate;
        this.modifierId = modifierId;
        this.version = version;

        this.customerName = customerName;
        this.targetCustomerName = targetCustomerName;
    }
}
