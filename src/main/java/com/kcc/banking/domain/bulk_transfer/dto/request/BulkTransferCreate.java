package com.kcc.banking.domain.bulk_transfer.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class BulkTransferCreate {
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

    @Builder
    public BulkTransferCreate(Long id, Long registrantId, String accId, Long branchId, String tradeDate, BigDecimal amount, Integer successCnt, Integer failureCnt, String status, String description) {
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
    }
}
