package com.kcc.banking.domain.bulk_transfer.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

@NoArgsConstructor
@Getter
public class BulkTransferDetail {

    private Long rn;
    private Long id;
    private Timestamp registrationDate;
    private BigDecimal amount;
    private int successCnt;
    private int failureCnt;
    private int totalCnt;
    private String description;
    private String status;


    @Builder
    public BulkTransferDetail(Long rn, Long id, Timestamp registrationDate, BigDecimal amount, int successCnt, int failureCnt, int totalCnt, String description, String status) {
        this.rn = rn;
        this.id = id;
        this.registrationDate = registrationDate;
        this.amount = amount;
        this.successCnt = successCnt;
        this.failureCnt = failureCnt;
        this.totalCnt = totalCnt;
        this.description = description;
        this.status = status;
    }
}
