package com.kcc.banking.domain.bulk_transfer.dto.request;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter
public class BulkTransferValidation {

    private String targetAccId;
    private BigDecimal transferAmount; // 이체금액
    private String krw; // 한글 금액 표기
    private String depositor; // 예금주
    private String description; // 비고

    @Builder
    public BulkTransferValidation(String targetAccId, BigDecimal transferAmount, String krw, String depositor, String description) {
        this.targetAccId = targetAccId;
        this.transferAmount = transferAmount;
        this.krw = krw;
        this.depositor = depositor;
        this.description = description;
    }
}