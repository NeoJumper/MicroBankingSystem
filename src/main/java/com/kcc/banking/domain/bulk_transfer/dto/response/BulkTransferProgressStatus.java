package com.kcc.banking.domain.bulk_transfer.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

@NoArgsConstructor
@Getter
public class BulkTransferProgressStatus {

    private int successCnt;
    private int failureCnt;
    private int totalCnt;
    private String status;


    @Builder
    public BulkTransferProgressStatus(int successCnt, int failureCnt, int totalCnt, String status) {
        this.successCnt = successCnt;
        this.failureCnt = failureCnt;
        this.totalCnt = totalCnt;
        this.status = status;
    }
}
