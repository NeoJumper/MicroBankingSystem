package com.kcc.banking.domain.bulk_transfer.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;

@NoArgsConstructor
@Getter
@Setter
public class BulkTransferDetail {

    private Long rn;
    private String accId;
    private Long id;
    private String registrantName;
    private Timestamp registrationDate;
    private BigDecimal registeredAmount;
    private BigDecimal amount;
    private BigDecimal failureAmount;
    private int successCnt;
    private int failureCnt;
    private int totalCnt;
    private String description;
    private String status;
    private Long registrantId;


    @Builder
    public BulkTransferDetail(Long rn, String accId, Long id, String registrantName,BigDecimal failureAmount ,Timestamp registrationDate,BigDecimal registeredAmount, BigDecimal amount, BigDecimal failAmount,int successCnt, int failureCnt, int totalCnt, String description, String status, Long registrantId) {
        this.rn = rn;
        this.accId = accId;
        this.id = id;
        this.registrantName = registrantName;
        this.registrationDate = registrationDate;
        this.amount = amount;
        this.failureAmount = failureAmount;
        this.registeredAmount = registeredAmount;
        this.successCnt = successCnt;
        this.failureCnt = failureCnt;
        this.totalCnt = totalCnt;
        this.description = description;
        this.status = status;
        this.registrantId = registrantId;
    }

    public void setFailureAmount() {
        this.failureAmount = registeredAmount.subtract(amount);
    }
}
