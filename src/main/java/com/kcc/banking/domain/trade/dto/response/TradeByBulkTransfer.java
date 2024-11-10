package com.kcc.banking.domain.trade.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
public class TradeByBulkTransfer {

    private Long id;
    private String status;     //처리 결과
    private String targetAccId;    //입금 계좌번호
    private BigDecimal amount;    //이체금액
    private String targetName;    //받는분
    private String description;    //받는분 통장표시
    private String failureReason; // 실패사유


    @Builder
    public TradeByBulkTransfer(Long id, String status, String targetAccId, BigDecimal amount, String targetName, String description, String failureReason) {
        this.id = id;
        this.status = status;
        this.targetAccId = targetAccId;
        this.amount = amount;
        this.targetName = targetName;
        this.description = description;
        this.failureReason = failureReason;
    }
}
