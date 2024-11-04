package com.kcc.banking.domain.reserve_transfer.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;

@NoArgsConstructor
@Getter
@Setter
public class ReserveTransferCreate {

    private Long id;
    private String accId;
    private String targetAccId;
    private Long bulkTransferId;
    private String autoTransferId;
    private BigDecimal amount;
    private Timestamp transferDate;
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

    private Timestamp createDate; // 추가된 필드
    private Timestamp modificationDate; // 추가된 필드
    private Long modifierId; // 추가된 필드
    
    @Builder
    public ReserveTransferCreate(Long id, String accId, String targetAccId, Long bulkTransferId, String autoTransferId, BigDecimal amount, Timestamp transferDate, String transferStartTime, String transferEndTime, Long retryCount, String description, String status, String failureReason, Long registrantId, Long branchId, String transferType, Timestamp createDate, Timestamp modificationDate, long modifierId ) {
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
        this.createDate =createDate;
        this.modificationDate = modificationDate;
        this.modifierId = modifierId;
    }
}
