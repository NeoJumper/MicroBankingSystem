package com.kcc.banking.domain.bulk_transfer.dto.response;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter
public class BulkTransferValidationResult {



    private String targetAccId;
    private BigDecimal transferAmount; // 이체금액
    private String krw; // 한글 금액 표기
    private String depositor; // 예금주
    private String validDepositor;
    private String description; // 비고
    private String status;

    @Builder
    public BulkTransferValidationResult(String targetAccId, BigDecimal transferAmount, String krw, String depositor, String validDepositor, String description, String status) {
        this.targetAccId = targetAccId;
        this.transferAmount = transferAmount;
        this.krw = krw;
        this.depositor = depositor;
        this.validDepositor = validDepositor;
        this.description = description;
        this.status = status;
    }
}