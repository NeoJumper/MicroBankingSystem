package com.kcc.banking.domain.bulk_transfer.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class BulkTransferCreate {
    private Long id;
    private Long registrantId;
    private String accId;
    private Long branchId;
    private Timestamp tradeDate;
    private BigDecimal registeredAmount;
    private BigDecimal amount;
    private Integer successCnt;
    private Integer failureCnt;
    private Integer totalCnt;
    private String status;
    private String description;

    @Builder
    public BulkTransferCreate(Long id, Long registrantId, String accId, Long branchId, Timestamp tradeDate, BigDecimal registeredAmount, BigDecimal amount, Integer successCnt, Integer failureCnt,Integer totalCnt, String status, String description) {
        this.id = id;
        this.registrantId = registrantId;
        this.accId = accId;
        this.branchId = branchId;
        this.tradeDate = tradeDate;
        this.registeredAmount = registeredAmount;
        this.amount = amount;
        this.successCnt = successCnt;
        this.failureCnt = failureCnt;
        this.totalCnt = totalCnt;
        this.status = status;
        this.description = description;
    }
}
