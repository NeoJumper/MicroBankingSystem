package com.kcc.banking.domain.bulk_transfer.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class BulkTransferUpdate {
    private Long id;
    private Long registrantId;
    private String accId;
    private Long branchId;
    private String tradeDate;
    private BigDecimal amount;
    private Integer successCnt;
    private Integer failureCnt;
    private String status;
    private String description;
    private Long modifierId;

    @Builder
    public BulkTransferUpdate(Long id, Long registrantId, String accId, Long branchId, String tradeDate, BigDecimal amount, Integer successCnt, Integer failureCnt, String status, String description, Long modifierId) {
        this.id = id;
        this.registrantId = registrantId;
        this.accId = accId;
        this.branchId = branchId;
        this.tradeDate = tradeDate;
        this.amount = amount;
        this.successCnt = successCnt;
        this.failureCnt = failureCnt;
        this.status = status;
        this.description = description;
        this.modifierId = modifierId;
    }
}
