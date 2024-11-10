package com.kcc.banking.domain.trade.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class TransferTradeCreate {
    private String accId;
    private String targetAccId;
    private Long bulkTransferId;
    private BigDecimal transferAmount;
    private String description;
    private String accountPassword;
    private String krw; // 한글 금액 표기
    private String depositor; // 예금주
    private String failureReason;

    // 예약이체를 위한 컬럼(나머지는 위에서 찾아쓰면 됨)
    private String reserveTransferId;
    private String transferStartTime;
    private String transferEndTime;
    private Long retryCount;
    private String status;
    private String transferType;
    private String autoTransferId;
    private Long registrantId;
    private Long branchId;

    private Long missedCount;

    // 영업일
    private Timestamp transferDate;

    @Builder
    public TransferTradeCreate(String accId, String targetAccId, Long bulkTransferId, BigDecimal transferAmount, String description, String accountPassword, String krw, String depositor, String failureReason, String reserveTransferId, String transferStartTime, String transferEndTime, Long retryCount, String status, Long missedCount, String autoTransferId) {
        this.accId = accId;
        this.targetAccId = targetAccId;
        this.bulkTransferId = bulkTransferId;
        this.transferAmount = transferAmount;
        this.description = description;
        this.accountPassword = accountPassword;
        this.krw = krw;
        this.depositor = depositor;
        this.failureReason = failureReason;
        this.reserveTransferId = reserveTransferId;
        this.transferStartTime = transferStartTime;
        this.transferEndTime = transferEndTime;
        this.retryCount = retryCount;
        this.status = status;
        this.autoTransferId = autoTransferId;
        // 자동이체 실패횟수
        this.missedCount = missedCount;
    }


    @Override
    public String toString() {
        return "TransferTradeCreate{" +
                "accId='" + accId + '\'' +
                ", targetAccId='" + targetAccId + '\'' +
                ", bulkTransferId=" + bulkTransferId +
                ", transferAmount=" + transferAmount +
                ", description='" + description + '\'' +
                ", accountPassword='" + accountPassword + '\'' +
                ", krw='" + krw + '\'' +
                ", depositor='" + depositor + '\'' +
                ", failureReason='" + failureReason + '\'' +
                ", reserveTransferId='" + reserveTransferId + '\'' +
                ", transferStartTime='" + transferStartTime + '\'' +
                ", transferEndTime='" + transferEndTime + '\'' +
                ", retryCount=" + retryCount +
                ", status='" + status + '\'' +
                ", transferType='" + transferType + '\'' +
                ", missedCount=" + missedCount +
                ", transferDate='" + transferDate + '\'' +
                ", autoTransferId='" + autoTransferId + '\'' +

                '}';
    }
}
