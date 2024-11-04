package com.kcc.banking.domain.reserve_transfer.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter
public class ReserveTransferCreate {

    private Long id;
    private String accId;
    private String targetAccId;
    private Long bulkTransferId;
    private Long autoTransferId;
    private BigDecimal amount;
    private String transferDate;
    private String transferStartTime;
    private String transferEndTime;
    private Long retryCount;

    private String description;
    private String status;
    private String failureReason;
    private Long registrantId;
    private Long branchId;
    // 자동이체 AUTO, 일반이체 NORMAL
    private String transferType;
    
    @Builder
    public ReserveTransferCreate(Long id, String accId, String targetAccId, Long bulkTransferId, Long autoTransferId, BigDecimal amount, String transferDate, String transferStartTime, String transferEndTime, Long retryCount, String description, String status, String failureReason, Long registrantId, Long branchId, String transferType) {
        this.id = id;
        this.accId = accId;
        this.targetAccId = targetAccId;
        this.bulkTransferId = bulkTransferId;
        this.amount = amount;
        this.transferDate = transferDate;
        this.transferStartTime = transferStartTime;
        this.transferEndTime = transferEndTime;
        this.retryCount = retryCount;
        this.transferType = transferType;
        this.description = description;
        this.status = status;
        this.failureReason = failureReason;
        this.registrantId = registrantId;
        this.branchId = branchId;
        this.autoTransferId = autoTransferId;
    }
}
